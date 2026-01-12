package br.com.abrigo.sistema_cadastro_pets.service;

import br.com.abrigo.sistema_cadastro_pets.dto.AdotanteCadastroDTO;
import br.com.abrigo.sistema_cadastro_pets.dto.EnderecoDTO;
import br.com.abrigo.sistema_cadastro_pets.model.Adotante;
import br.com.abrigo.sistema_cadastro_pets.model.Endereco;
import br.com.abrigo.sistema_cadastro_pets.repository.AdotanteRepository;
import br.com.abrigo.sistema_cadastro_pets.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdotanteService {

    @Autowired
    private AdotanteRepository adotanteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Adotante cadastrarAdotante(AdotanteCadastroDTO adotanteCadastroDTO) throws Exception {
        adotanteExiste(adotanteCadastroDTO.getNome());
        Endereco endereco = enderecoExiste(adotanteCadastroDTO.getEndereco());
        Adotante adotante = adotanteMapper(adotanteCadastroDTO);
        adotante.setEndereco(endereco);
        return adotanteRepository.save(adotante);
    }

    private Adotante adotanteExiste(String nome) throws Exception {
        Adotante exists = adotanteRepository.findByNome(nome);
        if (exists != null) {
            throw new Exception("Adotante já cadastrado");
        }
        return exists;
    }

    private Endereco enderecoExiste(EnderecoDTO enderecoDTO) {
        return enderecoRepository
                .findByCepAndNumero(enderecoDTO.getCep(), enderecoDTO.getNumero())
                .orElseGet(() -> {
                    Endereco novoEndereco = enderecoMapper(enderecoDTO);
                    return enderecoRepository.save(novoEndereco);
                });
    }

    private Endereco enderecoMapper(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setCep(enderecoDTO.getCep());
        endereco.setLogradouro(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setUf(enderecoDTO.getEstado());
        return endereco;
    }

    private Adotante adotanteMapper(AdotanteCadastroDTO adotanteCadastroDTO) {
        Adotante adotante = new Adotante();
        adotante.setNome(adotanteCadastroDTO.getNome());
        adotante.setEmail(adotanteCadastroDTO.getEmail());
        adotante.setTelefone(adotanteCadastroDTO.getTelefone());
        return adotante;
    }

    public void deletarAdotante(Integer id) {
        Adotante adotanteEncontrado = adotanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adotante não encontrado com o ID: " + id));
        adotanteRepository.delete(adotanteEncontrado);
    }
}
