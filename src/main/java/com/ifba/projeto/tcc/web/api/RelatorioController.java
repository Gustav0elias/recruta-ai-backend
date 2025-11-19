package com.ifba.projeto.tcc.web.api;

import com.ifba.projeto.tcc.application.dto.response.DistribuicaoScoreResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.DistribuicaoScoreVagaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioCandidaturasPorPeriodoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioCandidaturasVagaPorPeriodoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioEstatisticasGeraisResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioEstatisticasVagaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioHabilidadesMaisRequisitadasResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioTopCandidatosVagaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioVagasMaisProcuradasResponseDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/api/v01/relatorio")
public interface RelatorioController {
    
    @GetMapping("/estatisticas-gerais")
    ResponseEntity<RelatorioEstatisticasGeraisResponseDTO> obterEstatisticasGerais();
    
    @GetMapping("/candidaturas-por-periodo")
    ResponseEntity<RelatorioCandidaturasPorPeriodoResponseDTO> obterCandidaturasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim
    );
    
    @GetMapping("/habilidades-mais-requisitadas")
    ResponseEntity<RelatorioHabilidadesMaisRequisitadasResponseDTO> obterHabilidadesMaisRequisitadas(
            @RequestParam(required = false, defaultValue = "10") Integer limite
    );
    
    @GetMapping("/distribuicao-score")
    ResponseEntity<DistribuicaoScoreResponseDTO> obterDistribuicaoScore();
    
    @GetMapping("/vagas-mais-procuradas")
    ResponseEntity<RelatorioVagasMaisProcuradasResponseDTO> obterVagasMaisProcuradas(
            @RequestParam(required = false, defaultValue = "10") Integer limite
    );
    
    @GetMapping("/vaga/{vagaId}/estatisticas")
    ResponseEntity<RelatorioEstatisticasVagaResponseDTO> obterEstatisticasVaga(
            @PathVariable Long vagaId
    );
    
    @GetMapping("/vaga/{vagaId}/candidaturas-por-periodo")
    ResponseEntity<RelatorioCandidaturasVagaPorPeriodoResponseDTO> obterCandidaturasVagaPorPeriodo(
            @PathVariable Long vagaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim
    );
    
    @GetMapping("/vaga/{vagaId}/distribuicao-score")
    ResponseEntity<DistribuicaoScoreVagaResponseDTO> obterDistribuicaoScoreVaga(
            @PathVariable Long vagaId
    );
    
    @GetMapping("/vaga/{vagaId}/top-candidatos")
    ResponseEntity<RelatorioTopCandidatosVagaResponseDTO> obterTopCandidatosVaga(
            @PathVariable Long vagaId,
            @RequestParam(required = false, defaultValue = "10") Integer limite
    );
}

