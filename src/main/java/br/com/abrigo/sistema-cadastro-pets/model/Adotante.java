package br.com.abrigo.sistema_cadastro_pets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "adotante")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adotante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
