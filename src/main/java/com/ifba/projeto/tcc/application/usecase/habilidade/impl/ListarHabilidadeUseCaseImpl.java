package com.ifba.projeto.tcc.application.usecase.habilidade.impl;

import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.application.mapper.HabilidadeMapper;
import com.ifba.projeto.tcc.application.usecase.habilidade.ListarHabilidadesUseCase;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ListarHabilidadeUseCaseImpl implements ListarHabilidadesUseCase {
    private final HabilidadeRepository repository;
    private final HabilidadeMapper mapper;

    @Override
    public Page<HabilidadeResponseDTO> executar(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }
}
