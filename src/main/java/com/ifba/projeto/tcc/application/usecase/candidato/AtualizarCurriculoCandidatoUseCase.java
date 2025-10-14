package com.ifba.projeto.tcc.application.usecase.candidato;

import com.ifba.projeto.tcc.application.dto.response.CandidatoResumoResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface AtualizarCurriculoCandidatoUseCase {
    CandidatoResumoResponseDTO executar (Long candidatoId, MultipartFile arquivo);
}
