package com.ifba.projeto.tcc.Infra.api.impl;

import com.ifba.projeto.tcc.Infra.api.AiClient;
import com.ifba.projeto.tcc.application.dto.request.ModeloInteligenciaArtificialRequest;
import com.ifba.projeto.tcc.application.dto.response.ModeloInteligenciaArtificialResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component("ollamaAiClient")
@RequiredArgsConstructor
public class OllamaAiClient implements AiClient {
    private final RestTemplate restTemplate;
    @Override
    public String gerarResposta(String prompt) {

        try {
            ModeloInteligenciaArtificialRequest request =
                    new ModeloInteligenciaArtificialRequest("phi3:medium", prompt, false);

            ModeloInteligenciaArtificialResponse response =
                    restTemplate.postForObject("http://localhost:11434/api/generate", request, ModeloInteligenciaArtificialResponse.class);

            return response.response();
        } catch (Exception e) {
            log.error("Erro ao chamar Ollama: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao gerar resposta via Ollama", e);
        }
    }
}
