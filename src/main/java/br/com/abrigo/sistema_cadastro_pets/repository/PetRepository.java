package br.com.abrigo.sistema_cadastro_pets.repository;

import br.com.abrigo.sistema_cadastro_pets.model.Pet;
import br.com.abrigo.sistema_cadastro_pets.model.SexoPet;
import br.com.abrigo.sistema_cadastro_pets.model.TipoPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    List<Pet> findByNomeContainingIgnoreCase(String nome);
    List<Pet> findByRacaContainingIgnoreCase(String raca);
    List<Pet> findByPeso(double peso);
    List<Pet> findByIdade(double idade);
    List<Pet> findBySexoPet(SexoPet sexoPet);
    List<Pet> findByTipoPet(TipoPet tipoPet);
    List<Pet> findByNomeContainingIgnoreCaseAndIdade(String nome, double idade);
    List<Pet> findByIdadeAndPeso(double idade, double peso);

}
