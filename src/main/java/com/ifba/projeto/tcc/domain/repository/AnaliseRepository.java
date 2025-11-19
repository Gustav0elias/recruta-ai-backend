package com.ifba.projeto.tcc.domain.repository;

import com.ifba.projeto.tcc.domain.entity.Analise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Long> {
    @Query("""
        SELECT a
        FROM Analise a
        WHERE a.curriculo.id = :curriculoId
    """)
    List<Analise> findByCurriculoId(@Param("curriculoId") Long curriculoId);
}
