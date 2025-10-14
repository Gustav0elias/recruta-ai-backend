package com.ifba.projeto.tcc.application.mapper;


import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResumoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.ExperienciaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResumoResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Curriculo;
import com.ifba.projeto.tcc.domain.entity.Experiencia;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CandidatoMapper {

    @Mapping(target = "habilidades", source = "habilidades")
    @Mapping(target = "curriculos", source = "curriculos")
    CandidatoResponseDTO toDto(Candidato candidato);


    HabilidadeResumoResponseDTO toHabilidadeResumoDto(Habilidade habilidade);

    CurriculoResumoResponseDTO toCurriculoResumoDto(Curriculo curriculo);


    Set<HabilidadeResumoResponseDTO> toHabilidadeResumoDtoSet(Set<Habilidade> habilidades);

    Set<CurriculoResumoResponseDTO> toCurriculoResumoDtoSet(Set<Curriculo> curriculos);
    List<ExperienciaResponseDTO> toExperienciasDto (List<Experiencia> experiencias);
}