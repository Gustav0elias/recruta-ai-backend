package com.ifba.projeto.tcc.application.usecase.habilidade.impl;

import com.ifba.projeto.tcc.application.usecase.habilidade.RemoverHabilidadeUseCase;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoverHabilidadeUseCaseImpl implements RemoverHabilidadeUseCase {

    private final HabilidadeRepository repository;

    @Override
    public void removerHabilidade(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Habilidade com ID " + id + " não encontrada para exclusão.");
        }
        repository.deleteById(id);
    }
}