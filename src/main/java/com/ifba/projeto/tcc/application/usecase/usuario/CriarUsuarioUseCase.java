package com.ifba.projeto.tcc.application.usecase.usuario;

import com.ifba.projeto.tcc.application.dto.request.UsuarioRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.UsuarioRegistroResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.UsuarioResponseDTO;

public interface CriarUsuarioUseCase {
    UsuarioRegistroResponseDTO executar(UsuarioRequestDTO dto);
}
