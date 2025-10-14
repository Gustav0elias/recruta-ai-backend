package com.ifba.projeto.tcc.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CandidatoCurriculoResponseDTO(
        UUID uuid,
        String nome,
        String email,
        String telefone,
        String linkedin,
        LocalDateTime criadoEm,

        List<ExperienciaResponseDTO> experiencias
     ) {
}
