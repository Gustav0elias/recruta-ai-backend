package com.ifba.projeto.tcc.application.usecase.habilidade;

import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarHabilidadesUseCase {
    Page<HabilidadeResponseDTO> executar ( Pageable pageable);
}
