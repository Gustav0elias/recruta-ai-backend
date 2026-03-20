package com.ifba.projeto.tcc.Infra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifba.projeto.tcc.Infra.api.AiClient;
import com.ifba.projeto.tcc.application.dto.response.ScoreResponseDTO;
import com.ifba.projeto.tcc.application.helper.ScorePromptBuilder;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Vaga;
import com.ifba.projeto.tcc.domain.exception.BusinessException;
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

            String dadosCandidato = com.ifba.projeto.tcc.application.helper.prompt.CandidatoDataFormatter.formatar(candidato);
            String dadosVaga = com.ifba.projeto.tcc.application.helper.prompt.VagaDataFormatter.formatar(vaga);
            log.info("DADOS FORMATADOS DO CANDIDATO:\n{}", dadosCandidato);
            log.info("DADOS FORMATADOS DA VAGA:\n{}", dadosVaga);

            String prompt = com.ifba.projeto.tcc.application.helper.ScorePromptBuilder.construirPromptScore(candidato, vaga);
            log.info("PROMPT ENVIADO PARA IA:\n{}", prompt);

            String resposta = aiClient.gerarResposta(prompt);

            log.debug("Resposta do GPT para score: {}", resposta);

            String respostaLimpa = limparRespostaJson(resposta);
            log.debug("Resposta limpa: {}", respostaLimpa);

            try {
                return objectMapper.readValue(respostaLimpa, ScoreResponseDTO.class);
            } catch (Exception jsonException) {
                log.error("Erro ao fazer parse do JSON. Resposta original: {}", resposta);
                log.error("Resposta limpa: {}", respostaLimpa);
                throw jsonException;
            }

        } catch (Exception e) {
            log.error("Erro ao calcular score via GPT: {}", e.getMessage(), e);
            throw new BusinessException("Falha ao calcular score do candidato", e);
        }
    }

    private String limparRespostaJson(String resposta) {
        if (resposta == null || resposta.isBlank()) {
            return resposta;
        }

        String limpa = resposta.trim();

        limpa = limpa.replaceAll("(?s)```json\\s*", "");
        limpa = limpa.replaceAll("(?s)```\\s*", "");
        limpa = limpa.trim();

        int inicioJson = limpa.indexOf('{');
        int fimJson = limpa.lastIndexOf('}');

        if (inicioJson != -1 && fimJson != -1 && fimJson > inicioJson) {
            limpa = limpa.substring(inicioJson, fimJson + 1);
        }

        return limpa.trim();
    }
}
