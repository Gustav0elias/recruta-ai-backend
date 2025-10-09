package com.ifba.projeto.tcc.application.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record AdicionarHabilidadesRequestDTO(
        @NotEmpty(message = "Deve haver pelo menos uma habilidade para adicionar")
        Set<UUID> habilidadesUuids
) {}