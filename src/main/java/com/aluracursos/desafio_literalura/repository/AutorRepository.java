package com.aluracursos.desafio_literalura.repository;

import com.aluracursos.desafio_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :anio AND :anio < a.anioFallecimiento OR a.anioFallecimiento = 0 ")
    List<Autor> verAutoresVivosAUnAnio(int anio);

    @Query("SELECT a FROM Autor a WHERE a.nombre ILIKE %:nombreAutor%")
    List<Autor> buscarAutorPorNombre(String nombreAutor) ;


}
