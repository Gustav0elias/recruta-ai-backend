package com.ifba.projeto.tcc.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record CurriculoRequestDTO(
        @NotNull(message = "O arquivo do currículo é obrigatório")
        MultipartFile arquivo

) {}