package com.ifba.projeto.tcc.application.facede;

import com.ifba.projeto.tcc.application.dto.request.UsuarioLoginRequestDTO;
import com.ifba.projeto.tcc.application.dto.request.UsuarioRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.AuthResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.UsuarioRegistroResponseDTO;
import com.ifba.projeto.tcc.application.usecase.usuario.CriarUsuarioUseCase;
import com.ifba.projeto.tcc.application.usecase.usuario.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioFacede {
    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final LoginUseCase loginUseCase;
    public UsuarioRegistroResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        return criarUsuarioUseCase.executar(dto);
    }

    public AuthResponseDTO loginUsuario(UsuarioLoginRequestDTO dto) {
        return loginUseCase.executar(dto);
    }
}
