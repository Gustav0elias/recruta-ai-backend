package com.ifba.projeto.tcc.application.dto.request;

import com.ifba.projeto.tcc.domain.entity.TipoHabilidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HabilidadeRequestDTO(
        @NotBlank @Size(max = 50) String nome,
        @NotBlank @Size(max = 50) String descricao,
        @NotNull TipoHabilidade tipo
) {
}
