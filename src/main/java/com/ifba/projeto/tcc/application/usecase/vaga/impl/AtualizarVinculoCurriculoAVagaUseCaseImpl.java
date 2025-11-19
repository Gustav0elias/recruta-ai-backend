package com.ifba.projeto.tcc.application.usecase.vaga.impl;

import com.ifba.projeto.tcc.Infra.service.DefinirScoreAiService;
import com.ifba.projeto.tcc.application.dto.response.ScoreResponseDTO;
import com.ifba.projeto.tcc.application.usecase.Analise.CriarAnaliseUseCase;
import com.ifba.projeto.tcc.application.usecase.vaga.AtualizarVinculoCurriculoAVagaUseCase;
import com.ifba.projeto.tcc.application.usecase.vaga.VincularCurriculoAVagaUseCase;
import com.ifba.projeto.tcc.domain.entity.*;
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AtualizarVinculoCurriculoAVagaUseCaseImpl implements AtualizarVinculoCurriculoAVagaUseCase {

    private final VagaRepository vagaRepository;
    private final DefinirScoreAiService definirScoreAiService;
    private final CriarAnaliseUseCase criarAnaliseUseCase;
    @Override
    public void executar(Curriculo curriculo, Long idVaga) {
        if (idVaga != null) {
            Vaga vaga = vagaRepository.findById(idVaga)
                    .orElseThrow(() -> new ResourceNotFoundException("Vaga", idVaga));

            ScoreResponseDTO score = definirScoreAiService.calcularScore(curriculo.getCandidato(), vaga);

            Analise analiseespecifica = criarAnaliseUseCase.executar(score, curriculo, TipoAnalise.ESPECIFICA_CURRICULO);
            Analise analiseGeral = criarAnaliseUseCase.executar(score, curriculo, TipoAnalise.GERAL);

            curriculo.getAnalises().add(analiseespecifica);
            curriculo.getAnalises().add(analiseGeral);

            vagaRepository.save(vaga);
        }
    }
}