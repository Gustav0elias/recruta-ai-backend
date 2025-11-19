package com.ifba.projeto.tcc.domain.repository.projection;

import com.ifba.projeto.tcc.domain.entity.NivelExperiencia;

import java.time.LocalDateTime;
import java.util.UUID;

public interface VagaEstatisticaProjection {
    Long getId();
    UUID getUuid();
    String getTitulo();
    NivelExperiencia getNivelExperiencia();
    LocalDateTime getCriadoEm();
    Long getQuantidadeCandidaturas();
    Double getMediaScore();
}

