package com.aluracursos.desafio_literalura.dto;

public record LibroDTO(
        Long id,
        String titulo,
        String idioma,
        Long cantidadDescargas
) {
}
