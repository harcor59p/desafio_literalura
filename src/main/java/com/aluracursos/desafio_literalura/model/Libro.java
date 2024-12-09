package com.aluracursos.desafio_literalura.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(unique = true)
    private String titulo ;
    @ManyToOne()
    @JoinColumn( name = "autor_id", nullable = false)
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private String idiomas;
    private Integer cantidadDescargas;

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idiomas = datosLibros.idiomas().get(0);
        this.cantidadDescargas = Integer.valueOf(datosLibros.cantidadDescargas());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return "******************************************************************" + "\n" +
                "   Titulo: " + titulo  +  "\n" +
                "   Nombre autor: " + autor  + "\n" +
                "   Idioma:  " + idiomas + "\n" +
                "   Numero descargas:  " + cantidadDescargas + "\n" +
                "******************************************************************";
    }
}
