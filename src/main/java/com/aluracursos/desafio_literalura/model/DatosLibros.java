package com.aluracursos.desafio_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title")
        String titulo ,
        @JsonAlias("languages")
        String idioma ,
        @JsonAlias("authors")
        List<DatosAutores> autor,
        @JsonAlias("languages")
        List<String> lenguaje ,
        @JsonAlias("download_count")
        Integer cantidadDescargas
) {
        @Override
        public List<DatosAutores> autor() {
                return autor == null ? Collections.emptyList() : autor;
        }

        @Override
        public List<String> lenguaje() {
                return lenguaje == null ? Collections.emptyList() : lenguaje;
        }
}
