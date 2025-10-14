package com.ifba.projeto.tcc.application.usecase.candidatura.impl;

import com.ifba.projeto.tcc.application.dto.response.CandidaturaResponseDTO;
import com.ifba.projeto.tcc.application.mapper.CandidaturaMapper;
import com.ifba.projeto.tcc.application.usecase.candidatura.ListarCandidaturasPorScoreUseCase;
import com.ifba.projeto.tcc.application.usecase.candidatura.ListarCandidaturasUseCase;
import com.ifba.projeto.tcc.domain.repository.CandidaturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarCandidaturasPorScoreUseCaseImpl implements ListarCandidaturasPorScoreUseCase {
    private final CandidaturaRepository repository;
    private final CandidaturaMapper mapper;
    @Override
    public Page<CandidaturaResponseDTO> executar(Long vagaId, Pageable pageable) {
        return repository.findAllByVagaIdOrderByScoreDesc(vagaId, pageable).map(mapper::toDto);    }
}
