package com.ifba.projeto.tcc.domain.repository;

import com.ifba.projeto.tcc.domain.entity.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
    Optional<Habilidade> findByUuid(UUID uuid);

    Optional<Habilidade> findByNome(String nome);
    
    @Query("""
        SELECT DISTINCT h
        FROM Habilidade h
        INNER JOIN h.candidatos c
        WHERE c.id = :candidatoId
    """)
    Set<Habilidade> findByCandidatoId(@Param("candidatoId") Long candidatoId);
    
    @Query("""
        SELECT h.uuid as uuid,
               h.nome as nome,
               h.tipo as tipo,
               COUNT(DISTINCT vh.vaga.id) as quantidadeVagas,
               COUNT(DISTINCT c.id) as quantidadeCandidatos
        FROM Habilidade h
        LEFT JOIN h.vagaHabilidades vh
        LEFT JOIN h.candidatos c
        GROUP BY h.id, h.uuid, h.nome, h.tipo
        ORDER BY quantidadeVagas DESC, quantidadeCandidatos DESC
    """)
    java.util.List<com.ifba.projeto.tcc.domain.repository.projection.HabilidadeEstatisticaProjection> findHabilidadesMaisRequisitadas(
            org.springframework.data.domain.Pageable pageable);
}
