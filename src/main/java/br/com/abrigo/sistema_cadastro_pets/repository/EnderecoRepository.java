package br.com.abrigo.sistema_cadastro_pets.repository;

import br.com.abrigo.sistema_cadastro_pets.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository <Endereco, Integer> {
}
