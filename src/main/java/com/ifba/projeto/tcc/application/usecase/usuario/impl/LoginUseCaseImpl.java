package com.ifba.projeto.tcc.application.usecase.usuario.impl;

import com.ifba.projeto.tcc.application.dto.request.UsuarioLoginRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.AuthResponseDTO;
import com.ifba.projeto.tcc.application.usecase.usuario.LoginUseCase;
import com.ifba.projeto.tcc.config.JwtService;
import com.ifba.projeto.tcc.domain.entity.Usuario;
import com.ifba.projeto.tcc.domain.exception.UnauthorizedException;
import com.ifba.projeto.tcc.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public AuthResponseDTO executar(UsuarioLoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UnauthorizedException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
            throw new UnauthorizedException("Email ou senha inválidos");
        }

        String token = jwtService.gerarToken(usuario.getEmail());
        long expiresIn = jwtService.getExpirationInSeconds();
        LocalDateTime expiresAt = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(jwtService.getExpirationDate().getTime()),
                ZoneId.systemDefault()
        );

        AuthResponseDTO.UserInfoDTO userInfo = new AuthResponseDTO.UserInfoDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );

        return new AuthResponseDTO(
                token,
                "Bearer",
                expiresIn,
                expiresAt,
                userInfo
        );
    }
}
