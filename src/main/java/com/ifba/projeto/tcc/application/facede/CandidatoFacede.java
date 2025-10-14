package com.ifba.projeto.tcc.application.facede;

import com.ifba.projeto.tcc.application.dto.request.CurriculoRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidatoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CandidatoResumoResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.CurriculoResponseDTO;
import com.ifba.projeto.tcc.application.usecase.candidato.AtualizarCurriculoCandidatoUseCase;
import com.ifba.projeto.tcc.application.usecase.candidato.ListarCandidatosGeralUseCase;
import com.ifba.projeto.tcc.application.usecase.candidato.ListarCandidatosUseCase;
import com.ifba.projeto.tcc.application.usecase.candidato.impl.AtualizarCurriculoCandidatoUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CandidatoFacede {
    private final ListarCandidatosUseCase listarCandidatoUseCase;
    private final ListarCandidatosGeralUseCase listarCandidatosGeralUseCase;
    private final AtualizarCurriculoCandidatoUseCase atualizarCurriculoCandidatoUseCase;
    public Page<CandidatoResponseDTO> listarCandidatos(Long vagaId, Pageable pageable) {
        return listarCandidatoUseCase.executar(vagaId, pageable);
    }

    public Page<CandidatoResponseDTO> listarCandidatosGeral(Pageable pageable) {
        return listarCandidatosGeralUseCase.executar(pageable);
    }
    public CandidatoResumoResponseDTO atualizarCurriculoCandidato(Long candidatoId,  MultipartFile arquivo) {
        return atualizarCurriculoCandidatoUseCase.executar(candidatoId, arquivo);
    }
}
