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
public class Curriculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "caminho_arquivo", nullable = false)
    private String caminhoArquivo;
    private Boolean ativo;
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    @ManyToOne
    @JoinColumn(name = "id_candidato")
    private Candidato candidato;
}
