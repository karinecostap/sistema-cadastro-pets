package br.com.abrigo.sistema_cadastro_pets.controller;

import br.com.abrigo.sistema_cadastro_pets.dto.AdotanteCadastroDTO;
import br.com.abrigo.sistema_cadastro_pets.service.AdotanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adotantes")
public class AdotanteController {
    @Autowired
    private AdotanteService adotanteService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody AdotanteCadastroDTO adotanteCadastroDTO) {
        try {
            var result = this.adotanteService.cadastrarAdotante(adotanteCadastroDTO);
            return ResponseEntity.created(null).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        adotanteService.deletarAdotante(id);
        return ResponseEntity.noContent().build();
    }
}
