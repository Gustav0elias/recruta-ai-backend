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
import com.ifba.projeto.tcc.application.usecase.curriculo.ExtrairDadosCurriculoUseCase;
import com.ifba.projeto.tcc.application.usecase.vaga.VincularCurriculoAVagaUseCase;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Curriculo;
import com.ifba.projeto.tcc.domain.repository.CurriculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CriarCurriculoUseCaseImpl implements CriarCurriculoUseCase {

    private final CurriculoRepository curriculoRepository;
    private final SalvarCurriculoService SalvarCurriculoService;
    private final CurriculoMapper mapper;
    private final CurriculoParserService parserService;
    private final PdfExtractionService pdfExtractionService; // <- novo
    private final GerenciarCriarCandidatoUseCase gerenciarCandidatoUseCase;
    private final VincularCurriculoAVagaUseCase vincularCurriculoAVagaUseCase;
    private final ExtrairDadosCurriculoUseCase extrairDadosUseCase;

    @Override
    public CurriculoResponseDTO executar(CurriculoRequestDTO dto, Long idVaga) {
        String caminhoArquivo = SalvarCurriculoService.salvarCurriculo(dto.arquivo());

        Curriculo curriculo = new Curriculo();
        curriculo.setUuid(UUID.randomUUID());
        curriculo.setCaminhoArquivo(caminhoArquivo);
        curriculo.setAtivo(true);
        curriculo.setCriadoEm(LocalDateTime.now());
        curriculo = curriculoRepository.save(curriculo);

        File arquivoPdf = new File(caminhoArquivo);
        CurriculoExtraidoResponseDTO dadosExtraidos = extrairDadosUseCase.executar(arquivoPdf);

        Candidato candidato = gerenciarCandidatoUseCase.executar(dadosExtraidos);

        curriculo.setCandidato(candidato);
        curriculo = curriculoRepository.save(curriculo);
        vincularCurriculoAVagaUseCase.executar(curriculo, idVaga);

        CandidatoCurriculoResponseDTO candidatoDto = new CandidatoCurriculoResponseDTO(
                candidato.getUuid(),
                candidato.getNome(),
                candidato.getEmail(),
                candidato.getTelefone(),
                candidato.getLinkedin(),
                candidato.getCriadoEm(),
                candidato.getExperiencias().stream()
                        .map(e -> new ExperienciaResponseDTO(e.getEmpresa(), e.getCargo(),e.getDescricao(), e.getDataInicio() != null ? e.getDataInicio().toString() : null,  e.getDataFim() != null ? e.getDataInicio().toString() : null))
                        .toList()
        );

        return new CurriculoResponseDTO(
                curriculo.getId(),
                curriculo.getUuid(),
                curriculo.getCaminhoArquivo(),
                curriculo.getAtivo(),
                curriculo.getCriadoEm(),
                candidatoDto
        );
    }

}
