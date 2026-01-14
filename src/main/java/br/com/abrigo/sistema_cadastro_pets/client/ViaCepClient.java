package br.com.abrigo.sistema_cadastro_pets.client;

import br.com.abrigo.sistema_cadastro_pets.dto.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    ViaCepDTO buscarEnderecoPorCep(@PathVariable("cep") String cep);
}
