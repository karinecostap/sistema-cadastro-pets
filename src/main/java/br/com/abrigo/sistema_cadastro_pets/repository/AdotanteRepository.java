package br.com.abrigo.sistema_cadastro_pets.repository;

import br.com.abrigo.sistema_cadastro_pets.model.Adotante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdotanteRepository extends JpaRepository<Adotante, String> {
    Optional<Adotante> findById(Integer id);
    Adotante findByNome(String nome);
}