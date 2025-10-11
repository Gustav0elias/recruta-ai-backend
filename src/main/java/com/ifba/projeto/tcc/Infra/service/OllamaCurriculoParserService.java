package com.ifba.projeto.tcc.Infra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OllamaCurriculoParserService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final PdfExtractionService pdfExtractionService; // <- novo

    public CurriculoExtraidoResponseDTO extrairInformacoes(String conteudo) {
        try {

            // 2. Define o prompt
            String prompt = """
                Extraia do texto do currículo abaixo as seguintes informações e devolva um JSON válido (seja o mais preciso possivel, lembrando que isso é um curriculo:
                {
                  "nome": "",
                  "email": "",
                  "telefone": "",
                  "habilidades": [
                  {
                  "nome":"",
                  "tipo":""
                  }
                  ],
                  "experiencias": [
                    {
                      "empresa": "",
                      "cargo": "",
                      "descricao": "",
                      "dataInicio": "",
                      "dataFim": ""
                    }
                  ]
                }
                Texto do currículo:
                """ + conteudo;
            System.out.println(conteudo);
            // 3. Chama o Ollama local
            RestTemplate restTemplate = new RestTemplate();

            Map<String, Object> body = new HashMap<>();
            body.put("model", "gemma:2b");
            body.put("prompt", prompt);
            body.put("stream", false); // queremos resposta completa

            Map response = restTemplate.postForObject(
                    "http://localhost:11434/api/generate",
                    body,
                    Map.class
            );

            String resposta = (String) response.get("response");
            System.out.println(resposta);
            return mapper.readValue(resposta, CurriculoExtraidoResponseDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao extrair informações com Ollama: " + e.getMessage(), e);
        }
    }
}
