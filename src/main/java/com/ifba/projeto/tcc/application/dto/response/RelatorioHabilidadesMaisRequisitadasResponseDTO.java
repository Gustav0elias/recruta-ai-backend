package com.ifba.projeto.tcc.application.dto.response;

import java.util.List;

public record RelatorioHabilidadesMaisRequisitadasResponseDTO(
        List<HabilidadeEstatisticaResponseDTO> habilidades
) {}

