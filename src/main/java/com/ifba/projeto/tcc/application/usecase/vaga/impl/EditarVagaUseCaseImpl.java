package com.ifba.projeto.tcc.application.usecase.vaga.impl;

import com.ifba.projeto.tcc.application.dto.request.VagaRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import com.ifba.projeto.tcc.application.mapper.VagaMapper;
import com.ifba.projeto.tcc.application.usecase.vaga.EditarVagaUseCase;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.entity.Vaga;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class EditarVagaUseCaseImpl implements EditarVagaUseCase {

    private final VagaRepository repository;
    private final HabilidadeRepository habilidadeRepository;
    private final VagaMapper mapper;

    @Override
    @Transactional
    public VagaResponseDTO executar(VagaRequestDTO vagaDTO, Long id) {
        log.info("Iniciando atualização da vaga com ID {}", id);

        Vaga vagaExistente = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Vaga com ID {} não encontrada", id);
                    return new ResourceNotFoundException("Vaga", id);
                });

        mapper.updateEntityFromDto(vagaDTO, vagaExistente);

        vagaDTO.habilidades().forEach(hp -> {
            Habilidade habilidade = habilidadeRepository.findByUuid(hp.habilidadeUuid())
                    .orElseThrow(() -> {
                        log.error("Habilidade com UUID {} não encontrada", hp.habilidadeUuid());
                        return new ResourceNotFoundException("Habilidade", hp.habilidadeUuid());
                    });

            vagaExistente.getVagaHabilidades().stream()
                    .filter(vh -> vh.getHabilidade().equals(habilidade))
                    .findFirst()
                    .ifPresentOrElse(
                            vh -> vh.setPeso(hp.peso()),
                            () -> vagaExistente.adicionarHabilidade(habilidade, hp.peso())
                    );
        });

        Vaga vagaAtualizada = repository.save(vagaExistente);
        log.info("Vaga com ID {} atualizada com sucesso", id);
        return mapper.toDto(vagaAtualizada);
    }
}
