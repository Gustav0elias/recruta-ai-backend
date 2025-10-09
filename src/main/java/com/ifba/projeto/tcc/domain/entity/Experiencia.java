package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "empresa", nullable = false, length = 50)
    private String empresa;
    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;
    @Column(name = "descricao", length = 100)
    private String descricao;
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    @Column(name = "data_fim")
    private LocalDate dataFim;
    @ManyToOne
    @JoinColumn(name = "id_candidato")
    private Candidato candidato;
}
