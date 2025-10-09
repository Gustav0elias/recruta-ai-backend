package com.ifba.projeto.tcc.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

public record CandidatoRequestDTO(
        @NotBlank @Size(max = 50)
        String nome,

        @NotBlank @Email @Size(max = 50)
        String email,

        @NotBlank @Size(max = 12)
        String telefone,

        @NotBlank @Size(max = 50)
        String linkedin,

        Set<UUID> habilidadesUuids
) {
}
