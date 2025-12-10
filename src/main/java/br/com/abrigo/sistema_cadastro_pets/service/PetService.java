package br.com.abrigo.sistema_cadastro_pets.service;

import br.com.abrigo.sistema_cadastro_pets.dto.PetCadastroDTO;
import br.com.abrigo.sistema_cadastro_pets.model.Pet;
import br.com.abrigo.sistema_cadastro_pets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet cadastrarPet(PetCadastroDTO petCadastroDTO){
        Pet pet = petMapper(petCadastroDTO);
        return petRepository.save(pet);
    }
    private Pet petMapper (PetCadastroDTO petCadastroDTO){
        Pet pet = new Pet();
        pet.setNome(petCadastroDTO.getNome());
        pet.setIdade(petCadastroDTO.getIdade());
        pet.setSexoPet(petCadastroDTO.getSexoPet());
        pet.setTipoPet(petCadastroDTO.getTipoPet());
        pet.setPeso(petCadastroDTO.getPeso());
        pet.setRaca(petCadastroDTO.getRaca());
        return pet;
    }
}
