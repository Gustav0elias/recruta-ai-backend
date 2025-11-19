package com.ifba.projeto.tcc.application.usecase.candidato;

import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarCurriculosPorCandidatoUseCase {
Page<CurriculoResponseDTO> executar(Pageable pageable, Long candidatoId);
}
