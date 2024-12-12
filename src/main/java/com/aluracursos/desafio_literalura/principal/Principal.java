package com.aluracursos.desafio_literalura.principal;

import com.aluracursos.desafio_literalura.model.*;
import com.aluracursos.desafio_literalura.repository.AutorRepository;
import com.aluracursos.desafio_literalura.repository.LibroRepository;
import com.aluracursos.desafio_literalura.service.ConsumoAPI;
import com.aluracursos.desafio_literalura.service.ConvertirDatos;

import java.util.*;
import java.util.stream.Collectors;

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
                    2- Ver libros registrados
                    3- Ver autores registrados
                    4- Ver autores vivos en determinado año
                    5- Ver libros por idioma 
                    6- Ver Top 10 libros mas descargados
                    7- Buscar Autor por nombre
                    8- Ver estadisticas de libros por cantidad de descargas 
                    
                             
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    verLibrosRegistrados();
                    break;
                case 3:
                    verAutoresRegistrados();
                    break;
                case 4:
                    verAutoresVivosAUnAnio();
                    break;
                case 5:
                    verLibrosPorIdioma();
                    break;
                case 6:
                    verTop10LibrosPorDescargas();
                    break;
                case 7:
                    buscarAutorPorNombre() ;
                    break;
                case 8:
                    estadisticasLibrosPorCantidadDescargas();
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

    private void verLibrosRegistrados() {
        List<Libro> librosRegistrados = libroRepositorio.findAll();
        librosRegistrados.stream()
                .sorted(Comparator.comparing(Libro::getCantidadDescargas))
                .forEach(System.out::println);
    }

    private void verAutoresRegistrados() {
        autoresRegistrados = autorRepositorio.findAll();
        autoresRegistrados.stream()
                .sorted(Comparator.comparing(Autor::getAnioNacimiento))
                .forEach(System.out::println);
    }

    private void verAutoresVivosAUnAnio() {
        System.out.println("Ingrese el año con el que desea validar los autores vivos: ");
        try {
            int anio = teclado.nextInt();
            teclado.nextLine();
            List<Autor> autoresVivos = autorRepositorio.verAutoresVivosAUnAnio(anio);
            if(autoresVivos.isEmpty()){
                System.out.println("""
                     ******************* No existen autores registrados vivos al año indicado **********
                """);
            }else{
                autoresVivos.forEach(System.out::println);
            }

        } catch (InputMismatchException e) {
            teclado.nextLine();
            System.out.println("""
                    *********************** Ingrese el año a validar  **********************
                    """);
        }
    }

    private void verLibrosPorIdioma() {
        String idioma = "";
        System.out.println("""
   ************************************************************
                        IDIOMA DISPONIBLE:
                        1- Ingles
                        2- Español
                        3- Frances
                        4- Portugues
                        5- Italiano
   *************************************************************
""");

        var opcion = teclado.nextInt();
        teclado.nextLine();


        switch (opcion) {
            case 1:
                idioma = "en" ;
                break;
            case 2:
                idioma = "es" ;
                break;
            case 3:
                idioma = "fr";
                break;
            case 4:
                idioma = "pt" ;
                break;
            case 5:
                idioma = "it" ;
                break;
            default:
                System.out.println("********* Opción Invalida *********");


        }

        List<Libro> librosPorIdioma = libroRepositorio.findByIdiomasContaining(idioma);
        if (librosPorIdioma.isEmpty()) {
            System.out.println("""
            ************ No existen Libros en el idioma indicado  **************** 
            """);
        } else {
            var  cantidadLibrosPorIdioma =libroRepositorio.countByLanguage(idioma);
            System.out.println( " " + "\n" +

                    "**************** HAY " + cantidadLibrosPorIdioma + " En el idioma seleccionado ******************" + "\n" +

                    "  ");
            librosPorIdioma.forEach(System.out::println);
        }


    }
        private void verTop10LibrosPorDescargas() {
            var top10LibrosDescargados = libroRepositorio.findTop10ByOrderByCantidadDescargasDesc();
            top10LibrosDescargados.forEach(System.out::println);

        }

    private void buscarAutorPorNombre() {
        System.out.println("Ingrese el nombre del Autor a consultar ");
        var nombreAutor = teclado.nextLine();
        var autorPorNombre = autorRepositorio.buscarAutorPorNombre(nombreAutor);
        if(autorPorNombre != null) {
            System.out.println(
                    "Autor Encontrado " + "\n" +
                            autorPorNombre + "\n" +
                            "" + "\n");
            }else {
            System.out.println("Autor no emcontrado en la Base de datos ");
        }


    }

    private void estadisticasLibrosPorCantidadDescargas(){
        IntSummaryStatistics est = librosRegistrados.stream()
                .filter(l -> l.getCantidadDescargas() > 0)
                .collect(Collectors.summarizingInt(Libro::getCantidadDescargas));
        System.out.println("El promedio de descargas de los libros almacenados en base de datos es: " + est.getAverage());
        System.out.println("la cantidad mayor de descargas de los libros almacenados en base de datos es: " + est.getMax());
        System.out.println("La cantidad menor de descargas de los libros almacenados en base de datos es: " + est.getMin());
        System.out.println("La cantidad total de descargas de los libros almacenados en base de datos es: " + est.getSum());
        System.out.println("La cantidad total de libros registrados en la base de datos es: " + est.getSum());
    }





}



