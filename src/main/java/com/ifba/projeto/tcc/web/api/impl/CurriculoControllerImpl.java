package com.ifba.projeto.tcc.web.api.impl;

import com.ifba.projeto.tcc.application.dto.request.CurriculoRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.application.facede.CurriculoFacede;
import com.ifba.projeto.tcc.web.api.CurriculoController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class CurriculoControllerImpl implements CurriculoController {
    private final CurriculoFacede curriculoFacede;

    @Override
    public ResponseEntity<CurriculoResponseDTO> salvarCurriculo(MultipartFile arquivo, Long vagaId) {
        CurriculoRequestDTO dto = new CurriculoRequestDTO(arquivo);
        CurriculoResponseDTO response = curriculoFacede.criarCurriculo(dto, vagaId);
        return ResponseEntity.ok(response);
    }
}
