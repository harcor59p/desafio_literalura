package com.aluracursos.desafio_literalura.principal;

import com.aluracursos.desafio_literalura.model.*;
import com.aluracursos.desafio_literalura.repository.AutorRepository;
import com.aluracursos.desafio_literalura.repository.LibroRepository;
import com.aluracursos.desafio_literalura.service.ConsumoAPI;
import com.aluracursos.desafio_literalura.service.ConvertirDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.aluracursos.desafio_literalura.repository.AutorRepository.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in) ;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=" ;
    private ConvertirDatos conversor = new ConvertirDatos() ;
    private List<Libro> librosRegistrados = new ArrayList<>();
    private List<Autor> autoresRegistrados = new ArrayList<>();
    private ConsultaLibros datos ;
    private AutorRepository autorRepositorio;
    private LibroRepository libroRepositorio;


    public Principal(AutorRepository autorRepositorio, LibroRepository libroRepository) {
        this.autorRepositorio = autorRepositorio;
        this.libroRepositorio = libroRepository;
    }


    public void muestraElMenu(){
        var opcion = -1;
        while(opcion != 0){
            var menu = """
                    1- Buscar libro por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en determinado año
                    5- Listar libros por idioma 
                    
                             
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
//                case 2:
//                    buscarEpisodioPorSeerie();
//                    break;
//                case 3:
//                    mostrarSeriesBuscadas();
//                    break;
//                case 4:
//                    buscarSeriesPorTitulo();
//                    break;
//                case 5:
//                    buscarTop5Series();
//                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción invalida");
            }

        }


    }
    private ConsultaLibros getDatosLibros() {
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE  + nombreLibro.replace(" " , "+")) ;
        datos = conversor.obtenerDatos(json, ConsultaLibros.class);
        return datos;
    }

    private Libro crearLibro(DatosLibros datosLibro, Autor autor) {
        Libro libro = new Libro(datosLibro, autor);
        return libroRepositorio.save(libro);
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Por favor escribe el nombre del libro que deseas buscar: ");
        ConsultaLibros datos = getDatosLibros();
        if (!datos.resultados().isEmpty()) {
            DatosLibros datosLibro = datos.resultados().get(0);
            DatosAutores datosAutor = datosLibro.autor().get(0);
            Libro libro = null;
            Libro libroDb = libroRepositorio.findByTitulo(datosLibro.titulo());
            if (libroDb != null) {
                System.out.println(libroDb);
            } else {
                Autor autorDb = autorRepositorio.findByNombreIgnoreCase(datosLibro.autor().get(0).nombre());
                if (autorDb == null) {
                    Autor autor = new Autor(datosAutor);
                    autor = autorRepositorio.save(autor);
                    libro = crearLibro(datosLibro, autor);
                    System.out.println(libro);
                } else {
                    libro = crearLibro(datosLibro, autorDb);
                    System.out.println(libro);
                }
            }
        } else {
            System.out.println("""
            El libro indicado no existe..............
        """);
        }
    }



}



