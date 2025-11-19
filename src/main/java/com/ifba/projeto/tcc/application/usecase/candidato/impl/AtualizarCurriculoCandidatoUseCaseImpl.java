package com.ifba.projeto.tcc.application.usecase.candidato.impl;

import com.ifba.projeto.tcc.Infra.service.SalvarCurriculoService;
import com.ifba.projeto.tcc.application.dto.response.CandidatoResumoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoExtraidoResponseDTO;
import com.ifba.projeto.tcc.application.usecase.candidato.AtualizarCurriculoCandidatoUseCase;
import com.ifba.projeto.tcc.application.usecase.curriculo.DesativarCurriculoUseCase;
import com.ifba.projeto.tcc.application.usecase.curriculo.ExtrairDadosCurriculoUseCase;
import com.ifba.projeto.tcc.application.usecase.curriculo.SalvarCurriculoUseCase;
import com.ifba.projeto.tcc.application.usecase.experiencia.impl.CriarExperienciaUseCaseImpl;
import com.ifba.projeto.tcc.application.usecase.habilidade.CriarHabilidadeParaCandidatoUseCase;
import com.ifba.projeto.tcc.application.usecase.vaga.AtualizarVinculoCurriculoAVagaUseCase;
import com.ifba.projeto.tcc.application.usecase.vaga.VincularCurriculoAVagaUseCase;
import com.ifba.projeto.tcc.domain.entity.Candidato;
import com.ifba.projeto.tcc.domain.entity.Curriculo;
import com.ifba.projeto.tcc.domain.entity.Experiencia;
import com.ifba.projeto.tcc.domain.entity.Habilidade;
import com.ifba.projeto.tcc.domain.repository.CandidatoRepository;
import com.ifba.projeto.tcc.domain.repository.CurriculoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtualizarCurriculoCandidatoUseCaseImpl implements AtualizarCurriculoCandidatoUseCase {
    private final CandidatoRepository candidatoRepository;
    private final DesativarCurriculoUseCase desativarCurriculoUseCase;
    private final SalvarCurriculoUseCase salvarCurriculoUseCase;
    private final ExtrairDadosCurriculoUseCase extrairDadosUseCase;
    private final SalvarCurriculoService SalvarCurriculoService;
    private final AtualizarVinculoCurriculoAVagaUseCase atualizarVinculoCurriculoAVagaUseCase;
    private final CriarHabilidadeParaCandidatoUseCase  criarHabilidadeParaCandidatoUseCase;
    private final CriarExperienciaUseCaseImpl criarExperienciaUseCase;
    @Override
    public CandidatoResumoResponseDTO executar(Long candidatoId, MultipartFile arquivo) {
        Candidato candidato = candidatoRepository.findById(candidatoId).
                orElseThrow(() -> new EntityNotFoundException("Candidato não encontrado"));

        desativarCurriculoUseCase.executar(candidatoId);

        String caminhoArquivo = SalvarCurriculoService.salvarCurriculo(arquivo);
        File arquivoPdf = new File(caminhoArquivo);
        CurriculoExtraidoResponseDTO dadosExtraidos = extrairDadosUseCase.executar(arquivoPdf);

        candidato.setEmail(dadosExtraidos.email());
        candidato.setNome(dadosExtraidos.nome());
        candidato.setTelefone(dadosExtraidos.telefone());

        Set<Habilidade> habilidades = criarHabilidadeParaCandidatoUseCase.executar(dadosExtraidos.habilidades());
        List<Experiencia> experiencias = criarExperienciaUseCase.executar(dadosExtraidos.experiencias(), candidato);

        candidato.setHabilidades(habilidades);
        candidato.setExperiencias(experiencias);

        candidatoRepository.save(candidato);
        Curriculo curriculo = new Curriculo();
        curriculo.setCaminhoArquivo(caminhoArquivo);
        curriculo.setCandidato(candidato);
        curriculo.setAtivo(true);
        salvarCurriculoUseCase.executar(curriculo);
        curriculo.getCandidaturas().forEach(c -> atualizarVinculoCurriculoAVagaUseCase.executar(curriculo, c.getVaga().getId()));

        return new CandidatoResumoResponseDTO(candidato.getId(), candidato.getUuid(),candidato.getNome(), candidato.getEmail(), candidato.getTelefone() );
    }
}
