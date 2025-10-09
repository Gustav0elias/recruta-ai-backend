package com.ifba.projeto.tcc.web.api;


import com.ifba.projeto.tcc.application.dto.request.AdicionarHabilidadesRequestDTO;
import com.ifba.projeto.tcc.application.dto.request.VagaRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v01/vaga")
public interface VagaController {

    @PostMapping
    ResponseEntity<VagaResponseDTO> criarVaga(@Valid @RequestBody VagaRequestDTO dto);

    @GetMapping("/{usuarioId}")
    ResponseEntity<Page<VagaResponseDTO>> listarVagas(@PathVariable Long usuarioId, Pageable pageable);

    @GetMapping("/detalhar/{vagaId}")
    ResponseEntity<VagaResponseDTO> detalharVaga(@PathVariable Long vagaId);

    @PutMapping("/{id}")
    ResponseEntity<VagaResponseDTO> editarVaga(@PathVariable Long id, @Valid @RequestBody VagaRequestDTO dto);

    @PostMapping("/habilidades/{vagaId}")
    public ResponseEntity<VagaResponseDTO> adicionarHabilidades( @PathVariable Long vagaId,
            @Valid @RequestBody AdicionarHabilidadesRequestDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> removerVaga(@PathVariable Long id);
}
