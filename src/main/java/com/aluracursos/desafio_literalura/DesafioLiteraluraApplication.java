package com.aluracursos.desafio_literalura;

import com.aluracursos.desafio_literalura.principal.Principal;
import com.aluracursos.desafio_literalura.repository.AutorRepository;
import com.aluracursos.desafio_literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LibroRepository libroRepository;
	public static void main(String[] args) {
		SpringApplication.run(DesafioLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(autorRepository, libroRepository);
		principal.muestraElMenu();

	}

}
