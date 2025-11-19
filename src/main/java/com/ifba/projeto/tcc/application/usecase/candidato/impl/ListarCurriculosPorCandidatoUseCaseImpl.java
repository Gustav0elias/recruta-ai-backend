package com.ifba.projeto.tcc.application.usecase.candidato.impl;

import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.application.mapper.CurriculoMapper;
import com.ifba.projeto.tcc.application.usecase.candidato.DetalharCurriculoAtivoUseCase;
import com.ifba.projeto.tcc.domain.repository.CurriculoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalharCurriculoAtivoUseCaseImpl implements DetalharCurriculoAtivoUseCase {
    private final CurriculoRepository curriculoRepository;
    private final CurriculoMapper curriculoMapper;
    @Override
    public CurriculoResponseDTO executar(Long candidatoId) {
           return curriculoRepository.findAtivoByCandidatoId(candidatoId).map(curriculoMapper::toDto).orElseThrow(() -> new EntityNotFoundException(
                   "Nenhum currículo ativo encontrado para o candidato ID: " + candidatoId
           ));
    }
}
