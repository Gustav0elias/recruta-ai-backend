package com.ifba.projeto.tcc.application.usecase.candidato.impl;

import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.application.usecase.candidato.CriarCandidatoUseCase;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.repository.CandidatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarCandidatoUseCaseImpl implements CriarCandidatoUseCase {
    private final CandidatoRepository candidatoRepository;
    @Override
    public Candidato executar(CurriculoExtraidoResponseDTO dto) {
        Candidato candidato = new Candidato();
        candidato.setEmail(dto.email());
        candidato.setNome(dto.nome());
        candidato.setTelefone(dto.telefone());

        return candidatoRepository.save(candidato);
    }
}
