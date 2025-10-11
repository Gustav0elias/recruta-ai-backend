package com.ifba.projeto.tcc.application.usecase.curriculo.impl;

import com.ifba.projeto.tcc.Infra.service.OllamaCurriculoParserService;
import com.ifba.projeto.tcc.Infra.service.PdfExtractionService;
import com.ifba.projeto.tcc.Infra.service.SalvarCurriculoService;
import com.ifba.projeto.tcc.application.dto.request.CurriculoRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.application.mapper.CurriculoMapper;
import com.ifba.projeto.tcc.application.usecase.candidato.GerenciarCriarCandidatoUseCase;
import com.ifba.projeto.tcc.application.usecase.curriculo.CriarCurriculoUseCase;
import com.ifba.projeto.tcc.domain.entity.Curriculo;
import com.ifba.projeto.tcc.domain.repository.CandidatoRepository;
import com.ifba.projeto.tcc.domain.repository.CurriculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CriarCurriculoUseCaseImpl implements CriarCurriculoUseCase {

    private final CurriculoRepository curriculoRepository;
    private final CandidatoRepository candidatoRepository;
    private final SalvarCurriculoService SalvarCurriculoService;
    private final CurriculoMapper mapper;
    private final OllamaCurriculoParserService parserService;
    private final PdfExtractionService pdfExtractionService; // <- novo
    private final GerenciarCriarCandidatoUseCase gerenciarCandidatoUseCase;
    @Override
    public CurriculoResponseDTO executar(CurriculoRequestDTO dto) {
        String caminhoArquivo = SalvarCurriculoService.salvarCurriculo(dto.arquivo());

        Curriculo curriculo = new Curriculo();
        curriculo.setUuid(UUID.randomUUID());
        curriculo.setCaminhoArquivo(caminhoArquivo);
        curriculo.setAtivo(true);
        curriculo.setCriadoEm(LocalDateTime.now());

        Curriculo curriculoSalvo = curriculoRepository.save(curriculo);
        File arquivoPdf = new File(caminhoArquivo);
        String textoExtraido = pdfExtractionService.extractText(arquivoPdf);
        limparTexto(textoExtraido);
        CurriculoExtraidoResponseDTO dadosExtraidos = parserService.extrairInformacoes(textoExtraido);
        gerenciarCandidatoUseCase.executar(dadosExtraidos);
        return mapper.toDto(curriculoSalvo);
    }
    private String limparTexto(String texto) {
        // Remove caracteres não imprimíveis e substitui múltiplos espaços por um único espaço
        texto = texto.replaceAll("[^\\x20-\\x7E\\n]", " ");
        texto = texto.replaceAll("\\s+", " ").trim();
        return texto;
    }

}
