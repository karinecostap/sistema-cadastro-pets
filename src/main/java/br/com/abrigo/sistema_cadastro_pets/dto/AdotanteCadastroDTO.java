package br.com.abrigo.sistema_cadastro_pets.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdotanteCadastroDTO {
    @NotBlank
    String nome;

    @NotBlank
    @Email
    String email;

    @NotBlank
    String telefone;

    @Valid
    EnderecoDTO endereco;
}
