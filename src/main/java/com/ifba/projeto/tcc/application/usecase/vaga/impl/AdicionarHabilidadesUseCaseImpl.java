package com.ifba.projeto.tcc.application.usecase.vaga.impl;

import com.ifba.projeto.tcc.application.dto.request.AdicionarHabilidadesRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import com.ifba.projeto.tcc.application.mapper.VagaMapper;
import com.ifba.projeto.tcc.application.usecase.vaga.AdicionarHabilidadesUseCase;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.entity.Vaga;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdicionarHabilidadesUseCaseImpl implements AdicionarHabilidadesUseCase {
    private final VagaRepository vagaRepository;
    private final HabilidadeRepository habilidadeRepository;
    private final VagaMapper vagaMapper;
    @Override
    public VagaResponseDTO executar(Long vagaId, AdicionarHabilidadesRequestDTO dto) {
        Vaga vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new EntityNotFoundException("Vaga com ID " + vagaId + " não encontrada"));

        Set<Habilidade> novasHabilidades = dto.habilidadesUuids().stream()
                .map(uuid -> habilidadeRepository.findByUuid(uuid)
                        .orElseThrow(() -> new EntityNotFoundException("Habilidade com UUID " + uuid + " não encontrada")))
                .collect(Collectors.toSet());

        vaga.getHabilidades().addAll(novasHabilidades);
        vagaRepository.save(vaga);
        return vagaMapper.toDto(vaga);
    }
}
