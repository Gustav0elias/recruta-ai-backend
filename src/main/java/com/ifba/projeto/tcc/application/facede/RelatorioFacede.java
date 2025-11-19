package com.ifba.projeto.tcc.application.facede;

import com.ifba.projeto.tcc.application.dto.response.DistribuicaoScoreResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.DistribuicaoScoreVagaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioCandidaturasPorPeriodoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioCandidaturasVagaPorPeriodoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioEstatisticasGeraisResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioEstatisticasVagaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioHabilidadesMaisRequisitadasResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioTopCandidatosVagaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.RelatorioVagasMaisProcuradasResponseDTO;
import com.ifba.projeto.tcc.application.usecase.relatorio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RelatorioFacede {
    private final RelatorioEstatisticasGeraisUseCase relatorioEstatisticasGeraisUseCase;
    private final RelatorioCandidaturasPorPeriodoUseCase relatorioCandidaturasPorPeriodoUseCase;
    private final RelatorioHabilidadesMaisRequisitadasUseCase relatorioHabilidadesMaisRequisitadasUseCase;
    private final RelatorioDistribuicaoScoreUseCase relatorioDistribuicaoScoreUseCase;
    private final RelatorioVagasMaisProcuradasUseCase relatorioVagasMaisProcuradasUseCase;
    private final RelatorioEstatisticasVagaUseCase relatorioEstatisticasVagaUseCase;
    private final RelatorioCandidaturasVagaPorPeriodoUseCase relatorioCandidaturasVagaPorPeriodoUseCase;
    private final RelatorioDistribuicaoScoreVagaUseCase relatorioDistribuicaoScoreVagaUseCase;
    private final RelatorioTopCandidatosVagaUseCase relatorioTopCandidatosVagaUseCase;

    public RelatorioEstatisticasGeraisResponseDTO obterEstatisticasGerais() {
        return relatorioEstatisticasGeraisUseCase.executar();
    }

    public RelatorioCandidaturasPorPeriodoResponseDTO obterCandidaturasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return relatorioCandidaturasPorPeriodoUseCase.executar(dataInicio, dataFim);
    }

    public RelatorioHabilidadesMaisRequisitadasResponseDTO obterHabilidadesMaisRequisitadas(Integer limite) {
        return relatorioHabilidadesMaisRequisitadasUseCase.executar(limite);
    }

    public DistribuicaoScoreResponseDTO obterDistribuicaoScore() {
        return relatorioDistribuicaoScoreUseCase.executar();
    }

    public RelatorioVagasMaisProcuradasResponseDTO obterVagasMaisProcuradas(Integer limite) {
        return relatorioVagasMaisProcuradasUseCase.executar(limite);
    }

    public RelatorioEstatisticasVagaResponseDTO obterEstatisticasVaga(Long vagaId) {
        return relatorioEstatisticasVagaUseCase.executar(vagaId);
    }

    public RelatorioCandidaturasVagaPorPeriodoResponseDTO obterCandidaturasVagaPorPeriodo(Long vagaId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return relatorioCandidaturasVagaPorPeriodoUseCase.executar(vagaId, dataInicio, dataFim);
    }

    public DistribuicaoScoreVagaResponseDTO obterDistribuicaoScoreVaga(Long vagaId) {
        return relatorioDistribuicaoScoreVagaUseCase.executar(vagaId);
    }

    public RelatorioTopCandidatosVagaResponseDTO obterTopCandidatosVaga(Long vagaId, Integer limite) {
        return relatorioTopCandidatosVagaUseCase.executar(vagaId, limite);
    }
}

