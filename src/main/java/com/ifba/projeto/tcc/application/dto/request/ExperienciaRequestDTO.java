package com.ifba.projeto.tcc.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record ExperienciaRequestDTO(
        @NotBlank @Size(max = 50) String empresa,
        @NotBlank @Size(max = 50) String cargo,
        @Size(max = 100) String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        @NotNull UUID candidatoUuid
) {
}
