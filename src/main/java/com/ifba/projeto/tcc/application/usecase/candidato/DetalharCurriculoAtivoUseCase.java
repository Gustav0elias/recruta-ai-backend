package com.ifba.projeto.tcc.application.usecase.candidato;

import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;

import java.util.Optional;

public interface DetalharCurriculoAtivoUseCase {
   CurriculoResponseDTO executar(Long candidatoId);
}
