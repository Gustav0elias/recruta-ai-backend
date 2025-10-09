package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Habilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;
    @Column(name = "tipo_habilidade",nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoHabilidade tipo;
    @ManyToMany(mappedBy = "habilidades")
    private Set<Vaga> vagas  = new HashSet<>();
    @ManyToMany(mappedBy = "habilidades")
    private Set<Candidato> candidatos = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
