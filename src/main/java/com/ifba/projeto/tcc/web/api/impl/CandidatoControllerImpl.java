package com.ifba.projeto.tcc.web.api.impl;

import com.ifba.projeto.tcc.application.dto.request.CurriculoRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidatoResumoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.application.facede.CandidatoFacede;
import com.ifba.projeto.tcc.web.api.CandidatoController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class CandidatoControllerImpl implements CandidatoController {
    private final CandidatoFacede candidatoFacede;
    @Override
    public ResponseEntity<CandidatoResumoResponseDTO> atualizarCurriculo(MultipartFile arquivo, Long vagaId) {

        CandidatoResumoResponseDTO response = candidatoFacede.atualizarCurriculoCandidato(vagaId, arquivo);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<CandidatoResponseDTO>> listarCandidatos(Long vagaId, Pageable pageable) {
        Page<CandidatoResponseDTO> page = candidatoFacede.listarCandidatos(vagaId, pageable);
        return ResponseEntity.ok(page);
    }
    public ResponseEntity<Page<CandidatoResponseDTO>> listarCandidatosGeral(Pageable pageable) {
        Page<CandidatoResponseDTO> page = candidatoFacede.listarCandidatosGeral(pageable);
        return ResponseEntity.ok(page);
    }
}
