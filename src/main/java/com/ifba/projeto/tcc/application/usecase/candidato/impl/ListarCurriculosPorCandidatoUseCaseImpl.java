package com.ifba.projeto.tcc.application.usecase.candidato.impl;

import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.application.mapper.CurriculoMapper;
import com.ifba.projeto.tcc.application.usecase.candidato.DetalharCurriculoAtivoUseCase;
import com.ifba.projeto.tcc.application.usecase.candidato.ListarCurriculosPorCandidatoUseCase;
import com.ifba.projeto.tcc.domain.repository.CurriculoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarCurriculosPorCandidatoUseCaseImpl implements ListarCurriculosPorCandidatoUseCase {
    private final CurriculoRepository curriculoRepository;
    private final CurriculoMapper curriculoMapper;
    @Override
    public Page<CurriculoResponseDTO> executar(Pageable pageable, Long candidatoId) {
           return curriculoRepository.findAllByCandidatoId(pageable, candidatoId).map(curriculoMapper::toDto);
    }
}
