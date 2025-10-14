package com.ifba.projeto.tcc.web.api;

import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidatoResumoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v01/candidato")
public interface
CandidatoController {
    @PostMapping("/curriculo/atualizar")
    ResponseEntity<CandidatoResumoResponseDTO> atualizarCurriculo(
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam(value = "candidatoId", required = false) Long candidatoid);
    @GetMapping("/{vagaId}")
    ResponseEntity<Page<CandidatoResponseDTO>> listarCandidatos(@PathVariable Long vagaId, Pageable pageable);
    @GetMapping("/geral")
    ResponseEntity<Page<CandidatoResponseDTO>> listarCandidatosGeral(Pageable pageable);
}
