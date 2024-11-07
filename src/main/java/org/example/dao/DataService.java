package org.example.dao;

import org.example.HibernateUtil;
import org.example.models.Opinion;
import org.example.models.Pelicula;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * La clase DataService proporciona métodos para interactuar con la base de datos y gestionar
 * las entidades relacionadas con películas y opiniones en una plataforma de cine online.
 */
public class DataService {

    /**
     * Historia de usuario 1: Registrar nuevas películas en la base de datos.
     *
     * Registra una nueva película en la base de datos para construir un catálogo que permita
     * añadir opiniones.
     *
     * @param titulo El título de la nueva película a registrar.
     */
    public void registrarPelicula(String titulo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Pelicula nuevaPelicula = new Pelicula(titulo);
            session.persist(nuevaPelicula);
            transaction.commit();
            System.out.println("Pelicula '" + titulo + "' registrada exitosamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Historia de usuario 2: Obtener todas las opiniones realizadas por un usuario específico.
     *
     * Devuelve una lista de opiniones realizadas por un usuario para analizar su participación
     * y opiniones sobre las películas en la plataforma.
     *
     * @param correoUsuario El correo del usuario cuyas opiniones se desean obtener.
     * @return Una lista de objetos Opinion correspondientes al usuario.
     */
    public List<Opinion> obtenerOpinionesPorUsuario(String correoUsuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Opinion> opiniones = null;

        try {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Opinion o WHERE o.usuario = :correo";
            Query<Opinion> query = session.createQuery(hql, Opinion.class);
            query.setParameter("correo", correoUsuario);
            opiniones = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return opiniones;
    }

    /**
     * Historia de usuario 3: Añadir una opinión a una película existente.
     *
     * Añade una nueva opinión a una película existente, identificada por su ID.
     *
     * @param peliculaId El ID de la película a la que se desea añadir la opinión.
     * @param descripcion La descripción de la opinión.
     * @param usuario El correo del usuario que realiza la opinión.
     * @param puntuacion La puntuación otorgada a la película.
     */
    public void añadirOpinionAPelicula(Long peliculaId, String descripcion, String usuario, Integer puntuacion) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Pelicula pelicula = session.get(Pelicula.class, peliculaId);
            if (pelicula != null) {
                Opinion nuevaOpinion = new Opinion();
                nuevaOpinion.setDescripcion(descripcion);
                nuevaOpinion.setUsuario(usuario);
                nuevaOpinion.setPuntuacion(puntuacion);
                nuevaOpinion.setPelicula(pelicula);
                pelicula.getOpiniones().add(nuevaOpinion);
                session.persist(nuevaOpinion);
                transaction.commit();
                System.out.println("Opinión añadida con éxito a la película: " + pelicula.getTitulo());
            } else {
                System.out.println("Película no encontrada con ID: " + peliculaId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Historia de usuario 4: Listar películas con opiniones de puntuación igual o inferior a 3.
     *
     * Devuelve una lista de los títulos de todas las películas que tienen al menos una opinión
     * con una puntuación igual o menor a 3.
     *
     * @return Una lista de títulos de películas con al menos una opinión de baja puntuación.
     */
    public List<String> obtenerPeliculasConOpinionesBajas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<String> titulosPeliculas = null;

        try {
            Transaction transaction = session.beginTransaction();
            String hql = "SELECT DISTINCT p.titulo " +
                    "FROM Pelicula p " +
                    "JOIN p.opiniones o " +
                    "WHERE o.puntuacion <= 3";
            Query<String> query = session.createQuery(hql, String.class);
            titulosPeliculas = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return titulosPeliculas;
    }
}
