package com.ifba.projeto.tcc.application.dto.request;

import jakarta.validation.constraints.Size;

public record PerfilRequestDTO (
        @Size(max = 100) String descricao,
        @Size(max = 15) String telefone,
        String fotoPerfil
) {
}
