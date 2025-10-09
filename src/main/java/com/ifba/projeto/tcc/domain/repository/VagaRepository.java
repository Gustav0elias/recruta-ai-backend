package com.ifba.projeto.tcc.domain.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.ifba.projeto.tcc.domain.entity.Vaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    @Query("SELECT v FROM Vaga v WHERE (v.usuario.id = :usuarioId)")
    Page<Vaga> findAllByUserId(@Param("usuarioId") Long usuarioId, Pageable pageable);

}
