package com.ifba.projeto.tcc.application.usecase.usuario.impl;

import com.ifba.projeto.tcc.application.dto.request.UsuarioRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.UsuarioRegistroResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.UsuarioResponseDTO;
import com.ifba.projeto.tcc.application.mapper.UsuarioMapper;
import com.ifba.projeto.tcc.application.usecase.usuario.CriarUsuarioUseCase;
import com.ifba.projeto.tcc.config.JwtService;
import com.ifba.projeto.tcc.domain.entity.Usuario;
import com.ifba.projeto.tcc.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UsuarioRegistroResponseDTO executar(UsuarioRequestDTO dto) {
        Usuario usuario = mapper.toModel(dto);

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        repository.save(usuario);

        String token = jwtService.gerarToken(usuario.getEmail());

        return new UsuarioRegistroResponseDTO(usuario.getNome(), usuario.getEmail(), token);
    }
}