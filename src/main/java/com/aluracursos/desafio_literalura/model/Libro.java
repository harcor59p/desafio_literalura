package com.aluracursos.desafio_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(unique = true)
    @JsonAlias("title")
    private String titulo ;
    @Enumerated(EnumType.STRING)
    @JsonAlias("languages")
    private Idioma idioma ;
    @JsonAlias("download_count")
    private Integer cantidadDescargas;
    @ManyToOne(mappedBy = "autor" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonAlias("authors")
    private List<Autor> autor ;




}
