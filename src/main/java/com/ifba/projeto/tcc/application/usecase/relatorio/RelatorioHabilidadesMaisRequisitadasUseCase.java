package com.ifba.projeto.tcc.application.usecase.relatorio;

import com.ifba.projeto.tcc.application.dto.response.RelatorioHabilidadesMaisRequisitadasResponseDTO;

public interface RelatorioHabilidadesMaisRequisitadasUseCase {
    RelatorioHabilidadesMaisRequisitadasResponseDTO executar(Integer limite);
}

