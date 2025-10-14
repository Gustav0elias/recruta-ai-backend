package com.ifba.projeto.tcc.application.usecase.curriculo.impl;

import com.ifba.projeto.tcc.Infra.service.CurriculoParserService;
import com.ifba.projeto.tcc.Infra.service.PdfExtractionService;
import com.ifba.projeto.tcc.Infra.service.SalvarCurriculoService;
import com.ifba.projeto.tcc.application.dto.request.CurriculoRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidatoCurriculoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.ExperienciaResponseDTO;
import com.ifba.projeto.tcc.application.mapper.CurriculoMapper;
import com.ifba.projeto.tcc.application.usecase.candidato.GerenciarCriarCandidatoUseCase;
import com.ifba.projeto.tcc.application.usecase.curriculo.CriarCurriculoUseCase;
import com.ifba.projeto.tcc.application.usecase.curriculo.DesativarCurriculoUseCase;
import com.ifba.projeto.tcc.application.usecase.curriculo.ExtrairDadosCurriculoUseCase;
import com.ifba.projeto.tcc.application.usecase.vaga.VincularCurriculoAVagaUseCase;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Curriculo;
import com.ifba.projeto.tcc.domain.repository.CurriculoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class DesativarCurriculoUseCaseImpl implements DesativarCurriculoUseCase {

    private final CurriculoRepository curriculoRepository;
    @Override
    @Transactional
    public void executar(Long idCandidato) {
        curriculoRepository.desativarCurriculosAtivosPorCandidato(idCandidato);
    }
}
