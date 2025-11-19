package com.ifba.projeto.tcc.domain.repository;

import com.ifba.projeto.tcc.domain.entity.Curriculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
    @Modifying
    @Query("UPDATE Curriculo c SET c.ativo = false WHERE c.candidato.id = :idCandidato AND c.ativo = true")
    void desativarCurriculosAtivosPorCandidato(@Param("idCandidato") Long idCandidato);

    @Query("""
        SELECT c
        FROM Curriculo c
        WHERE c.candidato.id = :candidatoId
          AND c.ativo = true
    """)
    Optional<Curriculo> findAtivoByCandidatoId(@Param("candidatoId") Long candidatoId);

    Page<Curriculo> findAllByCandidatoId(Pageable pageable, Long candidatoId);
    
    @Query("""
        SELECT c
        FROM Curriculo c
        WHERE c.candidato.id = :candidatoId
    """)
    java.util.List<Curriculo> findAllByCandidatoId(@Param("candidatoId") Long candidatoId);

}
