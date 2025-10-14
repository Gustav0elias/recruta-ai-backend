package com.ifba.projeto.tcc.domain.repository;

import com.ifba.projeto.tcc.domain.entity.Candidatura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
    @Query("""
        SELECT cand
        FROM Candidatura cand
        JOIN FETCH cand.curriculo cur
        JOIN FETCH cur.candidato c
        LEFT JOIN FETCH c.habilidades h
        WHERE cand.vaga.id = :vagaId
    """)
    Page<Candidatura> findAllByVagaId(@Param("vagaId") Long vagaId, Pageable pageable);

    @Query("""
        SELECT cand
        FROM Candidatura cand
        JOIN FETCH cand.curriculo cur
        JOIN FETCH cur.candidato c
        LEFT JOIN FETCH c.habilidades h
        WHERE cand.vaga.id = :vagaId
        ORDER BY cand.score DESC
    """)
    Page<Candidatura> findAllByVagaIdOrderByScoreDesc(@Param("vagaId") Long vagaId, Pageable pageable);
}
