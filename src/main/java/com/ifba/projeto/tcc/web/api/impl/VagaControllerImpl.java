package com.ifba.projeto.tcc.web.api.impl;

import com.ifba.projeto.tcc.application.dto.request.AdicionarHabilidadesRequestDTO;
import com.ifba.projeto.tcc.application.dto.request.VagaRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import com.ifba.projeto.tcc.application.facede.VagaFacede;
import com.ifba.projeto.tcc.web.api.VagaController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VagaControllerImpl implements VagaController {

    private final VagaFacede vagaServiceFacede;

    @Override
    public ResponseEntity<VagaResponseDTO> criarVaga(@Valid VagaRequestDTO dto) {
        VagaResponseDTO response = vagaServiceFacede.criarVaga(dto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<VagaResponseDTO>> listarVagas(Long usuarioId, Pageable pageable) {
        Page<VagaResponseDTO> page = vagaServiceFacede.listarVagas(usuarioId, pageable);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<VagaResponseDTO> detalharVaga(Long vagaId) {
        VagaResponseDTO response = vagaServiceFacede.detalharVaga(vagaId);
        return ResponseEntity.ok(response);
    }

@Override
    public ResponseEntity<VagaResponseDTO> adicionarHabilidades(Long vagaId,@Valid  AdicionarHabilidadesRequestDTO dto) {
        VagaResponseDTO response = vagaServiceFacede.adicionarHabilidade(vagaId, dto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<VagaResponseDTO> editarVaga(Long id, @Valid VagaRequestDTO dto) {
        VagaResponseDTO response = vagaServiceFacede.editarVaga(id, dto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> removerVaga(Long id) {
        vagaServiceFacede.removerVaga(id);
        return ResponseEntity.noContent().build();
    }
}