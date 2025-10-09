package com.ifba.projeto.tcc.web.api;

import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v01/curriculo")

public interface CurriculoController {
    @PostMapping("/salvar")
    public ResponseEntity<CurriculoResponseDTO> salvarCurriculo(
            @RequestParam("arquivo") MultipartFile arquivo);
}
