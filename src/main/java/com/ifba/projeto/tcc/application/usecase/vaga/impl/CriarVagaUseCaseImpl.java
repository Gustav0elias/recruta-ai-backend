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
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
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
    private final HabilidadeRepository habilidadeRepository;
    private final VagaMapper vagaMapper;
    private final AuthenticatedUserProviderService authenticatedUserProvider;

    @Override
    public VagaResponseDTO executar(VagaRequestDTO dto) {
        var usuario = authenticatedUserProvider.getAuthenticatedUser();

        Vaga vaga = vagaMapper.toModel(dto);
        vaga.setUuid(UUID.randomUUID());
        vaga.setUsuario(usuario);
        vaga.setCriadoEm(LocalDateTime.now());

        dto.habilidades().forEach(hp -> {
            Habilidade habilidade = habilidadeRepository.findByUuid(hp.habilidadeUuid())
                    .orElseThrow(() -> new ResourceNotFoundException("Habilidade", hp.habilidadeUuid()));
            vaga.adicionarHabilidade(habilidade, hp.peso());
        });

        Vaga vagaSalva = vagaRepository.save(vaga);

        return vagaMapper.toDto(vagaSalva);
    }
}
