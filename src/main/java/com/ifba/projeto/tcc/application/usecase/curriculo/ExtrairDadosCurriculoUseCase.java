package com.ifba.projeto.tcc.application.usecase.curriculo;

import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;

import java.io.File;

public interface ExtrairDadosCurriculoUseCase {
    CurriculoExtraidoResponseDTO executar(File arquivoPdf);
}
