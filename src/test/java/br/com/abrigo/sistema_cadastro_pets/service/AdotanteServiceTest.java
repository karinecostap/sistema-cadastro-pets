package br.com.abrigo.sistema_cadastro_pets.service;

import br.com.abrigo.sistema_cadastro_pets.client.ViaCepClient;
import br.com.abrigo.sistema_cadastro_pets.dto.AdotanteCadastroDTO;
import br.com.abrigo.sistema_cadastro_pets.dto.EnderecoDTO;
import br.com.abrigo.sistema_cadastro_pets.dto.ViaCepDTO;
import br.com.abrigo.sistema_cadastro_pets.model.Adotante;
import br.com.abrigo.sistema_cadastro_pets.model.Endereco;
import br.com.abrigo.sistema_cadastro_pets.repository.AdotanteRepository;
import br.com.abrigo.sistema_cadastro_pets.repository.EnderecoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdotanteServiceTest {

    @Mock
    private AdotanteRepository adotanteRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private ViaCepClient viaCepClient;

    @InjectMocks
    private AdotanteService adotanteService;

    @Test
    @DisplayName("Deve cadastrar adotante com sucesso quando endereço já existe no banco")
    void deveCadastrarAdotanteComSucesso_QuandoEnderecoExisteNoBanco() throws Exception {
        // Arrange
        EnderecoDTO enderecoDTO = new EnderecoDTO("12345-678", "10");
        AdotanteCadastroDTO cadastroDTO = new AdotanteCadastroDTO("João", "joao@email.com", "123456789", enderecoDTO);

        Endereco enderecoExistente = new Endereco();
        enderecoExistente.setId(1);
        enderecoExistente.setCep("12345-678");

        Adotante adotanteSalvo = new Adotante();
        adotanteSalvo.setId(1);
        adotanteSalvo.setNome("João");
        adotanteSalvo.setEndereco(enderecoExistente);

        when(adotanteRepository.findByNome(cadastroDTO.getNome())).thenReturn(null);
        when(enderecoRepository.findByCepAndNumero(enderecoDTO.getCep(), enderecoDTO.getNumero()))
                .thenReturn(Optional.of(enderecoExistente));
        when(adotanteRepository.save(any(Adotante.class))).thenReturn(adotanteSalvo);

        // Act
        Adotante resultado = adotanteService.cadastrarAdotante(cadastroDTO);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("João");
        assertThat(resultado.getEndereco()).isEqualTo(enderecoExistente);

        verify(adotanteRepository).findByNome("João");
        verify(enderecoRepository).findByCepAndNumero("12345-678", "10");
        verify(viaCepClient, never()).buscarEnderecoPorCep(anyString()); // Não deve chamar ViaCep
        verify(adotanteRepository).save(any(Adotante.class));
    }

    @Test
    @DisplayName("Deve cadastrar adotante com sucesso buscando endereço na API externa")
    void deveCadastrarAdotanteComSucesso_QuandoBuscaEnderecoExterno() throws Exception {
        // Arrange
        EnderecoDTO enderecoDTO = new EnderecoDTO("87654-321", "20");
        AdotanteCadastroDTO cadastroDTO = new AdotanteCadastroDTO("Maria", "maria@email.com", "987654321", enderecoDTO);

        ViaCepDTO viaCepDTO = new ViaCepDTO("87654-321", "Rua Teste", "Bairro Teste", "Cidade Teste", "UF");
        Endereco novoEndereco = new Endereco();
        novoEndereco.setCep("87654-321");
        novoEndereco.setNumero("20");

        Adotante adotanteSalvo = new Adotante();
        adotanteSalvo.setNome("Maria");
        adotanteSalvo.setEndereco(novoEndereco);

        when(adotanteRepository.findByNome(cadastroDTO.getNome())).thenReturn(null);
        when(enderecoRepository.findByCepAndNumero(enderecoDTO.getCep(), enderecoDTO.getNumero()))
                .thenReturn(Optional.empty()); // Endereço não existe no banco
        when(viaCepClient.buscarEnderecoPorCep(enderecoDTO.getCep())).thenReturn(viaCepDTO);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(novoEndereco);
        when(adotanteRepository.save(any(Adotante.class))).thenReturn(adotanteSalvo);

        // Act
        Adotante resultado = adotanteService.cadastrarAdotante(cadastroDTO);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getEndereco().getCep()).isEqualTo("87654-321");

        verify(viaCepClient).buscarEnderecoPorCep("87654-321");
        verify(enderecoRepository).save(any(Endereco.class));
        verify(adotanteRepository).save(any(Adotante.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando adotante já existe")
    void deveLancarExcecao_QuandoAdotanteJaExiste() {
        // Arrange
        EnderecoDTO enderecoDTO = new EnderecoDTO("12345-678", "10");
        AdotanteCadastroDTO cadastroDTO = new AdotanteCadastroDTO("João", "joao@email.com", "123456789", enderecoDTO);

        when(adotanteRepository.findByNome("João")).thenReturn(new Adotante());

        // Act & Assert
        assertThatThrownBy(() -> adotanteService.cadastrarAdotante(cadastroDTO))
                .isInstanceOf(Exception.class)
                .hasMessage("Adotante já cadastrado");

        verify(adotanteRepository, never()).save(any(Adotante.class));
    }

    @Test
    @DisplayName("Deve lançar exceção genérica quando ViaCep falha")
    void deveLancarExcecao_QuandoViaCepFalha() {
        // Arrange
        EnderecoDTO enderecoDTO = new EnderecoDTO("00000-000", "1");
        AdotanteCadastroDTO cadastroDTO = new AdotanteCadastroDTO("Pedro", "pedro@email.com", "111222333", enderecoDTO);

        when(adotanteRepository.findByNome("Pedro")).thenReturn(null);
        when(enderecoRepository.findByCepAndNumero("00000-000", "1")).thenReturn(Optional.empty());
        when(viaCepClient.buscarEnderecoPorCep("00000-000")).thenThrow(new RuntimeException("API Indisponível"));

        // Act & Assert
        assertThatThrownBy(() -> adotanteService.cadastrarAdotante(cadastroDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("API Indisponível");

        verify(adotanteRepository, never()).save(any(Adotante.class));
    }

    @Test
    @DisplayName("Deve deletar adotante com sucesso")
    void deveDeletarAdotante_ComSucesso() {
        // Arrange
        Integer id = 1;
        Adotante adotante = new Adotante();
        adotante.setId(id);

        when(adotanteRepository.findById(id)).thenReturn(Optional.of(adotante));

        // Act
        adotanteService.deletarAdotante(id);

        // Assert
        verify(adotanteRepository).delete(adotante);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar adotante inexistente")
    void deveLancarExcecao_QuandoDeletarAdotanteInexistente() {
        // Arrange
        Integer id = 99;
        when(adotanteRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> adotanteService.deletarAdotante(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Adotante não encontrado com o ID: " + id);

        verify(adotanteRepository, never()).delete(any(Adotante.class));
    }
}
