package com.ifba.projeto.tcc.application.facede;

import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;
import com.ifba.projeto.tcc.application.usecase.habilidade.CriarHabilidadeUseCase;

import com.ifba.projeto.tcc.application.usecase.habilidade.EditarHabilidadeUseCase;
import com.ifba.projeto.tcc.application.usecase.habilidade.ListarHabilidadesUseCase;
import com.ifba.projeto.tcc.application.usecase.habilidade.RemoverHabilidadeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabilidadeFacede {
    private final CriarHabilidadeUseCase criarHabilidadeUseCase;
    private final ListarHabilidadesUseCase listarHabilidadesUseCase;
    private final EditarHabilidadeUseCase editarHabilidadeUseCase;
    private final RemoverHabilidadeUseCase removerHabilidadeUseCase;
    public HabilidadeResponseDTO criarHabilidade( HabilidadeRequestDTO dto) {
        return criarHabilidadeUseCase.executar(dto);
    }


    public Page<HabilidadeResponseDTO> listarHabilidades(Pageable pageable) {
        return listarHabilidadesUseCase.executar(pageable);
    }

    public HabilidadeResponseDTO editarHabilidade(Long id, HabilidadeRequestDTO dto) {
        return editarHabilidadeUseCase.executar(dto, id);
    }

    public void removerHabilidade(Long id) {
        removerHabilidadeUseCase.removerHabilidade(id);
    }
}
