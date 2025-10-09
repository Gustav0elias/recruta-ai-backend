package com.ifba.projeto.tcc.application.mapper;

import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Curriculo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurriculoMapper {
    CurriculoResponseDTO toDto(Curriculo curriculo);
}