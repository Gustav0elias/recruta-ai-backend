package com.ifba.projeto.tcc.application.mapper;

import com.ifba.projeto.tcc.application.dto.request.VagaRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResumoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.entity.Vaga;
import com.ifba.projeto.tcc.domain.entity.VagaHabilidade;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VagaMapper {

    VagaMapper INSTANCE = Mappers.getMapper(VagaMapper.class);


    Vaga toModel(VagaRequestDTO dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "titulo", source = "titulo")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "nivelExperiencia", source = "nivelExperiencia")
    @Mapping(target = "criadoEm", source = "criadoEm")
    @Mapping(target = "habilidades", source = "vagaHabilidades", qualifiedByName = "vagaHabilidadesToHabilidadeResumo")
    @Mapping(target = "quantidadeCandidaturas", ignore = true)
    VagaResponseDTO toDto(Vaga vaga);
    
    @Named("vagaHabilidadesToHabilidadeResumo")
    default Set<HabilidadeResumoResponseDTO> vagaHabilidadesToHabilidadeResumo(Set<VagaHabilidade> vagaHabilidades) {
        if (vagaHabilidades == null || vagaHabilidades.isEmpty()) {
            return null;
        }
        return vagaHabilidades.stream()
                .map(vh -> {
                    Habilidade h = vh.getHabilidade();
                    if (h == null) {
                        return null;
                    }
                    return new HabilidadeResumoResponseDTO(
                            h.getUuid(),
                            h.getNome(),
                            h.getTipo() != null ? h.getTipo().name() : null
                    );
                })
                .filter(h -> h != null)
                .collect(Collectors.toSet());
    }
    
    void updateEntityFromDto(VagaRequestDTO dto, @MappingTarget Vaga entity);
}