package com.ifba.projeto.tcc.application.usecase.candidato.impl;

import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.application.usecase.candidato.CriarCandidatoUseCase;
import com.ifba.projeto.tcc.application.usecase.candidato.GerenciarCriarCandidatoUseCase;
import com.ifba.projeto.tcc.application.usecase.experiencia.CriarExperienciaUseCase;
import com.ifba.projeto.tcc.application.usecase.habilidade.CriarHabilidadeParaCandidatoUseCase;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.repository.CandidatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GerenciarCriarCandidatoUseCaseImpl implements GerenciarCriarCandidatoUseCase {
    private final CriarCandidatoUseCase criarCandidatoUseCase;
    private final CriarHabilidadeParaCandidatoUseCase criarHabilidadeParaCandidatoUseCase;
    private final CriarExperienciaUseCase criarExperienciaUseCase;
    private final CandidatoRepository candidatoRepository;
    @Override
    public Candidato executar(CurriculoExtraidoResponseDTO dto) {
        Candidato candidato = criarCandidatoUseCase.executar(dto);
        candidato.setHabilidades(criarHabilidadeParaCandidatoUseCase.executar(dto.habilidades()));
        candidato.setExperiencias(criarExperienciaUseCase.executar(dto.experiencias(), candidato));
        return candidatoRepository.save(candidato);
    }
}
