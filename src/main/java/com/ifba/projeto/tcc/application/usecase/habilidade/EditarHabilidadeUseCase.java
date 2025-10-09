package com.ifba.projeto.tcc.application.usecase.habilidade;

import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Habilidade;

public interface EditarHabilidadeUseCase {
    HabilidadeResponseDTO executar(HabilidadeRequestDTO habilidade, Long id);
}
