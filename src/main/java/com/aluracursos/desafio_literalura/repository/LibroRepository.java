package com.aluracursos.desafio_literalura.repository;

import com.aluracursos.desafio_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository  extends JpaRepository<Libro, Long> {
}
