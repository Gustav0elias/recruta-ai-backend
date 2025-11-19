package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VagaHabilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vaga")
    private Vaga vaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habilidade")
    private Habilidade habilidade;

    private int peso; // 0 a 10
}

