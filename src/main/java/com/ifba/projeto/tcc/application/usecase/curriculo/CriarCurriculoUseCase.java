package com.ifba.projeto.tcc.application.usecase.curriculo;

import com.ifba.projeto.tcc.application.dto.request.CurriculoRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;

public interface CriarCurriculoUseCase {
    CurriculoResponseDTO executar(CurriculoRequestDTO dto, Long idVaga);
}
