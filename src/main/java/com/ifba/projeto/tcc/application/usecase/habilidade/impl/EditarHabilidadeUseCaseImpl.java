package com.ifba.projeto.tcc.application.usecase.habilidade.impl;

import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.application.helper.HabilidadeNomeNormalizer;
import com.ifba.projeto.tcc.application.mapper.HabilidadeMapper;
import com.ifba.projeto.tcc.application.usecase.habilidade.EditarHabilidadeUseCase;
import com.ifba.projeto.tcc.domain.exception.BusinessException;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EditarHabilidadeUseCaseImpl implements EditarHabilidadeUseCase {

    private final HabilidadeRepository repository;
    private final HabilidadeNomeNormalizer habilidadeNomeNormalizer;
    private final HabilidadeMapper mapper = HabilidadeMapper.INSTANCE;

    @Override
    public HabilidadeResponseDTO executar(HabilidadeRequestDTO habilidadeDTO, Long id) {
        Habilidade habilidadeExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habilidade", id));

        String nomeNormalizado = habilidadeNomeNormalizer.canonicalizar(habilidadeDTO.nome());
        repository.findAll().stream()
                .filter(h -> !h.getId().equals(id))
                .filter(h -> habilidadeNomeNormalizer.saoEquivalentes(h.getNome(), nomeNormalizado))
                .findFirst()
                .ifPresent(h -> {
                    throw new BusinessException("Ja existe uma habilidade cadastrada com este nome.");
                });

        habilidadeExistente.setNome(nomeNormalizado);
        habilidadeExistente.setDescricao(habilidadeDTO.descricao());
        habilidadeExistente.setTipo(habilidadeDTO.tipo());

        repository.save(habilidadeExistente);

        return mapper.toDTO(habilidadeExistente);
    }
}
