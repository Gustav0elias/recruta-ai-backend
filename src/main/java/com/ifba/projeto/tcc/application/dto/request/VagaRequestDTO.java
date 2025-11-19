package com.ifba.projeto.tcc.application.dto.request;

import com.ifba.projeto.tcc.domain.entity.NivelExperiencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;
import java.util.UUID;


public record VagaRequestDTO(
        @NotBlank(message = "O título da vaga é obrigatório")
        @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
        String titulo,

        @NotBlank(message = "A descrição da vaga é obrigatória")
        @Size(max = 300, message = "A descrição deve ter no máximo 300 caracteres")
        String descricao,

        @NotNull(message = "O nível de experiência é obrigatório")
        NivelExperiencia nivelExperiencia,

        @NotEmpty(message = "A vaga deve ter pelo menos uma habilidade vinculada")
        List<HabilidadeComPesoRequestDTO> habilidades
) {}