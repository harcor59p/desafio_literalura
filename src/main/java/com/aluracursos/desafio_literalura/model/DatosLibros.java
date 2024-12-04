package com.aluracursos.desafio_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title")
        String titulo ,
        @JsonAlias("languages")
        String idioma ,
        @JsonAlias("download_count")
        Integer cantidadDescargas


) {
}
