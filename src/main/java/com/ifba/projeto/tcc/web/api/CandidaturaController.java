package com.ifba.projeto.tcc.web.api;

import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidaturaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v01/candidatura")
public interface
CandidaturaController {
    @GetMapping("/{vagaId}")
    ResponseEntity<Page<CandidaturaResponseDTO>> listarCandidaturas(@PathVariable Long vagaId, Pageable pageable);
    @GetMapping("/score/{vagaId}")
    ResponseEntity<Page<CandidaturaResponseDTO>> listarCandidaturasPorScore(@PathVariable Long vagaId, Pageable pageable);
}
