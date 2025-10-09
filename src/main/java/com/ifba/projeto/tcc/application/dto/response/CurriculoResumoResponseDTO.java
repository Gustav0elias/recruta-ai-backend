package com.ifba.projeto.tcc.application.dto.response;

import java.util.UUID;

public record CurriculoResumoResponseDTO(
        UUID uuid,
        String caminhoArquivo,
        Boolean ativo
) {
}
