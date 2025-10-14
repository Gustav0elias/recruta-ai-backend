package com.ifba.projeto.tcc.application.usecase.candidato;

import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarCandidatosGeralUseCase {
    Page<CandidatoResponseDTO> executar(Pageable pageable);
}
