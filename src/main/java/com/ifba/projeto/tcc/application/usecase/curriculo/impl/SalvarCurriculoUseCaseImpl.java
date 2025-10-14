package com.ifba.projeto.tcc.application.usecase.curriculo.impl;

import com.ifba.projeto.tcc.application.usecase.curriculo.SalvarCurriculoUseCase;
import com.ifba.projeto.tcc.domain.entity.Curriculo;
import com.ifba.projeto.tcc.domain.repository.CurriculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalvarCurriculoUseCaseImpl implements SalvarCurriculoUseCase {
    private final CurriculoRepository curriculoRepository;

    @Override
    public void executar(Curriculo curriculo) {
        curriculoRepository.save(curriculo);
    }

}
