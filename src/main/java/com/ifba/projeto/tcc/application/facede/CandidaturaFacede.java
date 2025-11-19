package com.ifba.projeto.tcc.application.facede;

import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidaturaDetalharResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidaturaResponseDTO;
import com.ifba.projeto.tcc.application.usecase.candidato.ListarCandidatosUseCase;
import com.ifba.projeto.tcc.application.usecase.candidatura.DetalharCandidaturaUseCase;
import com.ifba.projeto.tcc.application.usecase.candidatura.ListarCandidaturasPorScoreUseCase;
import com.ifba.projeto.tcc.application.usecase.candidatura.ListarCandidaturasUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidaturaFacede {
    private final ListarCandidaturasUseCase listarCandidaturasUseCase;
    private final ListarCandidaturasPorScoreUseCase listarCandidaturasPorScoreUseCase;
    private final DetalharCandidaturaUseCase detalharCandidaturaUseCase;

    public Page<CandidaturaResponseDTO> listarCandidatos(Long vagaId, Pageable pageable) {
        return listarCandidaturasUseCase.executar(vagaId, pageable);
    }
    
    public Page<CandidaturaResponseDTO> listarCandidaturasPorScore(Long vagaId, Pageable pageable) {
        return listarCandidaturasPorScoreUseCase.executar(vagaId, pageable);
    }
    
    public CandidaturaDetalharResponseDTO detalharCandidatura(Long id) {
        return detalharCandidaturaUseCase.executar(id);
    }
}

