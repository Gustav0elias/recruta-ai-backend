package com.ifba.projeto.tcc.application.usecase.candidato.impl;

import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import com.ifba.projeto.tcc.application.mapper.CandidatoMapper;
import com.ifba.projeto.tcc.application.mapper.VagaMapper;
import com.ifba.projeto.tcc.application.usecase.candidato.ListarCandidatosUseCase;
import com.ifba.projeto.tcc.application.usecase.vaga.ListarVagasUseCase;
import com.ifba.projeto.tcc.domain.repository.CandidatoRepository;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarCandidatosUseCaseImpl implements ListarCandidatosUseCase {
    private final CandidatoRepository repository;
    private final CandidatoMapper mapper;
    @Override
    public Page<CandidatoResponseDTO> executar(Long vagaId, Pageable pageable) {
        return repository.findAllByVagaId(vagaId, pageable).map(mapper::toDto);    }
}
