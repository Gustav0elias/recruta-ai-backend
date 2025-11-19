package com.ifba.projeto.tcc.domain.repository;

import com.ifba.projeto.tcc.domain.entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {
    @Query("""
        SELECT e
        FROM Experiencia e
        WHERE e.candidato.id = :candidatoId
    """)
    List<Experiencia> findByCandidatoId(@Param("candidatoId") Long candidatoId);
}
