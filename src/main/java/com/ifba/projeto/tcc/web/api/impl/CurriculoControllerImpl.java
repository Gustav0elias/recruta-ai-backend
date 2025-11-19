package com.ifba.projeto.tcc.web.api.impl;

import com.ifba.projeto.tcc.application.dto.request.CurriculoRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.application.facede.CurriculoFacede;
import com.ifba.projeto.tcc.web.api.CurriculoController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Override
    public ResponseEntity<CurriculoResponseDTO> detalharCurriculoAtivo(   Long idCandidato){
        CurriculoResponseDTO curriculoResponseDTO =  curriculoFacede.detalharCurriculoAtivo(idCandidato);;
        return ResponseEntity.ok(curriculoResponseDTO);
    }

    @Override
    public ResponseEntity<Page<CurriculoResponseDTO>> listarCurriculosPorCandidato(Long idCandidato, Pageable pageable){
        Page<CurriculoResponseDTO> page = curriculoFacede.listarCurriculosPorCandidato(idCandidato, pageable);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<Void> deletarCurriculo(Long idCurriculo) {
        curriculoFacede.deletarCurriculo(idCurriculo);
        return ResponseEntity.noContent().build();
    }
}
