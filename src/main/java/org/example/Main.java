package org.example;

import org.example.dao.DataService;
import org.example.models.Opinion;

import java.util.List;

/**
 * La clase Main es la clase principal de la aplicación, que se encarga de iniciar y ejecutar
 * las funcionalidades implementadas en DataService para gestionar las historias de usuario.
 */
public class Main {

    /**
     * Método principal que ejecuta la aplicación e interactúa con el servicio de datos para
     * registrar películas, obtener opiniones y realizar otras operaciones.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {

        HibernateUtil.getSessionFactory();

        // Historia de Usuario 1: Registrar nuevas películas en la base de datos.
        DataService dataService = new DataService();
        dataService.registrarPelicula("Toy Story 4");

        // Historia de Usuario 2: Obtener todas las opiniones realizadas por un usuario específico.
        DataService dataService2 = new DataService();
        String correoUsuario = "user1@example.com";
        List<Opinion> opiniones = dataService2.obtenerOpinionesPorUsuario(correoUsuario);
        opiniones.forEach(System.out::println);

        // Historia de Usuario 3: Añadir una opinión a una película existente.
        DataService dataService3 = new DataService();
        dataService3.añadirOpinionAPelicula(1L, "Una pelicula desastrosa, 1/10", "user3@example.com", 1);

        // Historia de Usuario 4: Listar películas con opiniones de puntuación igual o inferior a 3.
        DataService dataService4 = new DataService();
        List<String> peliculasConOpinionesBajas = dataService.obtenerPeliculasConOpinionesBajas();
        System.out.println("Películas con opiniones de puntuación igual o inferior a 3:");
        peliculasConOpinionesBajas.forEach(System.out::println);
    }
}
