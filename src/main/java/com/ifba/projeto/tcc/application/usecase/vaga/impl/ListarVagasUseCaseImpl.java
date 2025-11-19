package com.ifba.projeto.tcc.application.usecase.vaga.impl;

import com.ifba.projeto.tcc.Infra.service.AuthenticatedUserProviderService;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import com.ifba.projeto.tcc.application.mapper.VagaMapper;
import com.ifba.projeto.tcc.application.usecase.vaga.ListarVagasUseCase;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarVagasUseCaseImpl implements ListarVagasUseCase {
    private final VagaRepository repository;
    private final VagaMapper mapper;
    private final AuthenticatedUserProviderService authenticatedUserProvider;

    @Override
    public Page<VagaResponseDTO> executar(Pageable pageable) {
        var usuario = authenticatedUserProvider.getAuthenticatedUser();

        return repository.findAllByUserId(usuario.getId(), pageable).map(vaga -> {
            VagaResponseDTO dto = mapper.toDto(vaga);
            Long quantidadeCandidaturas = repository.countCandidaturasByVagaId(vaga.getId());
            return new VagaResponseDTO(
                    dto.id(),
                    dto.uuid(),
                    dto.titulo(),
                    dto.descricao(),
                    dto.nivelExperiencia(),
                    dto.criadoEm(),
                    dto.habilidades(),
                    quantidadeCandidaturas != null ? quantidadeCandidaturas.intValue() : 0
            );
        });
    }
}
