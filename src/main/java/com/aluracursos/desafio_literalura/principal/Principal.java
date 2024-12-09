package com.aluracursos.desafio_literalura.principal;

import com.aluracursos.desafio_literalura.model.Autor;
import com.aluracursos.desafio_literalura.model.DatosLibros;
import com.aluracursos.desafio_literalura.model.Libro;
import com.aluracursos.desafio_literalura.repository.LibroRepository;
import com.aluracursos.desafio_literalura.service.ConsumoAPI;
import com.aluracursos.desafio_literalura.service.ConvertirDatos;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in) ;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=" ;
    private ConvertirDatos conversor = new ConvertirDatos() ;
    private LibroRepository libroRepository ;


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
                    buscarLibro();
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
    private DatosLibros getDatosLibros() {
        System.out.println("Por favor escribe el nombre del libro que deseas buscar: ");
        // Trae la información basica del libro indicado
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE  + nombreLibro.replace(" " , "+")) ;
        System.out.println(json);
        DatosLibros datos = conversor.obtenerDatos(json , DatosLibros.class);
        return datos;
    }

    private Libro crearLibro(DatosLibros datosLibro, Autor autor) {
        Libro libro = new Libro(datosLibro, autor);
        return libroRepository.save(libro);
    }

    private void buscarLibro(){
        DatosLibros datos = getDatosLibros();
        Libro libro = new Libro(datos) ;
        libroRepository.save(libro);
        System.out.println(datos);
    }



}



