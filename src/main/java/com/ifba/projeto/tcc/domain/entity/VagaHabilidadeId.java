package com.ifba.projeto.tcc.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VagaHabilidadeId implements Serializable {
    private Long vagaId;
    private Long habilidadeId;
}
