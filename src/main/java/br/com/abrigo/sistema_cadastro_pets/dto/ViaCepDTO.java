package br.com.abrigo.sistema_cadastro_pets.dto;

public record ViaCepDTO (
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf
) {}