package com.ifba.projeto.tcc.application.usecase.relatorio.impl;

import com.ifba.projeto.tcc.Infra.service.AuthenticatedUserProviderService;
import com.ifba.projeto.tcc.application.dto.response.RelatorioVagasMaisProcuradasResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaEstatisticaResponseDTO;
import com.ifba.projeto.tcc.application.usecase.relatorio.RelatorioVagasMaisProcuradasUseCase;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioVagasMaisProcuradasUseCaseImpl implements RelatorioVagasMaisProcuradasUseCase {
    private final VagaRepository vagaRepository;
    private final AuthenticatedUserProviderService authenticatedUserProvider;

    @Override
    public RelatorioVagasMaisProcuradasResponseDTO executar(Integer limite) {
        var usuario = authenticatedUserProvider.getAuthenticatedUser();
        int limit = limite != null && limite > 0 ? limite : 10;
        Pageable pageable = PageRequest.of(0, limit);
        
        List<VagaEstatisticaResponseDTO> vagas = vagaRepository
                .findVagasMaisProcuradasByUsuarioId(usuario.getId(), pageable)
                .stream()
                .map(result -> new VagaEstatisticaResponseDTO(
                        result.getId(),
                        result.getUuid(),
                        result.getTitulo(),
                        result.getNivelExperiencia(),
                        result.getCriadoEm(),
                        result.getQuantidadeCandidaturas(),
                        result.getMediaScore()
                ))
                .collect(Collectors.toList());
        
        return new RelatorioVagasMaisProcuradasResponseDTO(vagas);
    }
}

