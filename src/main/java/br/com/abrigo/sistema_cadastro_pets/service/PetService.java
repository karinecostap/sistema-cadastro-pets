package br.com.abrigo.sistema_cadastro_pets.service;

import br.com.abrigo.sistema_cadastro_pets.dto.PetCadastroDTO;
import br.com.abrigo.sistema_cadastro_pets.model.Pet;
import br.com.abrigo.sistema_cadastro_pets.model.SexoPet;
import br.com.abrigo.sistema_cadastro_pets.model.TipoPet;
import br.com.abrigo.sistema_cadastro_pets.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet cadastrarPet(PetCadastroDTO petCadastroDTO) {
        log.info("Iniciando cadastro de pet");
        try {
            String nomeFormatado = petCadastroDTO.getNome().toLowerCase();
            Pet exists = this.petRepository.findByNome(nomeFormatado);
            if (exists != null) {
                throw new RuntimeException("Pet já cadastrado");
            }
            Pet pet = petMapper(petCadastroDTO);
            return petRepository.save(pet);
        } catch (Exception e) {
            log.error("Erro ao cadastrar novo pet");
            throw e;
        }
    }

    private Pet petMapper(PetCadastroDTO petCadastroDTO) {
        Pet pet = new Pet();
        pet.setNome(petCadastroDTO.getNome().toLowerCase());
        pet.setIdade(petCadastroDTO.getIdade());
        pet.setSexoPet(petCadastroDTO.getSexoPet());
        pet.setTipoPet(petCadastroDTO.getTipoPet());
        pet.setPeso(petCadastroDTO.getPeso());
        pet.setRaca(petCadastroDTO.getRaca().toLowerCase());
        return pet;
    }

    public Pet buscarPetPorId(Integer id) {
        return petRepository.findById(id).get();
    }

    public List<Pet> buscarPetPeloNome(String nome) {
        return petRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Pet> listarTodosPets() {
        return petRepository.findAll();
    }

    public Pet atualizarPet(PetCadastroDTO petCadastroDTO) {
        Pet pet = petMapper(petCadastroDTO);
        return petRepository.save(pet);
    }

    public List<Pet> filtrarPets(String nome, Double idade, Double peso, String raca, SexoPet sexo, TipoPet tipo) {

        return petRepository.buscarComFiltros(nome, idade, peso, raca, sexo, tipo);
    }

    public void deletarPet(Integer id){
        Pet petEncontrado = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pet não encontrado com ID: " + id));
        petRepository.delete(petEncontrado);
    }
}