package com.aluracursos.desafio_literalura.dto;

import jakarta.persistence.Column;

public record AutorDTO(
        Long id,
        String autor,
        Integer anioNaciiento,
        Integer anioFallecimiento
) {
}
