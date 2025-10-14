package com.ifba.projeto.tcc.web.api.impl;

import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidaturaResponseDTO;
import com.ifba.projeto.tcc.application.facede.CandidatoFacede;
import com.ifba.projeto.tcc.application.facede.CandidaturaFacede;
import com.ifba.projeto.tcc.web.api.CandidatoController;
import com.ifba.projeto.tcc.web.api.CandidaturaController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CandidaturaControllerImpl implements CandidaturaController {
    private final CandidaturaFacede candidaturaFacede;
    @Override
    public ResponseEntity<Page<CandidaturaResponseDTO>> listarCandidaturas(Long vagaId, Pageable pageable) {
        Page<CandidaturaResponseDTO> page = candidaturaFacede.listarCandidatos(vagaId, pageable);
        return ResponseEntity.ok(page);
    }
    @Override
    public ResponseEntity<Page<CandidaturaResponseDTO>> listarCandidaturasPorScore(Long vagaId, Pageable pageable) {
        Page<CandidaturaResponseDTO> page = candidaturaFacede.listarCandidaturasPorScore(vagaId, pageable);
        return ResponseEntity.ok(page);
    }
}
