package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @Column(name = "descricao", nullable = false, length = 2000)
    private String descricao;
    @Column(name = "nivel_experiencia", nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelExperiencia nivelExperiencia;
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidatura> candidaturas = new ArrayList<>();

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VagaHabilidade> vagaHabilidades = new HashSet<>();
    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }

    }
    public void adicionarHabilidade(Habilidade habilidade, int peso) {
        VagaHabilidade vh = new VagaHabilidade();
        vh.setVaga(this);
        vh.setHabilidade(habilidade);
        vh.setPeso(peso);
        this.vagaHabilidades.add(vh);
    }

    public Vaga() {

    }
    public Vaga(Long id, UUID uuid, String titulo, String descricao, NivelExperiencia nivelExperiencia, LocalDateTime criadoEm, Usuario usuario, List<Candidatura> candidaturas, Set<VagaHabilidade> vagaHabilidades) {
        this.id = id;
        this.uuid = uuid;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nivelExperiencia = nivelExperiencia;
        this.criadoEm = criadoEm;
        this.usuario = usuario;
        this.candidaturas = candidaturas;
        this.vagaHabilidades = vagaHabilidades;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public NivelExperiencia getNivelExperiencia() {
        return nivelExperiencia;
    }

    public void setNivelExperiencia(NivelExperiencia nivelExperiencia) {
        this.nivelExperiencia = nivelExperiencia;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public void setCandidaturas(List<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }

    public Set<VagaHabilidade> getVagaHabilidades() {
        return vagaHabilidades;
    }

    public void setVagaHabilidades(Set<VagaHabilidade> vagaHabilidades) {
        this.vagaHabilidades = vagaHabilidades;
    }
}
