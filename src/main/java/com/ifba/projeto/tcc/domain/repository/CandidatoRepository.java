package com.ifba.projeto.tcc.domain.repository;

import com.ifba.projeto.tcc.domain.entity.Candidato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    @Query("""
        SELECT DISTINCT c
        FROM Candidatura cand
        JOIN cand.curriculo cur
        JOIN cur.candidato c
        WHERE cand.vaga.id = :vagaId
    """)
    Page<Candidato> findAllByVagaId(@Param("vagaId") Long vagaId, Pageable pageable);
}
