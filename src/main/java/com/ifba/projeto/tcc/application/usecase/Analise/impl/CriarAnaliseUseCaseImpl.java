package com.ifba.projeto.tcc.application.usecase.Analise.impl;

import com.ifba.projeto.tcc.application.dto.response.ExperienciaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.ScoreResponseDTO;
import com.ifba.projeto.tcc.application.usecase.Analise.CriarAnaliseUseCase;
import com.ifba.projeto.tcc.domain.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CriarAnaliseUseCaseImpl implements CriarAnaliseUseCase {

    @Override
    public Analise executar(ScoreResponseDTO score, Curriculo curriculo, TipoAnalise tipoAnalise) {
        Analise analise = new Analise();
        analise.setTipoAnalise(tipoAnalise);
        String descricao = (tipoAnalise == TipoAnalise.GERAL)  ? score.analiseGeral() : score.analiseEspecifica();
        analise.setDescricao(descricao);
        analise.setCurriculo(curriculo);
    return analise;
    }
}
