package com.ifba.projeto.tcc.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record CandidaturaDetalharResponseDTO(
        Long id,
        UUID uuid,
        String nome,
        String email,
        String telefone,
        String linkedin,
        LocalDateTime criadoEm,
        Set<HabilidadeResumoResponseDTO> habilidades,
        List<ExperienciaResponseDTO>  experiencias,
        Set<CurriculoResumoResponseDTO> curriculos,
        ScoreResponseDTO scoreResponseDTO
) {
}
