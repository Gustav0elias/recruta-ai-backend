package com.ifba.projeto.tcc.application.mapper;


import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidatoResumoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidatoRetornoCurriculoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResumoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.ExperienciaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResumoResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Curriculo;
import com.ifba.projeto.tcc.domain.entity.Experiencia;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidatoMapper {

    @Mapping(target = "habilidades", source = "habilidades")
    @Mapping(target = "curriculos", source = "curriculos")
    CandidatoResponseDTO toDto(Candidato candidato);

    @Mapping(target = "candidatoId", source = "id")
    CandidatoResumoResponseDTO toResumoDto(Candidato candidato);

    default CandidatoRetornoCurriculoResponseDTO toResumeDto(Candidato candidato) {
        if (candidato == null) {
            return null;
        }
        return new CandidatoRetornoCurriculoResponseDTO(
                candidato.getId(),
                candidato.getUuid(),
                candidato.getNome(),
                candidato.getEmail(),
                candidato.getTelefone(),
                candidato.getLinkedin(),
                candidato.getCriadoEm()
        );
    }

    HabilidadeResumoResponseDTO toHabilidadeResumoDto(Habilidade habilidade);

    CurriculoResumoResponseDTO toCurriculoResumoDto(Curriculo curriculo);


    Set<HabilidadeResumoResponseDTO> toHabilidadeResumoDtoSet(Set<Habilidade> habilidades);

    Set<CurriculoResumoResponseDTO> toCurriculoResumoDtoSet(Set<Curriculo> curriculos);
    
    default Set<CurriculoResumoResponseDTO> toCurriculoResumoDtoSetFromList(List<Curriculo> curriculos) {
        if (curriculos == null) {
            return null;
        }
        return curriculos.stream()
                .map(this::toCurriculoResumoDto)
                .collect(java.util.stream.Collectors.toSet());
    }
    
    default List<ExperienciaResponseDTO> toExperienciasDto(List<Experiencia> experiencias) {
        if (experiencias == null) {
            return null;
        }
        return experiencias.stream()
                .map(this::toExperienciaDto)
                .collect(java.util.stream.Collectors.toList());
    }
    
    default ExperienciaResponseDTO toExperienciaDto(Experiencia experiencia) {
        if (experiencia == null) {
            return null;
        }
        return new ExperienciaResponseDTO(
                experiencia.getEmpresa(),
                experiencia.getCargo(),
                experiencia.getDescricao(),
                experiencia.getDataInicio() != null ? experiencia.getDataInicio().toString() : null,
                experiencia.getDataFim() != null ? experiencia.getDataFim().toString() : null
        );
    }
}