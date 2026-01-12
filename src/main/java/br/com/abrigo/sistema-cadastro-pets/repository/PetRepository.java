package br.com.abrigo.sistema_cadastro_pets.repository;

import br.com.abrigo.sistema_cadastro_pets.model.Pet;
import br.com.abrigo.sistema_cadastro_pets.model.SexoPet;
import br.com.abrigo.sistema_cadastro_pets.model.TipoPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{

    List<Pet> findByNomeContainingIgnoreCase(String nome);
    Pet findByNome(String nome);

    @Query("SELECT p FROM Pet p WHERE " +
            "(:nome IS NULL OR p.nome = :nome) AND " +
            "(:idade IS NULL OR p.idade = :idade) AND " +
            "(:peso IS NULL OR p.peso = :peso) AND " +
            "(:raca IS NULL OR p.raca = :raca) AND " +
            "(:sexo IS NULL OR p.sexoPet = :sexo) AND " +
            "(:tipo IS NULL OR p.tipoPet = :tipo)")
    List<Pet> buscarComFiltros(
            @Param("nome") String nome,
            @Param("idade") Double idade,
            @Param("peso") Double peso,
            @Param("raca") String raca,
            @Param("sexo") SexoPet sexo,
            @Param("tipo") TipoPet tipo
    );
}
