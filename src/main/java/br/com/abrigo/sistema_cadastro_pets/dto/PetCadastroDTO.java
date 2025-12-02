package br.com.abrigo.sistema_cadastro_pets.dto;

import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetCadastroDTO {

    @NotBlank(message = "O nome do Pet é obrigatório.")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O campo deve conter apenas letras e espaços, sem caracteres especiais.")
    private String nome;

    @DecimalMax(value = "20", message = "A idade deve ser menor que 20 anos")
    private double idade;

    @NotNull(message = "O sexo do Pet é obrigatório.")
    private String sexoPet;

    @NotNull(message = "O tipo do Pet é obrigatório.")
    private String tipoPet;

    @DecimalMin(value = "0.5", message = "O peso deve ser maior que 0.5kg")
    @DecimalMax(value = "60", message = "O peso deve ser menor que 60kg")
    private double peso;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O campo deve conter apenas letras e espaços, sem caracteres especiais.")
    private String raca;

    @Valid
    @NotNull
    @Embedded
    private EnderecoDTO endereco;

}
