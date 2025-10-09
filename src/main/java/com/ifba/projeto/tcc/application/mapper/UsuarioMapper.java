package com.ifba.projeto.tcc.application.mapper;

import com.ifba.projeto.tcc.application.dto.request.UsuarioRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.UsuarioRegistroResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.UsuarioResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario toModel(UsuarioRequestDTO dto);

    UsuarioRegistroResponseDTO toDTO(Usuario entity);
}