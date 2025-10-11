package com.ifba.projeto.tcc.application.mapper;

import com.ifba.projeto.tcc.application.dto.request.CurriculoRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Curriculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurriculoMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "caminhoArquivo", source = "caminhoArquivo")
    @Mapping(target = "ativo", source = "ativo")
    @Mapping(target = "criadoEm", source = "criadoEm")
    @Mapping(target = "candidatoUuid", source = "candidato.uuid")
    CurriculoResponseDTO toDto(Curriculo entity);

    Curriculo toEntity(CurriculoRequestDTO dto);
}
