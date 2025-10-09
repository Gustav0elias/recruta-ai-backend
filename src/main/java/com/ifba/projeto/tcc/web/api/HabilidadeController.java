package com.ifba.projeto.tcc.web.api;

import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v01/habilidade")
public interface HabilidadeController {
    @PostMapping
    ResponseEntity<HabilidadeResponseDTO> criarHabilidade (@Valid @RequestBody HabilidadeRequestDTO dto);

    @GetMapping
    ResponseEntity<Page<HabilidadeResponseDTO>> listarHabilidades(Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<HabilidadeResponseDTO> editarHabilidade(@PathVariable Long id, @Valid @RequestBody HabilidadeRequestDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> removerHabilidade(@PathVariable Long id);
}
