package com.ifba.projeto.tcc.Infra.service;

import com.ifba.projeto.tcc.domain.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class PdfExtractionService {

    private final Tika tika = new Tika();

    public String extractText(File pdfFile) {
        try {
            log.debug("Extraindo texto do PDF: {}", pdfFile.getName());
            return tika.parseToString(pdfFile);
        } catch (IOException | TikaException e) {
            log.error("Erro ao extrair texto do PDF: {}", e.getMessage(), e);
            throw new BusinessException("Erro ao extrair texto do PDF", e);
        }
    }
}