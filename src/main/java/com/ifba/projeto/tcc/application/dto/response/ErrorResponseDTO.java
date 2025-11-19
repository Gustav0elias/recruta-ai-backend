package com.ifba.projeto.tcc.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseDTO(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path,
        List<FieldErrorDTO> fieldErrors
) {
    public ErrorResponseDTO(Integer status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
    
    public ErrorResponseDTO(Integer status, String error, String message, String path, List<FieldErrorDTO> fieldErrors) {
        this(LocalDateTime.now(), status, error, message, path, fieldErrors);
    }
}

