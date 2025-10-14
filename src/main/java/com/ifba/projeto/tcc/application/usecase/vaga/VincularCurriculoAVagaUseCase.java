package com.ifba.projeto.tcc.application.usecase.vaga;

import com.ifba.projeto.tcc.domain.entity.Curriculo;

import java.util.UUID;

public interface VincularCurriculoAVagaUseCase {
    void executar(Curriculo curriculo, Long idVaga);

}
