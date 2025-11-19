package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
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

    @OneToMany(mappedBy = "habilidade")
    private Set<VagaHabilidade> vagaHabilidades = new HashSet<>();

    @ManyToMany(mappedBy = "habilidades")
    private Set<Candidato> candidatos = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
public Habilidade(){}

    public Habilidade(Long id, UUID uuid, String nome, String descricao, TipoHabilidade tipo, Set<VagaHabilidade> vagaHabilidades, Set<Candidato> candidatos) {
        this.id = id;
        this.uuid = uuid;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.vagaHabilidades = vagaHabilidades;
        this.candidatos = candidatos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoHabilidade getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabilidade tipo) {
        this.tipo = tipo;
    }

    public Set<VagaHabilidade> getVagaHabilidades() {
        return vagaHabilidades;
    }

    public void setVagaHabilidades(Set<VagaHabilidade> vagaHabilidades) {
        this.vagaHabilidades = vagaHabilidades;
    }

    public Set<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(Set<Candidato> candidatos) {
        this.candidatos = candidatos;
    }
}
