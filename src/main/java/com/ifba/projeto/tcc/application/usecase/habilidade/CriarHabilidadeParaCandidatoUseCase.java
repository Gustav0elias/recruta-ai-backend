package com.ifba.projeto.tcc.application.usecase.habilidade;

import com.ifba.projeto.tcc.application.dto.response.HabilidadeParaCurriculoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Habilidade;

import java.util.List;
import java.util.Set;

public interface CriarHabilidadeParaCandidatoUseCase {
    Set<Habilidade> executar (List<HabilidadeParaCurriculoResponseDTO>habilidadesDTO);
}
