
package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;

/**
 *
 * @author monse
 */
public class Libreria {
    private List<Libro> libros;
    private String nombre;
    
    public Libreria(String nom) {
        libros = new ArrayList<>();
        this.nombre = nom;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean agregarLibro(Libro l) {
        libros.add(l);
        return true;
    }
    
    //Obtiene todos los titulo distintos de la libreria
    public List<String> getLibros() {
        List<String> nomLibros = libros.stream()
                                .map(libro -> libro.getTitulo()) //.map(Libro::getTitulo)
                                .distinct()
                                .collect(Collectors.toList());
        return nomLibros;
    }
    
    public Map<Genero, List<Autor>> getGroupAutores() {
        return libros.parallelStream() 
                .map(Libro::getAutores)
                .flatMap(List<Autor>::parallelStream)
                .collect(groupingBy(Autor::getGenero));
    }
    
    public void imprimirLibros() {
        libros.stream()
            .map(libro -> libro.getTitulo())
            .distinct()
            .forEach(System.out::print); //titulo -> System.out.println(titulo)
    }
    
    //Obtiene todos los autores de todos los libros
    public List<Autor> getAutoresM() {
        return libros.stream()
                .map(Libro::getAutores) //De cada colección de autores
                .flatMap(autores -> autores.stream()) //Leo uno por uno
                .filter(autor -> autor.getGenero() == Genero.MUJER)
                //Para distinguir objetos de forma correcta objeto, es necesario agregar el metodo equals
                .distinct()
                .collect(Collectors.toList());
    }
    
    public List<Autor> getAutoresGenero(Genero g) {
        return libros.stream()
                .map(Libro::getAutores) //De cada colección de autores
                .flatMap(autores -> autores.stream()) //Leo uno por uno
                .filter(autor -> autor.getGenero() == g)
                //Para distinguir objetos de forma correcta objeto, es necesario agregar el metodo equals
                .distinct()
                .sorted(Comparator.comparing(Autor::getEdad)
                                .thenComparing(Autor::getNombre)
                                .thenComparing(Autor::getGenero))
                .collect(Collectors.toList());
    }
    
    public List<String> getOtros(){
        return libros.stream()
                .map(Libro::getAutores)
                .flatMap(autores -> autores.stream())
                .filter(autor -> autor.getEdad() >= 50)
                .map(Autor::getNombre)
                .map(String::toUpperCase)
                .distinct()
                .limit(15)
                .collect(Collectors.toList());
    }
    
    public int getSumaEdadAutores() {
        return libros.stream()
                .map(Libro::getAutores)
                .flatMap(List<Autor>::stream)
                .peek(autor -> System.out.println("FlatMap:" + autor)) //Herramienta para hacer log
                .distinct()
                .peek(autor -> System.out.println("Distinct:" + autor)) //Ver la salida
                .map(Autor::getEdad)
                .reduce(0, (base, edad) -> base + edad);
        
        /*return libros.stream()
                .map(Libro::getAutores)
                .flatMap(List<Autor>::stream)
                .distinct()
                .mapToInt(Autor::getEdad)
                .sum();*/
    }
}
