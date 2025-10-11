package com.ifba.projeto.tcc.application.mapper;

import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HabilidadeMapper {
    HabilidadeMapper INSTANCE = Mappers.getMapper(HabilidadeMapper.class);

    Habilidade toModel(HabilidadeRequestDTO dto);
    HabilidadeResponseDTO toDTO(Habilidade entity);

}
