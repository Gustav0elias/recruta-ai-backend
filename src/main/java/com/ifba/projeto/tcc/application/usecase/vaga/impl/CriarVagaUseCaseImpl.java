package com.ifba.projeto.tcc.application.usecase.vaga.impl;

import com.ifba.projeto.tcc.application.dto.request.VagaRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import com.ifba.projeto.tcc.application.mapper.VagaMapper;
import com.ifba.projeto.tcc.application.usecase.vaga.CriarVagaUseCase;
import com.ifba.projeto.tcc.Infra.service.AuthenticatedUserProviderService;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.entity.Usuario;
import com.ifba.projeto.tcc.domain.entity.Vaga;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import com.ifba.projeto.tcc.domain.repository.UsuarioRepository;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CriarVagaUseCaseImpl implements CriarVagaUseCase {
    private final VagaRepository vagaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HabilidadeRepository habilidadeRepository;
    private final VagaMapper vagaMapper;
    private final AuthenticatedUserProviderService authenticatedUserProvider;

    @Override
    public VagaResponseDTO executar(VagaRequestDTO dto) {
        Usuario usuario = authenticatedUserProvider.getAuthenticatedUser();

        Set<Habilidade> habilidades = dto.habilidadesUuids().stream()
                .map(uuid -> habilidadeRepository.findByUuid(uuid)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Habilidade com UUID " + uuid + " não encontrada")))
                .collect(Collectors.toSet());

        Vaga vaga = vagaMapper.toModel(dto);
        vaga.setUuid(UUID.randomUUID());
        vaga.setUsuario(usuario);
        vaga.setHabilidades(habilidades);
        vaga.setCriadoEm(LocalDateTime.now());

        Vaga vagaSalva = vagaRepository.save(vaga);

        return vagaMapper.toDto(vagaSalva);
    }
}
