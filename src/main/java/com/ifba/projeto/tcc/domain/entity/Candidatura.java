package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Candidatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private LocalDateTime dataCadastro;
    @ManyToOne()
    @JoinColumn(name = "id_vaga")
    private Vaga vaga;
    @ManyToOne
    @JoinColumn(name = "id_curriculo")
    private Curriculo curriculo;
    private Long score;
    private String justificativaScore;

    public Candidatura(Long id, UUID uuid, LocalDateTime dataCadastro, Vaga vaga, Curriculo curriculo, Long score, String justificativaScore) {
        this.id = id;
        this.uuid = uuid;
        this.dataCadastro = dataCadastro;
        this.vaga = vaga;
        this.curriculo = curriculo;
        this.score = score;
        this.justificativaScore = justificativaScore;
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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getJustificativaScore() {
        return justificativaScore;
    }

    public void setJustificativaScore(String justificativaScore) {
        this.justificativaScore = justificativaScore;
    }
}
