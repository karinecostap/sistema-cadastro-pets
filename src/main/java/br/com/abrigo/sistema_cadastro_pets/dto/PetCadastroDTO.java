package br.com.abrigo.sistema_cadastro_pets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetCadastroDTO {
    private String nomePet;
    private double idade;
    private String sexoPet;
    private String tipoPet;
    private double peso;
    private String raca;
    private EnderecoDTO endereco;

}
