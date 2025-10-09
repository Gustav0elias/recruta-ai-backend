package com.ifba.projeto.tcc.application.dto.response;

import java.util.UUID;

public record HabilidadeResumoResponseDTO(
        UUID uuid,
        String nome,
        String tipo

) {
}
