package com.ifba.projeto.tcc.application.mapper;

import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.request.VagaRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Vaga;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VagaMapper {
    VagaMapper INSTANCE = Mappers.getMapper(VagaMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "habilidades", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    Vaga toModel (VagaRequestDTO dto);
    VagaResponseDTO toDto (Vaga vaga);

    void updateEntityFromDto(VagaRequestDTO dto, @MappingTarget Vaga entity);

}
