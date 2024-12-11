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
    private String nombreAutor;
    @ManyToOne
    private Autor autor;
    private String idiomas;
    private int cantidadDescargas;

    public Libro() {
    }



    public Libro(DatosLibros datosLibro, Autor autor) {
        this.titulo = datosLibro.titulo();
        this.nombreAutor = autor.getNombre() ;
        this.autor = autor;
        this.idiomas = datosLibro.idiomas().get(0);
        this.cantidadDescargas = datosLibro.cantidadDescargas();

    }

    @Override
    public String toString() {
        return "******************************************************************" + "\n" +
                "   Titulo: " + titulo  +  "\n" +
                "   Nombre autor: " + nombreAutor  + "\n" +
                "   Idioma:  " + idiomas + "\n" +
                "   Numero descargas:  " + cantidadDescargas + "\n" +
                "******************************************************************";
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
    public int getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(int cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
