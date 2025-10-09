package com.ifba.projeto.tcc.web.api.impl;

import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.application.facede.HabilidadeServiceFacede;
import com.ifba.projeto.tcc.web.api.HabilidadeController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HabilidadaControllerImpl implements HabilidadeController {
    private final HabilidadeServiceFacede habilidadeServiceFacede;
    @Override
    public ResponseEntity<HabilidadeResponseDTO> criarHabilidade(HabilidadeRequestDTO dto) {
        HabilidadeResponseDTO response =habilidadeServiceFacede.criarHabilidade(dto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<HabilidadeResponseDTO>> listarHabilidades(@PageableDefault(page = 0, size = 10, sort = "nome") Pageable pageable) {
        Page<HabilidadeResponseDTO> page = habilidadeServiceFacede.listarHabilidades(pageable);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<HabilidadeResponseDTO> editarHabilidade(Long id, HabilidadeRequestDTO dto) {
        HabilidadeResponseDTO response = habilidadeServiceFacede.editarHabilidade(id, dto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> removerHabilidade(Long id) {
        habilidadeServiceFacede.removerHabilidade(id);
        return ResponseEntity.noContent().build();
    }
}
