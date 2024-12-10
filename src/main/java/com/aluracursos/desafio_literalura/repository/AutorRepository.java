package com.aluracursos.desafio_literalura.repository;

import com.aluracursos.desafio_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombreIgnoreCase(String nombre);
}
