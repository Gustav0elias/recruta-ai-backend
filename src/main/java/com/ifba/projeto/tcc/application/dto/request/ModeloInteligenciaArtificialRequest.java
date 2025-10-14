package com.ifba.projeto.tcc.application.dto.request;

public record ModeloInteligenciaArtificialRequest (
        String model,
        String prompt,
        boolean stream
) {
}
