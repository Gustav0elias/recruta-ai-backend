package com.ifba.projeto.tcc.application.usecase.habilidade.impl;

import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.application.mapper.HabilidadeMapper;
import com.ifba.projeto.tcc.application.usecase.habilidade.CriarHabilidadeUseCase;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CriarHabilidadeUseCaseImpl implements CriarHabilidadeUseCase {

    private final HabilidadeRepository repository;
    private final HabilidadeMapper mapper;

    @Override
    public HabilidadeResponseDTO executar(HabilidadeRequestDTO habilidade) {
        Habilidade habilidadeEntity = mapper.toModel(habilidade);
        repository.save(habilidadeEntity);
        return mapper.toDTO(habilidadeEntity);
    }
}
