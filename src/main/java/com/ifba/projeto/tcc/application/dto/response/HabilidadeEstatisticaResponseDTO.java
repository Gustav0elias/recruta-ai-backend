package com.ifba.projeto.tcc.application.dto.response;

import java.util.UUID;

public record HabilidadeEstatisticaResponseDTO(
        UUID uuid,
        String nome,
        String tipo,
        Long quantidadeVagas,
        Long quantidadeCandidatos
) {}

