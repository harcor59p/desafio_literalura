package com.aluracursos.desafio_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutores(
        @JsonAlias("title")
        String autor ,
        @JsonAlias("birth_year")
        Integer anioNacimiento ,
        @JsonAlias("death_year")
        Integer anioFallecimiento

) {
}
