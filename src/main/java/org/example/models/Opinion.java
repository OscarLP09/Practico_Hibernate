package org.example.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Entity
@Table(name = "opinion")
public class Opinion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String usuario;
    private Integer puntuacion;

    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    @Override
    public String toString() {
        return "Opinion{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", usuario='" + usuario + '\'' +
                ", puntuacion=" + puntuacion +
                ", pelicula=" + pelicula +
                '}';
    }
}
