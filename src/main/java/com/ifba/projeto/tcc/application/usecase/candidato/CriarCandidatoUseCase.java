package com.ifba.projeto.tcc.application.usecase.candidato;

import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Candidato;

public interface CriarCandidatoUseCase {
    Candidato executar (CurriculoExtraidoResponseDTO dto);
}
