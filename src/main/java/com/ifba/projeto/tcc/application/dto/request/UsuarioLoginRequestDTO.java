package com.ifba.projeto.tcc.application.dto.request;

public record UsuarioLoginRequestDTO(
        String email,
        String senha
) {}