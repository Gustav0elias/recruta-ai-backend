package com.ifba.projeto.tcc.application.usecase.vaga;

import com.ifba.projeto.tcc.domain.entity.Curriculo;

public interface AtualizarVinculoCurriculoAVagaUseCase {
    void executar(Curriculo curriculo, Long idVaga);

}
