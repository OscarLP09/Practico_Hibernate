package org.example.dao;

import java.util.List;

/**
 * Interfaz que define los métodos que deben implementar las clases DAO
 * @param <T> Tipo de objeto que manejará la clase DAO
 */
public interface DAO<T> {
    List<T> findAll();
    T findByID(Long id);
    void insert(T t);
    void delete(T t);
    void update(T t);

}