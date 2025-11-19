package com.ifba.projeto.tcc.application.usecase.candidato.impl;

import com.ifba.projeto.tcc.application.usecase.candidato.DeletarCandidatoUseCase;
import com.ifba.projeto.tcc.application.usecase.curriculo.DeletarCurriculoUseCase;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Curriculo;
import com.ifba.projeto.tcc.domain.repository.CandidatoRepository;
import com.ifba.projeto.tcc.domain.repository.CurriculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarCandidatoUseCaseImpl implements DeletarCandidatoUseCase {
    private final CandidatoRepository candidatoRepository;

    @Override
    public void executar(Long idCandidato) {
        Candidato candidato = candidatoRepository.findById(idCandidato)
                .orElseThrow(() -> new IllegalArgumentException("Currículo não encontrado para exclusão."));
        candidatoRepository.delete(candidato);
    }
}
