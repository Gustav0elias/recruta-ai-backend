package com.ifba.projeto.tcc.domain.repository;

import com.ifba.projeto.tcc.domain.entity.Vaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    @Query("""
        SELECT DISTINCT v
        FROM Vaga v
        LEFT JOIN FETCH v.vagaHabilidades vh
        LEFT JOIN FETCH vh.habilidade h
        WHERE v.usuario.id = :usuarioId
    """)
    Page<Vaga> findAllByUserId(@Param("usuarioId") Long usuarioId, Pageable pageable);
    
    @Query("""
        SELECT COUNT(c)
        FROM Candidatura c
        WHERE c.vaga.id = :vagaId
    """)
    Long countCandidaturasByVagaId(@Param("vagaId") Long vagaId);
    
    @Query("""
        SELECT COUNT(v)
        FROM Vaga v
        WHERE v.usuario.id = :usuarioId
    """)
    Long countByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query("""
        SELECT COUNT(DISTINCT v)
        FROM Vaga v
        WHERE v.usuario.id = :usuarioId
        AND EXISTS (SELECT 1 FROM Candidatura c WHERE c.vaga.id = v.id)
    """)
    Long countVagasComCandidaturasByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query("""
        SELECT v.id as id,
               v.uuid as uuid,
               v.titulo as titulo,
               v.nivelExperiencia as nivelExperiencia,
               v.criadoEm as criadoEm,
               COUNT(c) as quantidadeCandidaturas,
               AVG(c.score) as mediaScore
        FROM Vaga v
        LEFT JOIN Candidatura c ON c.vaga.id = v.id
        WHERE v.usuario.id = :usuarioId
        GROUP BY v.id, v.uuid, v.titulo, v.nivelExperiencia, v.criadoEm
        ORDER BY quantidadeCandidaturas DESC
    """)
    java.util.List<com.ifba.projeto.tcc.domain.repository.projection.VagaEstatisticaProjection> findVagasMaisProcuradasByUsuarioId(
            @Param("usuarioId") Long usuarioId,
            org.springframework.data.domain.Pageable pageable);

}
