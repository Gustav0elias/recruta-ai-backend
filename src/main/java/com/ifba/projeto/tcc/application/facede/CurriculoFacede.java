package com.ifba.projeto.tcc.application.facede;

import com.ifba.projeto.tcc.application.dto.request.CurriculoRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.application.usecase.curriculo.CriarCurriculoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurriculoFacede {
    private final CriarCurriculoUseCase criarCurriculoUseCase;
    public CurriculoResponseDTO criarCurriculo(CurriculoRequestDTO dto) {
        return criarCurriculoUseCase.executar(dto);
    }
}
