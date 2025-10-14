package com.ifba.projeto.tcc.Infra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifba.projeto.tcc.Infra.api.AiClient;
import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.application.helper.CurriculoPromptBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurriculoParserService {
    private static final Logger log = LoggerFactory.getLogger(CurriculoParserService.class);

    private final ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate restTemplate;
    private final PdfExtractionService pdfExtractionService;
    private final AiClient aiClient;
    public CurriculoParserService(
            RestTemplate restTemplate,
            PdfExtractionService pdfExtractionService,
            @Qualifier("openAiClient") AiClient aiClient
    ) {
        this.restTemplate = restTemplate;
        this.pdfExtractionService = pdfExtractionService;
        this.aiClient = aiClient;
    }
    public CurriculoExtraidoResponseDTO extrairInformacoes(String conteudo) {
        try {
            log.info("Extraindo informações do currículo...");

            String prompt = CurriculoPromptBuilder.ConstruirPromptCandidato(conteudo);
            System.out.println(conteudo);
            String resposta = aiClient.gerarResposta(prompt);
            System.out.println("Ia resposta" + resposta);
            return mapper.readValue(resposta, CurriculoExtraidoResponseDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao extrair informações com Ollama: " + e.getMessage(), e);
        }
    }
}
