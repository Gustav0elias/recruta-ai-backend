package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.xmlbeans.impl.store.Cur;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
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
    @OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL)
    private List<Analise> analises = new ArrayList<>();
    @OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL)
    private List<Candidatura> candidaturas = new ArrayList<>();

    public Curriculo(Long id, UUID uuid, String caminhoArquivo, Boolean ativo, LocalDateTime criadoEm, Candidato candidato, List<Analise> analises) {
        this.id = id;
        this.uuid = uuid;
        this.caminhoArquivo = caminhoArquivo;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
        this.candidato = candidato;
        this.analises = analises;
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

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public List<Analise> getAnalises() {
        return analises;
    }

    public void setAnalises(List<Analise> analises) {
        this.analises = analises;
    }

    public List<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public void setCandidaturas(List<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }
}
