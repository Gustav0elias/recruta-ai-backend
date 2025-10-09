package com.ifba.projeto.tcc.application.facede;

import com.ifba.projeto.tcc.application.dto.request.AdicionarHabilidadesRequestDTO;
import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.request.VagaRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;
import com.ifba.projeto.tcc.application.usecase.vaga.*;
import com.ifba.projeto.tcc.domain.entity.Vaga;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VagaServiceFacede {
    private final CriarVagaUseCase criarVagaUseCase;
    private final EditarVagaUseCase editarVagaUseCase;
    private final ListarVagasUseCase listarVagasUseCase;
    private final DetalharVagaUseCase detalharVagaUseCase;
    private final RemoverVagaUseCase removerVagaUseCase;
    private final AdicionarHabilidadesUseCase adicionarHabilidadesUseCase;
    public VagaResponseDTO criarVaga(VagaRequestDTO dto) {
        return criarVagaUseCase.executar(dto);
    }


    public Page<VagaResponseDTO> listarVagas(Long usuarioId,Pageable pageable) {
        return listarVagasUseCase.executar(usuarioId,pageable);
    }

    public VagaResponseDTO detalharVaga(Long vagaId) {
        return detalharVagaUseCase.executar(vagaId);
    }
    public VagaResponseDTO editarVaga(Long id, VagaRequestDTO dto) {
        return editarVagaUseCase.executar(dto, id);
    }

    public void removerVaga(Long id) {
        removerVagaUseCase.executar(id);
    }
    public VagaResponseDTO adicionarHabilidade(Long vagaId, AdicionarHabilidadesRequestDTO dto) {
        return adicionarHabilidadesUseCase.executar(vagaId,dto);
    }
}
