package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private LocalDateTime dataCadastro;
    @ManyToOne()
    @JoinColumn(name = "id_vaga")
    private Vaga vaga;
    @ManyToOne
    @JoinColumn(name = "id_curriculo")
    private Curriculo curriculo;
    private Long score;
}
