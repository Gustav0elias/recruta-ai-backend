package com.ifba.projeto.tcc.application.usecase.relatorio.impl;

import com.ifba.projeto.tcc.application.dto.response.HabilidadeEstatisticaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioHabilidadesMaisRequisitadasResponseDTO;
import com.ifba.projeto.tcc.application.usecase.relatorio.RelatorioHabilidadesMaisRequisitadasUseCase;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioHabilidadesMaisRequisitadasUseCaseImpl implements RelatorioHabilidadesMaisRequisitadasUseCase {
    private final HabilidadeRepository habilidadeRepository;

    @Override
    public RelatorioHabilidadesMaisRequisitadasResponseDTO executar(Integer limite) {
        int limit = limite != null && limite > 0 ? limite : 10;
        Pageable pageable = PageRequest.of(0, limit);
        
        List<HabilidadeEstatisticaResponseDTO> habilidades = habilidadeRepository
                .findHabilidadesMaisRequisitadas(pageable)
                .stream()
                .map(result -> new HabilidadeEstatisticaResponseDTO(
                        result.getUuid(),
                        result.getNome(),
                        result.getTipo() != null ? result.getTipo().name() : null,
                        result.getQuantidadeVagas(),
                        result.getQuantidadeCandidatos()
                ))
                .collect(Collectors.toList());
        
        return new RelatorioHabilidadesMaisRequisitadasResponseDTO(habilidades);
    }
}

