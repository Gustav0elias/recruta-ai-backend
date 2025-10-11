package com.ifba.projeto.tcc.application.usecase.habilidade.impl;

import com.ifba.projeto.tcc.application.dto.response.HabilidadeParaCurriculoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.application.usecase.habilidade.CriarHabilidadeParaCandidatoUseCase;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.entity.TipoHabilidade;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CriarHabilidadeParaCandidatoUseCaseImpl implements CriarHabilidadeParaCandidatoUseCase {
    private final HabilidadeRepository habilidadeRepository;
    @Override
    public Set<Habilidade> executar(List<HabilidadeParaCurriculoResponseDTO> habilidadesDTO) {
        return habilidadesDTO.stream()
                .map(h -> habilidadeRepository.findByNome(h.nome())
                        .orElseGet(() -> {
                            Habilidade nova = new Habilidade();
                            nova.setNome(h.nome());
                            nova.setDescricao("teste");
                            nova.setTipo(TipoHabilidade.HABILIDADE_TECNICA);
                            return habilidadeRepository.save(nova);
                        })
                ).collect(Collectors.toSet());
    }
}
