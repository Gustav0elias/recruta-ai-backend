package com.ifba.projeto.tcc.application.usecase.vaga.impl;

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
    @Override
    public Page<VagaResponseDTO> executar(Long usuarioId, Pageable pageable) {
        return repository.findAllByUserId(usuarioId, pageable).map(mapper::toDto);    }
}
