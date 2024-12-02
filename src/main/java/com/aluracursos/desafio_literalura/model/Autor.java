package com.aluracursos.desafio_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "Autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(unique = true)
    @JsonAlias("title")
    private String autor ;
    @JsonAlias("birth_year")
    private Integer anioNacimiento ;
    @JsonAlias("death_year")
    private Integer anioFallecimiento ;
    @OneToMany(mappedBy = "autores" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Libro libro ;



}
