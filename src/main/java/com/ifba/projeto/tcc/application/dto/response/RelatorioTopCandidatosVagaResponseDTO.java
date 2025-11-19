package com.ifba.projeto.tcc.application.dto.response;

import java.util.List;

public record RelatorioTopCandidatosVagaResponseDTO(
        Long vagaId,
        String tituloVaga,
        List<CandidatoScoreResponseDTO> candidatos
) {}

