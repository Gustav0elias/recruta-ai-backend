package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity

public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "telefone", nullable = true, length = 20)
    private String telefone;

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

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }

    public Candidato() {
    }

    public Candidato(Long id, UUID uuid, String nome, String email, String telefone, Set<Habilidade> habilidades, List<Experiencia> experiencias, List<Curriculo> curriculos, String linkedin, LocalDateTime criadoEm) {
        this.id = id;
        this.uuid = uuid;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.habilidades = habilidades;
        this.experiencias = experiencias;
        this.curriculos = curriculos;
        this.linkedin = linkedin;
        this.criadoEm = criadoEm;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public List<Curriculo> getCurriculos() {
        return curriculos;
    }

    public void setCurriculos(List<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
