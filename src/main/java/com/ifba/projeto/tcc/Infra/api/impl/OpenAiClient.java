package com.ifba.projeto.tcc.Infra.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifba.projeto.tcc.Infra.api.AiClient;
import com.ifba.projeto.tcc.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Slf4j
@Component("openAiClient")
@RequiredArgsConstructor
public class OpenAiClient implements AiClient {
    @Value("${openai.api.key}")
    private String apiKey;
    @Value("${openai.api.url}")
    private String urlChatGpt;
    @Value("${openai.model:gpt-3.5-turbo}")
    private String model;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Override
    public String gerarResposta(String prompt) {
        try {
            log.info("Enviando requisição para OpenAI...");

            Map<String, Object> body = Map.of(
                    "model", model,
                    "messages", new Object[]{
                            Map.of("role", "system", "content",
                                    "Você é um assistente que extrai dados de currículos e responde **somente** em JSON válido."),
                            Map.of("role", "user", "content", prompt)
                    },
                    "temperature", 0.2
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    urlChatGpt, HttpMethod.POST, entity, String.class);

            JsonNode root = objectMapper.readTree(response.getBody());
            String content = root.path("choices").get(0).path("message").path("content").asText();

            log.debug("Resposta bruta da OpenAI: {}", content);
            return content.trim();

        } catch (Exception e) {
            log.error("Erro ao chamar API OpenAI: {}", e.getMessage(), e);
            throw new BusinessException("Falha ao gerar resposta via OpenAI", e);
        }
    }
}