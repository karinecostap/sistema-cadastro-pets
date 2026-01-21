package br.com.abrigo.sistema_cadastro_pets.service;

import br.com.abrigo.sistema_cadastro_pets.dto.PetCadastroDTO;
import br.com.abrigo.sistema_cadastro_pets.model.Pet;
import br.com.abrigo.sistema_cadastro_pets.model.SexoPet;
import br.com.abrigo.sistema_cadastro_pets.model.TipoPet;
import br.com.abrigo.sistema_cadastro_pets.repository.PetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    @DisplayName("Deve cadastrar pet com sucesso")
    void deveCadastrarPet_ComSucesso() {

        PetCadastroDTO dto = new PetCadastroDTO("Rex", 5.0, SexoPet.MACHO, TipoPet.CAO, 10.0, "SRD");
        Pet petSalvo = new Pet();
        petSalvo.setId(1);
        petSalvo.setNome("rex");

        when(petRepository.findByNome("rex")).thenReturn(null);
        when(petRepository.save(any(Pet.class))).thenReturn(petSalvo);

        Pet resultado = petService.cadastrarPet(dto);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("rex");
        verify(petRepository).save(any(Pet.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando pet já existe")
    void deveLancarExcecao_QuandoPetJaExiste() {

        PetCadastroDTO dto = new PetCadastroDTO("Rex", 5.0, SexoPet.MACHO, TipoPet.CAO, 10.0, "SRD");
        Pet petExistente = new Pet();
        petExistente.setNome("rex");

        when(petRepository.findByNome("rex")).thenReturn(petExistente);
        assertThatThrownBy(() -> petService.cadastrarPet(dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Pet já cadastrado");

        verify(petRepository, never()).save(any(Pet.class));
    }

    @Test
    @DisplayName("Deve buscar pet por ID com sucesso")
    void deveBuscarPetPorId_ComSucesso() {
        // Arrange
        Pet pet = new Pet();
        pet.setId(1);
        when(petRepository.findById(1)).thenReturn(Optional.of(pet));

        // Act
        Pet resultado = petService.buscarPetPorId(1);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar pet por ID inexistente")
    void deveLancarExcecao_QuandoBuscarPetIdInexistente() {
        // Arrange
        when(petRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> petService.buscarPetPorId(99))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("Deve buscar pets pelo nome")
    void deveBuscarPetsPeloNome() {
        // Arrange
        Pet pet = new Pet();
        pet.setNome("rex");
        when(petRepository.findByNomeContainingIgnoreCase("Rex")).thenReturn(List.of(pet));

        // Act
        List<Pet> resultados = petService.buscarPetPeloNome("Rex");

        // Assert
        assertThat(resultados).hasSize(1);
        assertThat(resultados.get(0).getNome()).isEqualTo("rex");
    }

    @Test
    @DisplayName("Deve listar todos os pets")
    void deveListarTodosPets() {
        // Arrange
        when(petRepository.findAll()).thenReturn(List.of(new Pet(), new Pet()));

        // Act
        List<Pet> resultados = petService.listarTodosPets();

        // Assert
        assertThat(resultados).hasSize(2);
    }

    @Test
    @DisplayName("Deve filtrar pets com parâmetros")
    void deveFiltrarPets() {
        // Arrange
        String nome = "Rex";
        Double idade = 5.0;
        Double peso = 10.0;
        String raca = "SRD";
        SexoPet sexo = SexoPet.MACHO;
        TipoPet tipo = TipoPet.CAO;

        when(petRepository.buscarComFiltros(nome, idade, peso, raca, sexo, tipo))
                .thenReturn(List.of(new Pet()));

        // Act
        List<Pet> resultados = petService.filtrarPets(nome, idade, peso, raca, sexo, tipo);

        // Assert
        assertThat(resultados).isNotEmpty();
    }

    @Test
    @DisplayName("Deve atualizar pet com sucesso")
    void deveAtualizarPet_ComSucesso() {
        // Arrange
        PetCadastroDTO dto = new PetCadastroDTO("Rex", 5.0, SexoPet.MACHO, TipoPet.CAO, 10.0, "SRD");
        Pet petAtualizado = new Pet();
        petAtualizado.setIdade(6.0);

        when(petRepository.save(any(Pet.class))).thenReturn(petAtualizado);

        // Act
        Pet resultado = petService.atualizarPet(dto);

        // Assert
        assertThat(resultado.getIdade()).isEqualTo(6.0);
    }

    @Test
    @DisplayName("Deve deletar pet com sucesso")
    void deveDeletarPet_ComSucesso() {
        // Arrange
        Pet pet = new Pet();
        pet.setId(1);
        when(petRepository.findById(1)).thenReturn(Optional.of(pet));

        // Act
        petService.deletarPet(1);

        // Assert
        verify(petRepository).delete(pet);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar pet inexistente")
    void deveLancarExcecao_AoDeletarPetInexistente() {
        // Arrange
        when(petRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> petService.deletarPet(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Pet não encontrado com ID: 99");
    }
}
