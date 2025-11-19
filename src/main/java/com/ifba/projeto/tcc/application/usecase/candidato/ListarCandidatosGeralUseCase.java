package com.ifba.projeto.tcc.application.usecase.candidato;

import com.ifba.projeto.tcc.application.dto.response.CandidatoRetornoCurriculoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarCandidatosGeralUseCase {
    Page<CandidatoRetornoCurriculoResponseDTO> executar(Pageable pageable);

    
}
