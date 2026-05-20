package com.ifba.projeto.tcc.application.usecase.habilidade.impl;

import com.ifba.projeto.tcc.application.dto.response.HabilidadeParaCurriculoResponseDTO;
import com.ifba.projeto.tcc.application.helper.HabilidadeNomeNormalizer;
import com.ifba.projeto.tcc.application.usecase.habilidade.CriarHabilidadeParaCandidatoUseCase;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.entity.TipoHabilidade;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CriarHabilidadeParaCandidatoUseCaseImpl implements CriarHabilidadeParaCandidatoUseCase {
    private final HabilidadeRepository habilidadeRepository;
    private final HabilidadeNomeNormalizer habilidadeNomeNormalizer;

    @Override
    public Set<Habilidade> executar(List<HabilidadeParaCurriculoResponseDTO> habilidadesDTO) {
        List<Habilidade> habilidadesExistentes = habilidadeRepository.findAll();

        List<String> habilidadesNormalizadas = habilidadesDTO.stream()
                .map(HabilidadeParaCurriculoResponseDTO::nome)
                .map(habilidadeNomeNormalizer::canonicalizar)
                .filter(nome -> !nome.isBlank())
                .toList();

        List<String> unicas = new ArrayList<>();
        for (String nome : habilidadesNormalizadas) {
            boolean duplicada = unicas.stream()
                    .anyMatch(existente -> habilidadeNomeNormalizer.saoEquivalentes(existente, nome));
            if (!duplicada) {
                unicas.add(nome);
            }
        }

        Set<Habilidade> resultado = new HashSet<>();
        for (String nome : unicas) {
            Habilidade equivalente = habilidadesExistentes.stream()
                    .filter(h -> habilidadeNomeNormalizer.saoEquivalentes(h.getNome(), nome))
                    .findFirst()
                    .orElse(null);

            if (equivalente != null) {
                resultado.add(equivalente);
                continue;
            }

            Habilidade nova = new Habilidade();
            nova.setNome(nome);
            nova.setDescricao("teste");
            nova.setTipo(TipoHabilidade.HABILIDADE_TECNICA);
            Habilidade salva = habilidadeRepository.save(nova);
            habilidadesExistentes.add(salva);
            resultado.add(salva);
        }

        return resultado;
    }
}
