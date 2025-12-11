package br.com.abrigo.sistema_cadastro_pets.controller;

import br.com.abrigo.sistema_cadastro_pets.dto.PetCadastroDTO;
import br.com.abrigo.sistema_cadastro_pets.model.Pet;
import br.com.abrigo.sistema_cadastro_pets.service.PetService;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId( @PathVariable Integer id) {
        try{
            var result = this.petService.buscarPetPorId(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object>getNome(@PathVariable String nome) {
        try {
            var result = this.petService.buscarPetPeloNome(nome);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("/all{pets}")
    public ResponseEntity<Object>getAll(@PathVariable List<Pet> pets){
        try {
            var result = this.petService.listarTodosPets(pets);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public  ResponseEntity<Object>update(@Valid @RequestBody PetCadastroDTO petCadastroDTO) {
        try {
            var result = this.petService.atualizarPet(petCadastroDTO);
            return ResponseEntity.ok().body(result);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
