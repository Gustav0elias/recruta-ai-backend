package com.ifba.projeto.tcc.Infra.service;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PdfExtractionService {

    private final Tika tika = new Tika();

    public String extractText(File pdfFile) {
        try {
            return tika.parseToString(pdfFile);
        } catch (IOException | TikaException e) {
            throw new RuntimeException("Erro ao extrair texto do PDF", e);
        }
    }
}