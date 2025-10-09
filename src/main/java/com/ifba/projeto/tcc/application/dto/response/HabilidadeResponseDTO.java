package com.ifba.projeto.tcc.application.dto.response;

import com.ifba.projeto.tcc.domain.entity.TipoHabilidade;

import java.util.UUID;

public record HabilidadeResponseDTO(
        Long id,
        UUID uuid,
        String nome,
        String descricao,
        TipoHabilidade tipo
) {
}
