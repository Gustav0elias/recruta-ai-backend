package com.ifba.projeto.tcc.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 50)
        String nome,

        @Size(max = 100)
        @Email(message = "Email inválido")
        String email,


        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, max = 200)
        String senha
) {
}
