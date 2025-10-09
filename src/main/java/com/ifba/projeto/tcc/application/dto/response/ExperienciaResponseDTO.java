package com.ifba.projeto.tcc.application.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record ExperienciaResponseDTO(
        UUID uuid,
        String empresa,
        String cargo,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        CandidatoResumoResponseDTO candidato
) {
}
