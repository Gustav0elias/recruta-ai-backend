package com.ifba.projeto.tcc.Infra.service;

import com.ifba.projeto.tcc.domain.entity.Usuario;
import com.ifba.projeto.tcc.domain.exception.UnauthorizedException;
import com.ifba.projeto.tcc.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserProviderService {

    private final UsuarioRepository usuarioRepository;

    public Usuario getAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new UnauthorizedException("Usuário não autenticado");
        }

        return (Usuario) authentication.getPrincipal();
    }
}