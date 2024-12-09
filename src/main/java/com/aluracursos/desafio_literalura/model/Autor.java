package com.aluracursos.desafio_literalura.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "Autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(unique = true)
    private String autor ;
    private Integer anioNacimiento ;
    private Integer anioFallecimiento ;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();


    public Autor(){}

    public Autor(DatosAutores datosAutores) {
        //this.id = autorRecord.id();
        this.autor = datosAutores.autor();
        this.anioNacimiento = datosAutores.anioNacimiento();
        this.anioFallecimiento = datosAutores.anioFallecimiento();
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "******************************************************************************" + "\n" +
                "   Nombre:  " + autor  +   "\n" +
                "   Fecha nacimiento:  " + anioNacimiento + "\n" +
                "   Fecha muerte:  " + anioFallecimiento +  "\n" +
                "*******************************************************************************";

    }

}
