package com.ifba.projeto.tcc.application.usecase.usuario;

import com.ifba.projeto.tcc.application.dto.request.UsuarioLoginRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.AuthResponseDTO;

public interface LoginUseCase {
    AuthResponseDTO executar(UsuarioLoginRequestDTO dto);
}
