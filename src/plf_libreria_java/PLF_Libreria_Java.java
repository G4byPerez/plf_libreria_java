
package plf_libreria_java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import model.Autor;
import model.Genero;
import model.Libreria;
import model.Libro;

/**
 *
 * @author monse
 */
public class PLF_Libreria_Java {

    public static void main(String[] args) {
        Libreria  libreria = new Libreria("Ocho Libros");
        
        ArrayList<Autor> autor = new ArrayList<>();
        autor.add(new Autor("Craig Larman",70, Genero.HOMBRE));
        Libro libro = new Libro("UML y Patrones",autor,"7ma","1234567890");
        libreria.agregarLibro(libro);
        
        autor = new ArrayList<>();
        autor.add(new Autor("Jane Austen",100, Genero.MUJER));
        libro = new Libro("Orgullo y Prejuicio",autor,"7ma","1234567890");
        libreria.agregarLibro(libro);
        
        autor = new ArrayList<>();
        autor.add(new Autor("Jane Austen",100, Genero.MUJER));
        libro = new Libro("Sensatez y sentimiento",autor,"7ma","1234567890");
        libreria.agregarLibro(libro);
        
        //Obtener todos los nombres de todos los libros
        List<String> libros = libreria.getLibros();
        System.out.println("-- Titulos disponibles: " + libros);
        
        System.out.println("-- Autores mujeres: " + libreria.getAutoresM());
        
        System.out.println("-- Suma edades autores: " +  libreria.getSumaEdadAutores());
        
        System.out.println("-- Usando groupingBy");
        System.out.println(libreria.getGroupAutores());
        
        int suma = IntStream.range(0, 100)
                            .sum();
        System.out.println("-- Suma usando IntStream: " + suma);
        
        /*IntStream.iterate(0, valor -> valor + 1)
                 .limit(15)
                 .forEach(valor -> System.out.println(valor));*/
    }
    
}
