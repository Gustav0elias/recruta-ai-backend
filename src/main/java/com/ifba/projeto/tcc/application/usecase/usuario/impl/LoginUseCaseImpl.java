package com.ifba.projeto.tcc.application.usecase.usuario.impl;

import com.ifba.projeto.tcc.application.dto.request.UsuarioLoginRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.AuthResponseDTO;
import com.ifba.projeto.tcc.application.mapper.UsuarioMapper;
import com.ifba.projeto.tcc.application.usecase.usuario.LoginUseCase;
import com.ifba.projeto.tcc.config.JwtService;
import com.ifba.projeto.tcc.domain.entity.Usuario;
import com.ifba.projeto.tcc.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UsuarioMapper mapper;
    @Override
    public AuthResponseDTO executar (UsuarioLoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        String token = jwtService.gerarToken(usuario.getEmail());
        return new AuthResponseDTO(token);
    }
}
