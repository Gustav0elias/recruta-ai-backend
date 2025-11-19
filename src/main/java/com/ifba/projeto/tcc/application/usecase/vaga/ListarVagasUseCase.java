package com.ifba.projeto.tcc.application.usecase.vaga;

import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarVagasUseCase {
    Page<VagaResponseDTO> executar( Pageable pageable);
}
