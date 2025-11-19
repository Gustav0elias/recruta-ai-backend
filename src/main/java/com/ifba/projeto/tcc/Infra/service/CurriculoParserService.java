package com.ifba.projeto.tcc.Infra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifba.projeto.tcc.Infra.api.AiClient;
import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.application.helper.CurriculoPromptBuilder;
import com.ifba.projeto.tcc.domain.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CurriculoParserService {
    private final ObjectMapper objectMapper;
    private final AiClient aiClient;

    public CurriculoParserService(
            ObjectMapper objectMapper,
            @Qualifier("openAiClient") AiClient aiClient
    ) {
        this.objectMapper = objectMapper;
        this.aiClient = aiClient;
    }

    public CurriculoExtraidoResponseDTO extrairInformacoes(String conteudo) {
        try {
            log.info("Extraindo informações do currículo...");
            log.debug("Conteúdo do currículo: {}", conteudo);

            String prompt = CurriculoPromptBuilder.ConstruirPromptCandidato(conteudo);
            String resposta = aiClient.gerarResposta(prompt);
            log.debug("Resposta da IA: {}", resposta);
            
            return objectMapper.readValue(resposta, CurriculoExtraidoResponseDTO.class);

        } catch (Exception e) {
            log.error("Erro ao extrair informações do currículo: {}", e.getMessage(), e);
            throw new BusinessException("Erro ao extrair informações do currículo", e);
        }
    }
}
