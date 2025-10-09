package com.ifba.projeto.tcc.application.dto.response;

import java.util.UUID;

public record UsuarioResponseDTO(
        Long id,
        UUID uuid,
        String nome,
        String email
) {}