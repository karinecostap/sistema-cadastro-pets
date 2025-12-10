package br.com.abrigo.sistema_cadastro_pets.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private double idade;

    @Enumerated(EnumType.STRING)
    private SexoPet sexoPet;

    @Enumerated(EnumType.STRING)
    private TipoPet tipoPet;

    private double peso;
    private String raca;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
