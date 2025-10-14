package com.ifba.projeto.tcc.application.usecase.experiencia.impl;

import com.ifba.projeto.tcc.application.dto.response.ExperienciaResponseDTO;
import com.ifba.projeto.tcc.application.usecase.experiencia.CriarExperienciaUseCase;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Experiencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CriarExperienciaUseCaseImpl implements CriarExperienciaUseCase {
    @Override
    public List<Experiencia> executar(List<ExperienciaResponseDTO> experiencias, Candidato candidato) {
        return experiencias.stream().map(experienciaResponseDTO -> {
            Experiencia experiencia = new Experiencia();
            experiencia.setCargo(experienciaResponseDTO.cargo());
            experiencia.setEmpresa(experienciaResponseDTO.empresa());
            experiencia.setDescricao(experienciaResponseDTO.descricao());
            experiencia.setDataInicio(LocalDate.parse(experienciaResponseDTO.dataInicio()));
            experiencia.setDataFim(Optional.ofNullable(experienciaResponseDTO.dataFim()).map(LocalDate::parse) .orElse(null));
            experiencia.setCandidato(candidato);
            return experiencia;
        }).collect(Collectors.toList());
    }
}
