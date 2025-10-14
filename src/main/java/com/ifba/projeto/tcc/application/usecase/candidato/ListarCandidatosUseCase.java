package com.ifba.projeto.tcc.application.usecase.candidato;

import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarCandidatosUseCase {
    Page<CandidatoResponseDTO> executar(Long vagaId, Pageable pageable);
}
