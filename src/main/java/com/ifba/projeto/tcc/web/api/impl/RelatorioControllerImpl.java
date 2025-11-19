package com.ifba.projeto.tcc.web.api.impl;

import com.ifba.projeto.tcc.application.dto.response.DistribuicaoScoreResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.DistribuicaoScoreVagaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioCandidaturasPorPeriodoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioCandidaturasVagaPorPeriodoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioEstatisticasGeraisResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioEstatisticasVagaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioHabilidadesMaisRequisitadasResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioTopCandidatosVagaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioVagasMaisProcuradasResponseDTO;
import com.ifba.projeto.tcc.application.facede.RelatorioFacede;
import com.ifba.projeto.tcc.web.api.RelatorioController;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class RelatorioControllerImpl implements RelatorioController {
    private final RelatorioFacede relatorioFacede;

    @Override
    public ResponseEntity<RelatorioEstatisticasGeraisResponseDTO> obterEstatisticasGerais() {
        RelatorioEstatisticasGeraisResponseDTO response = relatorioFacede.obterEstatisticasGerais();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RelatorioCandidaturasPorPeriodoResponseDTO> obterCandidaturasPorPeriodo(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim
    ) {
        RelatorioCandidaturasPorPeriodoResponseDTO response = relatorioFacede.obterCandidaturasPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RelatorioHabilidadesMaisRequisitadasResponseDTO> obterHabilidadesMaisRequisitadas(Integer limite) {
        RelatorioHabilidadesMaisRequisitadasResponseDTO response = relatorioFacede.obterHabilidadesMaisRequisitadas(limite);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<DistribuicaoScoreResponseDTO> obterDistribuicaoScore() {
        DistribuicaoScoreResponseDTO response = relatorioFacede.obterDistribuicaoScore();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RelatorioVagasMaisProcuradasResponseDTO> obterVagasMaisProcuradas(Integer limite) {
        RelatorioVagasMaisProcuradasResponseDTO response = relatorioFacede.obterVagasMaisProcuradas(limite);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RelatorioEstatisticasVagaResponseDTO> obterEstatisticasVaga(Long vagaId) {
        RelatorioEstatisticasVagaResponseDTO response = relatorioFacede.obterEstatisticasVaga(vagaId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RelatorioCandidaturasVagaPorPeriodoResponseDTO> obterCandidaturasVagaPorPeriodo(
            Long vagaId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim
    ) {
        RelatorioCandidaturasVagaPorPeriodoResponseDTO response = relatorioFacede.obterCandidaturasVagaPorPeriodo(vagaId, dataInicio, dataFim);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<DistribuicaoScoreVagaResponseDTO> obterDistribuicaoScoreVaga(Long vagaId) {
        DistribuicaoScoreVagaResponseDTO response = relatorioFacede.obterDistribuicaoScoreVaga(vagaId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RelatorioTopCandidatosVagaResponseDTO> obterTopCandidatosVaga(Long vagaId, Integer limite) {
        RelatorioTopCandidatosVagaResponseDTO response = relatorioFacede.obterTopCandidatosVaga(vagaId, limite);
        return ResponseEntity.ok(response);
    }
}

