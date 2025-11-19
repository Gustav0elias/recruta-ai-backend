package com.ifba.projeto.tcc.application.usecase.candidatura.impl;

import com.ifba.projeto.tcc.application.dto.response.CandidaturaDetalharResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.ScoreResponseDTO;
import com.ifba.projeto.tcc.application.mapper.CandidatoMapper;
import com.ifba.projeto.tcc.application.usecase.candidatura.DetalharCandidaturaUseCase;
import com.ifba.projeto.tcc.domain.entity.TipoAnalise;
import com.ifba.projeto.tcc.domain.repository.AnaliseRepository;
import com.ifba.projeto.tcc.domain.repository.CandidatoRepository;
import com.ifba.projeto.tcc.domain.repository.CandidaturaRepository;
import com.ifba.projeto.tcc.domain.repository.CurriculoRepository;
import com.ifba.projeto.tcc.domain.repository.ExperienciaRepository;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import com.ifba.projeto.tcc.domain.exception.BusinessException;
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetalharCandidaturaUseCaseImpl implements DetalharCandidaturaUseCase {
    private final CandidatoRepository candidatoRepository;
    private final CurriculoRepository curriculoRepository;
    private final AnaliseRepository analiseRepository;
    private final ExperienciaRepository experienciaRepository;
    private final HabilidadeRepository habilidadeRepository;
    private final CandidaturaRepository candidaturaRepository;
    private final CandidatoMapper candidatoMapper;

    @Override
    public CandidaturaDetalharResponseDTO executar(Long candidatoId) {
        var candidato = candidatoRepository.findById(candidatoId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato", candidatoId));
        
        var curriculoAtivo = curriculoRepository.findAtivoByCandidatoId(candidatoId)
                .orElseThrow(() -> new BusinessException("Nenhum currículo ativo encontrado para o candidato"));
        
        var analises = analiseRepository.findByCurriculoId(curriculoAtivo.getId());
        
        var experiencias = experienciaRepository.findByCandidatoId(candidatoId);
        
        var habilidades = habilidadeRepository.findByCandidatoId(candidatoId);
        
        var curriculos = curriculoRepository.findAllByCandidatoId(candidatoId);
        
        var candidatura = candidaturaRepository.findFirstByCurriculoIdOrderByDataCadastroDesc(curriculoAtivo.getId());
        
        Long score = candidatura.map(c -> c.getScore()).orElse(null);
        
        String analiseEspecifica = analises.stream()
                .filter(analise -> analise.getTipoAnalise() == TipoAnalise.ESPECIFICA_CURRICULO)
                .findFirst()
                .map(analise -> analise.getDescricao())
                .orElse(null);
        
        String analiseGeral = analises.stream()
                .filter(analise -> analise.getTipoAnalise() == TipoAnalise.GERAL)
                .findFirst()
                .map(analise -> analise.getDescricao())
                .orElse(null);
        
        return new CandidaturaDetalharResponseDTO(
                candidato.getId(),
                candidato.getUuid(),
                candidato.getNome(),
                candidato.getEmail(),
                candidato.getTelefone(),
                candidato.getLinkedin(),
                candidato.getCriadoEm(),
                candidatoMapper.toHabilidadeResumoDtoSet(habilidades),
                candidatoMapper.toExperienciasDto(experiencias),
                candidatoMapper.toCurriculoResumoDtoSetFromList(curriculos),
                new ScoreResponseDTO(
                        score,
                        analiseEspecifica,
                        analiseGeral
                )
        );
    }
}

