package br.com.abrigo.sistema_cadastro_pets.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

}
