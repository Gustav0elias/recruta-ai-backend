package com.ifba.projeto.tcc.application.dto.response;

import java.util.List;

public record CurriculoExtraidoResponseDTO(
        String nome,
        String email,
        String telefone,
        List<HabilidadeParaCurriculoResponseDTO> habilidades,
        List<ExperienciaResponseDTO> experiencias
) {
}
