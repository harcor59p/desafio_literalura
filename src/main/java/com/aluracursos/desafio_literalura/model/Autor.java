package com.aluracursos.desafio_literalura.model;



import jakarta.persistence.*;



import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private   String nombre;
    private   int anioNacimiento;
    private   int anioFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<Libro> libros;


    public Autor() {
    }


    public Autor(DatosAutores datosAutor)  {
        this.nombre = datosAutor.nombre() ;
        this.anioNacimiento = Integer.parseInt(datosAutor.anioNacimiento());
        this.anioFallecimiento = Integer.parseInt(datosAutor.anioFallecimiento());
    }


    @Override
    public String toString() {
        return  "******************************************************************************" + "\n" +
                "   Nombre:  " + nombre  +   "\n" +
                "   Fecha nacimiento:  " + anioNacimiento + "\n" +
                "   Fecha muerte:  " + anioFallecimiento +  "\n" +
                "*******************************************************************************";

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public int getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(int anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
