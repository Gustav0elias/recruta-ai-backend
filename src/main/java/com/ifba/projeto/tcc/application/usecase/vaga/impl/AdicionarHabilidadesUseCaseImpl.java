package com.ifba.projeto.tcc.application.usecase.vaga.impl;

import com.ifba.projeto.tcc.application.dto.request.AdicionarHabilidadesRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import com.ifba.projeto.tcc.application.mapper.VagaMapper;
import com.ifba.projeto.tcc.application.usecase.vaga.AdicionarHabilidadesUseCase;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.entity.Vaga;
import com.ifba.projeto.tcc.domain.entity.VagaHabilidade;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
        var vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga", vagaId));

        var novasVagaHabilidades = dto.habilidades().stream()
                .map(req -> {
                    var habilidade = habilidadeRepository.findByUuid(req.habilidadeUuid())
                            .orElseThrow(() -> new ResourceNotFoundException("Habilidade", req.habilidadeUuid()));

                    boolean jaExiste = vaga.getVagaHabilidades().stream()
                            .anyMatch(vh -> vh.getHabilidade().equals(habilidade));

                    return jaExiste ? null : VagaHabilidade.builder()
                            .vaga(vaga)
                            .habilidade(habilidade)
                            .peso(req.peso())
                            .build();
                })
                .filter(Objects::nonNull)
                .toList();

        vaga.getVagaHabilidades().addAll(novasVagaHabilidades);
        vagaRepository.save(vaga);

        return vagaMapper.toDto(vaga);
    }
}
