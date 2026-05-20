package com.ifba.projeto.tcc.application.usecase.habilidade.impl;

import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.application.helper.HabilidadeNomeNormalizer;
import com.ifba.projeto.tcc.application.mapper.HabilidadeMapper;
import com.ifba.projeto.tcc.application.usecase.habilidade.CriarHabilidadeUseCase;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CriarHabilidadeUseCaseImpl implements CriarHabilidadeUseCase {

    private final HabilidadeRepository repository;
    private final HabilidadeMapper mapper;
    private final HabilidadeNomeNormalizer habilidadeNomeNormalizer;

    @Override
    public HabilidadeResponseDTO executar(HabilidadeRequestDTO habilidade) {
        String nomeNormalizado = habilidadeNomeNormalizer.canonicalizar(habilidade.nome());
        Habilidade existente = repository.findAll().stream()
                .filter(h -> habilidadeNomeNormalizer.saoEquivalentes(h.getNome(), nomeNormalizado))
                .findFirst()
                .orElse(null);

        if (existente != null) {
            return mapper.toDTO(existente);
        }

        HabilidadeRequestDTO normalizado = new HabilidadeRequestDTO(
                nomeNormalizado,
                habilidade.descricao().trim(),
                habilidade.tipo()
        );

        Habilidade habilidadeEntity = mapper.toModel(normalizado);
        repository.save(habilidadeEntity);
        return mapper.toDTO(habilidadeEntity);
    }
}
