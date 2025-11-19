package com.ifba.projeto.tcc.application.usecase.habilidade.impl;

import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.application.mapper.HabilidadeMapper;
import com.ifba.projeto.tcc.application.usecase.habilidade.EditarHabilidadeUseCase;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EditarHabilidadeUseCaseImpl implements EditarHabilidadeUseCase {

    private final HabilidadeRepository repository;
    private final HabilidadeMapper mapper = HabilidadeMapper.INSTANCE;

    @Override
    public HabilidadeResponseDTO executar(HabilidadeRequestDTO habilidadeDTO, Long id) {
        Habilidade habilidadeExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habilidade", id));

        habilidadeExistente.setNome(habilidadeDTO.nome());
        habilidadeExistente.setDescricao(habilidadeDTO.descricao());
        habilidadeExistente.setTipo(habilidadeDTO.tipo());

        repository.save(habilidadeExistente);

        return mapper.toDTO(habilidadeExistente);
    }
}