package com.ifba.projeto.tcc.application.dto.response;

public record FieldErrorDTO(
        String field,
        String message,
        Object rejectedValue
) {
}

