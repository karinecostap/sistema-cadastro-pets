package br.com.abrigo.sistema_cadastro_pets.dto;

import br.com.abrigo.sistema_cadastro_pets.model.SexoPet;
import br.com.abrigo.sistema_cadastro_pets.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
    private SexoPet sexoPet;

    @NotNull(message = "O tipo do Pet é obrigatório.")
    private TipoPet tipoPet;

    @DecimalMin(value = "0.5", message = "O peso deve ser maior que 0.5kg")
    @DecimalMax(value = "60", message = "O peso deve ser menor que 60kg")
    private double peso;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O campo deve conter apenas letras e espaços, sem caracteres especiais.")
    private String raca;
}
