package com.ifba.projeto.tcc.application.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CandidaturaRequestDTO(
        @NotNull UUID vagaUuid,
        @NotNull UUID curriculoUuid

) {
}
