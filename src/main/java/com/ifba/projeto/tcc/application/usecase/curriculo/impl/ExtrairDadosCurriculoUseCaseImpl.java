package com.ifba.projeto.tcc.application.usecase.curriculo.impl;

import com.ifba.projeto.tcc.Infra.service.CurriculoParserService;
import com.ifba.projeto.tcc.Infra.service.PdfExtractionService;
import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.application.helper.LimparTexto;
import com.ifba.projeto.tcc.application.usecase.curriculo.ExtrairDadosCurriculoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExtrairDadosCurriculoUseCaseImpl implements ExtrairDadosCurriculoUseCase {

    private final PdfExtractionService pdfExtractionService;
    private final CurriculoParserService parserService;

    @Override
    public CurriculoExtraidoResponseDTO executar(File arquivoPdf) {
        log.info("Extraindo dados do currículo: {}", arquivoPdf.getName());
        String textoExtraido = pdfExtractionService.extractText(arquivoPdf);
        textoExtraido = LimparTexto.executar(textoExtraido);
        log.debug("Texto extraído e limpo: {}", textoExtraido);
        return parserService.extrairInformacoes(textoExtraido);
    }

}