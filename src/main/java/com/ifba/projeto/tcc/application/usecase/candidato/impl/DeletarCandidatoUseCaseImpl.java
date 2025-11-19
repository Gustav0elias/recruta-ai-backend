package com.ifba.projeto.tcc.application.usecase.curriculo.impl;

import com.ifba.projeto.tcc.application.usecase.curriculo.DeletarCurriculoUseCase;
import com.ifba.projeto.tcc.domain.entity.Curriculo;
import com.ifba.projeto.tcc.domain.repository.CurriculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarCurriculoUseCaseImpl implements DeletarCurriculoUseCase {
    private final CurriculoRepository curriculoRepository;

    @Override
    public void executar(Long idCurriculo) {
        Curriculo curriculo = curriculoRepository.findById(idCurriculo)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado para exclusão."));
        curriculoRepository.delete(curriculo);
    }
}
