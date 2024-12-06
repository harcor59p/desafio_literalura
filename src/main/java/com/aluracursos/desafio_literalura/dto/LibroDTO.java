package com.aluracursos.desafio_literalura.dto;

import com.aluracursos.desafio_literalura.model.Autor;
import com.aluracursos.desafio_literalura.model.Lenguaje;
import jakarta.persistence.*;

public record LibroDTO(
        Long id,
        String titulo,
        Lenguaje lenguaje,
        Long cantidadDescargas
) {
}
