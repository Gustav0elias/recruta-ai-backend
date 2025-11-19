package com.ifba.projeto.tcc.application.dto.response;

public record DistribuicaoScoreResponseDTO(
        Long faixa0a20,
        Long faixa21a40,
        Long faixa41a60,
        Long faixa61a80,
        Long faixa81a100
) {}

