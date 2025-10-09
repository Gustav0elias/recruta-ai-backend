package com.ifba.projeto.tcc.application.dto.request;

import com.ifba.projeto.tcc.domain.entity.TipoAnalise;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AnaliseRequestDTO(
        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotNull(message = "O tipo de análise é obrigatório")
        TipoAnalise tipoAnalise,

        @NotNull(message = "O currículo vinculado é obrigatório")
        UUID curriculoUuid
) {
}
