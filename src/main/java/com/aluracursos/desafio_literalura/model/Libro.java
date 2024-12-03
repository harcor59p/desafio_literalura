package com.aluracursos.desafio_literalura.model;


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
    private String titulo ;
    @Enumerated(EnumType.STRING)
    private String idioma ;
    private Integer cantidadDescargas;
    @ManyToOne(fetch = FetchType.EAGER)
    private List<Autor> autores ;




}
