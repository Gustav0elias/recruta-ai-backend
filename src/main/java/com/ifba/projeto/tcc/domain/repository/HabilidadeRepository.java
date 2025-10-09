package com.ifba.projeto.tcc.domain.repository;

import com.ifba.projeto.tcc.domain.entity.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
    Optional<Habilidade> findByUuid(UUID uuid);
}
