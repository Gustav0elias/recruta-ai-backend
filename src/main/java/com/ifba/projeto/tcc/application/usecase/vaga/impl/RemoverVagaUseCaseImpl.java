package com.ifba.projeto.tcc.application.usecase.vaga.impl;

import com.ifba.projeto.tcc.application.mapper.VagaMapper;
import com.ifba.projeto.tcc.application.usecase.vaga.RemoverVagaUseCase;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoverVagaUseCaseImpl implements RemoverVagaUseCase {
    private final VagaRepository repository;
    private final VagaMapper mapper;
    @Override
    public void executar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Vaga com ID " + id + " não encontrada para exclusão.");
        }
        repository.deleteById(id);
    }
}
