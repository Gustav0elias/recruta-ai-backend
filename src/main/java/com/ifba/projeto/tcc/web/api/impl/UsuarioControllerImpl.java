package com.ifba.projeto.tcc.web.api.impl;

import com.ifba.projeto.tcc.application.dto.request.UsuarioLoginRequestDTO;
import com.ifba.projeto.tcc.application.dto.request.UsuarioRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.AuthResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.UsuarioRegistroResponseDTO;
import com.ifba.projeto.tcc.application.facede.UsuarioFacede;
import com.ifba.projeto.tcc.web.api.UsuarioController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioController {
    private final UsuarioFacede usuarioServiceFacede;

    @Override
    public ResponseEntity<UsuarioRegistroResponseDTO> criarUsuario(UsuarioRequestDTO dto) {
        UsuarioRegistroResponseDTO response = usuarioServiceFacede.criarUsuario(dto);
        return ResponseEntity.ok(response);
    }
    @Override
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UsuarioLoginRequestDTO dto) {
        AuthResponseDTO response = usuarioServiceFacede.loginUsuario(dto);
        return ResponseEntity.ok(response);
    }
}