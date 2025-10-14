package com.ifba.projeto.tcc.Infra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifba.projeto.tcc.Infra.api.AiClient;
import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.ScoreResponseDTO;
import com.ifba.projeto.tcc.application.helper.ScorePromptBuilder;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Vaga;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefinirScoreAiService {
    private final AiClient aiClient;
    private final ObjectMapper objectMapper;

    public DefinirScoreAiService(@Qualifier("openAiClient") AiClient aiClient, ObjectMapper objectMapper ){
        this.aiClient = aiClient;
        this.objectMapper = objectMapper;
    }
    public ScoreResponseDTO calcularScore(Candidato candidato, Vaga vaga) {
        try {
            log.info("Calculando score do candidato para a vaga...");

            String prompt = ScorePromptBuilder.construirPromptScore(candidato, vaga);
            String resposta = aiClient.gerarResposta(prompt);

            log.debug("Resposta do GPT para score: {}", resposta);

            return objectMapper.readValue(resposta, ScoreResponseDTO.class);

        } catch (Exception e) {
            log.error("Erro ao calcular score via GPT: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao calcular score do candidato", e);
        }
    }
}
