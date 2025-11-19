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

    @Query("""
        SELECT cand
        FROM Candidatura cand
        WHERE cand.curriculo.id = :curriculoId
        ORDER BY cand.dataCadastro DESC
    """)
    java.util.Optional<com.ifba.projeto.tcc.domain.entity.Candidatura> findFirstByCurriculoIdOrderByDataCadastroDesc(@Param("curriculoId") Long curriculoId);
    
    @Query("""
        SELECT AVG(c.score)
        FROM Candidatura c
        WHERE c.score IS NOT NULL
    """)
    Double calcularMediaScore();
    
    @Query("""
        SELECT COUNT(c)
        FROM Candidatura c
        WHERE c.dataCadastro BETWEEN :dataInicio AND :dataFim
    """)
    Long countByPeriodo(@Param("dataInicio") java.time.LocalDateTime dataInicio, @Param("dataFim") java.time.LocalDateTime dataFim);
    
    @Query("""
        SELECT AVG(c.score)
        FROM Candidatura c
        WHERE c.dataCadastro BETWEEN :dataInicio AND :dataFim
        AND c.score IS NOT NULL
    """)
    Double calcularMediaScorePorPeriodo(@Param("dataInicio") java.time.LocalDateTime dataInicio, @Param("dataFim") java.time.LocalDateTime dataFim);
    
    @Query("""
        SELECT MAX(c.score)
        FROM Candidatura c
        WHERE c.dataCadastro BETWEEN :dataInicio AND :dataFim
        AND c.score IS NOT NULL
    """)
    Long obterMaiorScorePorPeriodo(@Param("dataInicio") java.time.LocalDateTime dataInicio, @Param("dataFim") java.time.LocalDateTime dataFim);
    
    @Query("""
        SELECT MIN(c.score)
        FROM Candidatura c
        WHERE c.dataCadastro BETWEEN :dataInicio AND :dataFim
        AND c.score IS NOT NULL
    """)
    Long obterMenorScorePorPeriodo(@Param("dataInicio") java.time.LocalDateTime dataInicio, @Param("dataFim") java.time.LocalDateTime dataFim);
    
    @Query("""
        SELECT COUNT(c)
        FROM Candidatura c
        WHERE c.score IS NOT NULL
        AND c.score >= :scoreMin
        AND c.score <= :scoreMax
    """)
    Long countByScoreRange(@Param("scoreMin") Long scoreMin, @Param("scoreMax") Long scoreMax);
    
    @Query("""
        SELECT COUNT(c)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
    """)
    Long countByVagaId(@Param("vagaId") Long vagaId);
    
    @Query("""
        SELECT AVG(c.score)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
        AND c.score IS NOT NULL
    """)
    Double calcularMediaScoreByVagaId(@Param("vagaId") Long vagaId);
    
    @Query("""
        SELECT MAX(c.score)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
        AND c.score IS NOT NULL
    """)
    Long obterMaiorScoreByVagaId(@Param("vagaId") Long vagaId);
    
    @Query("""
        SELECT MIN(c.score)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
        AND c.score IS NOT NULL
    """)
    Long obterMenorScoreByVagaId(@Param("vagaId") Long vagaId);
    
    @Query("""
        SELECT COUNT(c)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
        AND c.score IS NOT NULL
    """)
    Long countCandidaturasComScoreByVagaId(@Param("vagaId") Long vagaId);
    
    @Query("""
        SELECT COUNT(c)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
        AND c.score IS NULL
    """)
    Long countCandidaturasSemScoreByVagaId(@Param("vagaId") Long vagaId);
    
    @Query("""
        SELECT COUNT(c)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
        AND c.dataCadastro BETWEEN :dataInicio AND :dataFim
    """)
    Long countByVagaIdAndPeriodo(@Param("vagaId") Long vagaId, @Param("dataInicio") java.time.LocalDateTime dataInicio, @Param("dataFim") java.time.LocalDateTime dataFim);
    
    @Query("""
        SELECT AVG(c.score)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
        AND c.dataCadastro BETWEEN :dataInicio AND :dataFim
        AND c.score IS NOT NULL
    """)
    Double calcularMediaScoreByVagaIdAndPeriodo(@Param("vagaId") Long vagaId, @Param("dataInicio") java.time.LocalDateTime dataInicio, @Param("dataFim") java.time.LocalDateTime dataFim);
    
    @Query("""
        SELECT MAX(c.score)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
        AND c.dataCadastro BETWEEN :dataInicio AND :dataFim
        AND c.score IS NOT NULL
    """)
    Long obterMaiorScoreByVagaIdAndPeriodo(@Param("vagaId") Long vagaId, @Param("dataInicio") java.time.LocalDateTime dataInicio, @Param("dataFim") java.time.LocalDateTime dataFim);
    
    @Query("""
        SELECT MIN(c.score)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
        AND c.dataCadastro BETWEEN :dataInicio AND :dataFim
        AND c.score IS NOT NULL
    """)
    Long obterMenorScoreByVagaIdAndPeriodo(@Param("vagaId") Long vagaId, @Param("dataInicio") java.time.LocalDateTime dataInicio, @Param("dataFim") java.time.LocalDateTime dataFim);
    
    @Query("""
        SELECT COUNT(c)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
        AND c.score IS NOT NULL
        AND c.score >= :scoreMin
        AND c.score <= :scoreMax
    """)
    Long countByVagaIdAndScoreRange(@Param("vagaId") Long vagaId, @Param("scoreMin") Long scoreMin, @Param("scoreMax") Long scoreMax);
    
    @Query("""
        SELECT c
        FROM Candidatura c
        JOIN FETCH c.curriculo cur
        JOIN FETCH cur.candidato cand
        WHERE c.vaga.id = :vagaId
        AND c.score IS NOT NULL
        ORDER BY c.score DESC
    """)
    java.util.List<Candidatura> findTopCandidatosByVagaId(@Param("vagaId") Long vagaId, org.springframework.data.domain.Pageable pageable);
}
