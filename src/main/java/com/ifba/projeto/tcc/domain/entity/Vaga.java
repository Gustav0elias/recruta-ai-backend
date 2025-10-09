package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @Column(name = "descricao", nullable = false, length = 300)
    private String descricao;
    @Column(name = "nivel_experiencia", nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelExperiencia nivelExperiencia;
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @OneToMany(mappedBy = "vaga")
    private List<Candidatura> candidaturas = new ArrayList<>();
    @ManyToMany()
    @JoinTable(name = "vaga_habilidade", joinColumns = @JoinColumn(name = "id_vaga"), inverseJoinColumns = @JoinColumn(name = "id_habilidade"))
    private Set<Habilidade> habilidades = new HashSet<>();

}
