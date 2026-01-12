package br.com.abrigo.sistema_cadastro_pets.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

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
