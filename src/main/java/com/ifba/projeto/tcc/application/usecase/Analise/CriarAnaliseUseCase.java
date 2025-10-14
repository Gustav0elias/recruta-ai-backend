package com.ifba.projeto.tcc.application.usecase.Analise;

import com.ifba.projeto.tcc.application.dto.response.ExperienciaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.ScoreResponseDTO;
import com.ifba.projeto.tcc.domain.entity.*;

import java.util.List;

public interface CriarAnaliseUseCase {
    Analise  executar (ScoreResponseDTO score, Curriculo curriculo, TipoAnalise tipoAnalise);
}
