package com.ifba.projeto.tcc.web.api;

import com.ifba.projeto.tcc.application.dto.request.UsuarioLoginRequestDTO;
import com.ifba.projeto.tcc.application.dto.request.UsuarioRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.AuthResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.UsuarioRegistroResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v01/usuario")
public interface UsuarioController {
    @PostMapping("/register")
    ResponseEntity<UsuarioRegistroResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO dto);

    @PostMapping("/login")
     ResponseEntity<AuthResponseDTO> login(@RequestBody UsuarioLoginRequestDTO dto);
}