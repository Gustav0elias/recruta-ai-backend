package com.ifba.projeto.tcc.application.usecase.relatorio;

import com.ifba.projeto.tcc.application.dto.response.RelatorioVagasMaisProcuradasResponseDTO;

public interface RelatorioVagasMaisProcuradasUseCase {
    RelatorioVagasMaisProcuradasResponseDTO executar(Integer limite);
}

