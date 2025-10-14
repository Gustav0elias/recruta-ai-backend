package com.ifba.projeto.tcc.application.usecase.candidato.impl;

import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.mapper.CandidatoMapper;
import com.ifba.projeto.tcc.application.usecase.candidato.ListarCandidatosGeralUseCase;
import com.ifba.projeto.tcc.application.usecase.candidato.ListarCandidatosUseCase;
import com.ifba.projeto.tcc.domain.repository.CandidatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarCandidatosGeralUseCaseImpl implements ListarCandidatosGeralUseCase {
    private final CandidatoRepository repository;
    private final CandidatoMapper mapper;
    @Override
    public Page<CandidatoResponseDTO> executar(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);    }
}
