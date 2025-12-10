package br.com.abrigo.sistema_cadastro_pets.controller;

import br.com.abrigo.sistema_cadastro_pets.dto.PetCadastroDTO;
import br.com.abrigo.sistema_cadastro_pets.model.Pet;
import br.com.abrigo.sistema_cadastro_pets.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody PetCadastroDTO petCadastroDTO) {
        try {
            var result = this.petService.cadastrarPet(petCadastroDTO);
            return ResponseEntity.created(null).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
