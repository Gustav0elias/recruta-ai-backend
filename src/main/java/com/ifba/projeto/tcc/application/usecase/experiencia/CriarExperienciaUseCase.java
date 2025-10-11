package com.ifba.projeto.tcc.application.usecase.experiencia;

import com.ifba.projeto.tcc.application.dto.response.ExperienciaResponseDTO;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Experiencia;

import java.util.List;

public interface CriarExperienciaUseCase {
    List<Experiencia> executar (List<ExperienciaResponseDTO> experiencias, Candidato candidato);
}
