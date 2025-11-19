package com.ifba.projeto.tcc.application.facede;

import com.ifba.projeto.tcc.application.dto.request.CurriculoRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.application.usecase.candidato.DetalharCurriculoAtivoUseCase;
import com.ifba.projeto.tcc.application.usecase.candidato.ListarCurriculosPorCandidatoUseCase;
import com.ifba.projeto.tcc.application.usecase.curriculo.CriarCurriculoUseCase;
import com.ifba.projeto.tcc.application.usecase.curriculo.DeletarCurriculoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurriculoFacede {
    private final CriarCurriculoUseCase criarCurriculoUseCase;
    private final DetalharCurriculoAtivoUseCase detalharCurriculoAtivoUseCase;
    private final ListarCurriculosPorCandidatoUseCase listarCurriculosPorCandidatoUseCase;
    private final DeletarCurriculoUseCase deletarCurriculoUseCase;
    public CurriculoResponseDTO criarCurriculo(CurriculoRequestDTO dto, Long vagaId) {
        return criarCurriculoUseCase.executar(dto, vagaId);
    }
    public CurriculoResponseDTO detalharCurriculoAtivo(Long candidatoId) {
        return detalharCurriculoAtivoUseCase.executar(candidatoId);
    }
    public Page<CurriculoResponseDTO> listarCurriculosPorCandidato(Long candidatoId, Pageable pageable) {
        return listarCurriculosPorCandidatoUseCase.executar(pageable, candidatoId);
    }

    public void deletarCurriculo(Long curriculoId) {
         deletarCurriculoUseCase.executar(curriculoId);
    }
}
