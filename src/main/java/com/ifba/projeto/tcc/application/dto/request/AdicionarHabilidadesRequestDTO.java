package com.ifba.projeto.tcc.application.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record AdicionarHabilidadesRequestDTO(List<HabilidadeComPesoRequestDTO> habilidades) {}
