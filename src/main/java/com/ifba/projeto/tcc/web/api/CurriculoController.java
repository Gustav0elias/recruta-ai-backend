package com.ifba.projeto.tcc.web.api;

import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v01/curriculo")

public interface CurriculoController {
    @PostMapping("/salvar")
    ResponseEntity<CurriculoResponseDTO> salvarCurriculo(
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam(value = "idVaga", required = false) Long idVaga);

    @GetMapping("/detalhar/ativo/{idCandidato}")
    ResponseEntity<CurriculoResponseDTO> detalharCurriculoAtivo(
            @PathVariable(value = "idCandidato") Long idCandidato);
    @GetMapping("/listar/{idCandidato}")
    ResponseEntity<Page<CurriculoResponseDTO>> listarCurriculosPorCandidato(
            @PathVariable(value = "idCandidato") Long idCandidato,  Pageable pageable);
    @DeleteMapping("/{idCurriculo}")
    ResponseEntity<Void> deletarCurriculo ( @PathVariable(value = "idCurriculo") Long idCurriculo);
}
