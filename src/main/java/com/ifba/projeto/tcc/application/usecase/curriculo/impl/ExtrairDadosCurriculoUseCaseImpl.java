package com.ifba.projeto.tcc.application.usecase.curriculo.impl;

import com.ifba.projeto.tcc.Infra.service.CurriculoParserService;
import com.ifba.projeto.tcc.Infra.service.PdfExtractionService;
import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.application.usecase.curriculo.ExtrairDadosCurriculoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;


@RequiredArgsConstructor
@Service
public class ExtrairDadosCurriculoUseCaseImpl implements ExtrairDadosCurriculoUseCase {

    private final PdfExtractionService pdfExtractionService;
    private final CurriculoParserService parserService;

    @Override
    public CurriculoExtraidoResponseDTO executar(File arquivoPdf) {
        String textoExtraido = pdfExtractionService.extractText(arquivoPdf);
        textoExtraido = limparTexto(textoExtraido);
        return parserService.extrairInformacoes(textoExtraido);
    }

    private String limparTexto(String texto) {
        texto = texto.replaceAll("[^\\x20-\\x7E\\n]", " ");
        texto = texto.replaceAll("\\s+", " ").trim();
        return texto;
    }
}