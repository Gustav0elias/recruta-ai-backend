package com.ifba.projeto.tcc.application.dto.response;

import com.ifba.projeto.tcc.domain.entity.TipoAnalise;

import java.time.LocalDateTime;
import java.util.UUID;

public record AnaliseResponseDTO(
        UUID uuid,
        String descricao,
        TipoAnalise tipoAnalise,
        LocalDateTime dataAnalise,
        CurriculoResumoResponseDTO curriculo
) {
}
