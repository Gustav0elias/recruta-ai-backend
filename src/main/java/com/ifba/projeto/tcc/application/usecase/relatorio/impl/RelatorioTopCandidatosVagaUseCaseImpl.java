package com.ifba.projeto.tcc.application.usecase.relatorio.impl;

import com.ifba.projeto.tcc.Infra.service.AuthenticatedUserProviderService;
import com.ifba.projeto.tcc.application.dto.response.CandidatoScoreResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioTopCandidatosVagaResponseDTO;
import com.ifba.projeto.tcc.application.usecase.relatorio.RelatorioTopCandidatosVagaUseCase;
import com.ifba.projeto.tcc.domain.exception.ForbiddenException;
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
import com.ifba.projeto.tcc.domain.repository.CandidaturaRepository;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioTopCandidatosVagaUseCaseImpl implements RelatorioTopCandidatosVagaUseCase {
    private final VagaRepository vagaRepository;
    private final CandidaturaRepository candidaturaRepository;
    private final AuthenticatedUserProviderService authenticatedUserProvider;

    @Override
    public RelatorioTopCandidatosVagaResponseDTO executar(Long vagaId, Integer limite) {
        var usuario = authenticatedUserProvider.getAuthenticatedUser();
        
        var vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga", vagaId));
        
        if (!vaga.getUsuario().getId().equals(usuario.getId())) {
            throw new ForbiddenException("Vaga não pertence ao usuário autenticado");
        }
        
        int limit = limite != null && limite > 0 ? limite : 10;
        Pageable pageable = PageRequest.of(0, limit);
        
        List<CandidatoScoreResponseDTO> candidatos = candidaturaRepository
                .findTopCandidatosByVagaId(vagaId, pageable)
                .stream()
                .map(candidatura -> {
                    var candidato = candidatura.getCurriculo().getCandidato();
                    return new CandidatoScoreResponseDTO(
                            candidato.getId(),
                            candidato.getUuid(),
                            candidato.getNome(),
                            candidato.getEmail(),
                            candidatura.getScore(),
                            candidatura.getDataCadastro()
                    );
                })
                .collect(Collectors.toList());
        
        return new RelatorioTopCandidatosVagaResponseDTO(
                vaga.getId(),
                vaga.getTitulo(),
                candidatos
        );
    }
}

