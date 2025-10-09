package com.ifba.projeto.tcc.application.dto.response;

import com.ifba.projeto.tcc.domain.entity.NivelExperiencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


public record VagaResponseDTO(
        UUID uuid,
        String titulo,
        String descricao,
        NivelExperiencia nivelExperiencia,
        LocalDateTime criadoEm,
        Set<HabilidadeResumoResponseDTO> habilidades
) {}