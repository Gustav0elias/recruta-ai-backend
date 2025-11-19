package com.ifba.projeto.tcc.application.dto.response;

import java.util.List;

public record DistribuicaoScoreVagaResponseDTO(
        Long vagaId,
        String tituloVaga,
        List<FaixaScoreResponseDTO> distribuicao
) {}

