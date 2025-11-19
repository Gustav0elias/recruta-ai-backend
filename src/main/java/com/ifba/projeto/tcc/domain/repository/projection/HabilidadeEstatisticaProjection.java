package com.ifba.projeto.tcc.domain.repository.projection;

import com.ifba.projeto.tcc.domain.entity.TipoHabilidade;

import java.util.UUID;

public interface HabilidadeEstatisticaProjection {
    UUID getUuid();
    String getNome();
    TipoHabilidade getTipo();
    Long getQuantidadeVagas();
    Long getQuantidadeCandidatos();
}

