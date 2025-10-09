package com.ifba.projeto.tcc.application.dto.response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record CandidatoResponseDTO(
        UUID uuid,
        String nome,
        String email,
        String telefone,
        String linkedin,
        LocalDateTime criadoEm,
        Set<HabilidadeResumoResponseDTO> habilidades,
        Set<CurriculoResumoResponseDTO> curriculos
) {
}
