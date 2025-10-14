package com.ifba.projeto.tcc.application.usecase.candidatura;

import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidaturaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarCandidaturasUseCase {
    Page<CandidaturaResponseDTO> executar(Long vagaId, Pageable pageable);
}
