package com.ifba.projeto.tcc.application.dto.request;

public record UsuarioRegisterRequestDTO(
        String nome,
        String email,
        String senha
) {}