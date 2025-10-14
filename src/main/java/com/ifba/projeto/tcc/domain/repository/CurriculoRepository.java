package com.ifba.projeto.tcc.domain.repository;

import com.ifba.projeto.tcc.domain.entity.Curriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
    @Modifying
    @Query("UPDATE Curriculo c SET c.ativo = false WHERE c.candidato.id = :idCandidato AND c.ativo = true")
    void desativarCurriculosAtivosPorCandidato(@Param("idCandidato") Long idCandidato);
}
