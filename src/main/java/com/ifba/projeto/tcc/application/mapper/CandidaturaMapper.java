package com.ifba.projeto.tcc.application.mapper;

import com.ifba.projeto.tcc.application.dto.response.CandidaturaResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Candidatura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CandidatoMapper.class})
public interface CandidaturaMapper {

    @Mapping(target = "uuidCandidatura", source = "uuid")
    @Mapping(target = "candidato", source = "curriculo.candidato")
    CandidaturaResponseDTO toDto(Candidatura candidatura);
}
