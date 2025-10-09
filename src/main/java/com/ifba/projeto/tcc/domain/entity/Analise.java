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
public class Analise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "data_analise")
    private LocalDateTime dataAnalise;
    @Column(name = "tipo_analise", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAnalise tipoAnalise;
    @ManyToOne()
    @JoinColumn(name = "id_curriculo")
    private Curriculo curriculo;
}
