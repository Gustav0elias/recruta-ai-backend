package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @Column(name ="email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "senha", nullable = false, length = 200)
    private String senha;
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Vaga> vagas = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        if (criadoEm == null) {
            criadoEm = LocalDateTime.now();
        }
    }
}
