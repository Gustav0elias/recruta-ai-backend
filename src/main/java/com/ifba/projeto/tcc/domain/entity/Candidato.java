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
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "telefone", nullable = false, length = 12)
    private String telefone;
    @Column(name = "linkedin", nullable = false, length = 50)
    @ManyToMany()
    @JoinTable(name = "candidato_habilidade", joinColumns = @JoinColumn(name = "id_candidato"), inverseJoinColumns = @JoinColumn(name="id_habilidade"))
    private Set<Habilidade> habilidades = new HashSet<>();
    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private List<Experiencia> experiencias = new ArrayList<>();
    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private List<Curriculo> curriculos = new ArrayList<>();
    @Column(name="linkedin")
    private String linkedin;
    @Column(name = "criado_em" )
    private LocalDateTime criadoEm;
}
