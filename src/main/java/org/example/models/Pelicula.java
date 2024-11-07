package org.example.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @OneToMany(mappedBy = "pelicula",
               fetch = FetchType.EAGER,
               cascade = CascadeType.PERSIST
    )
    @ToString.Exclude
    private List<Opinion> opiniones = new ArrayList<>();

    public Pelicula() {}


    public Pelicula(String titulo) {
        this.titulo = titulo;
    }


}
