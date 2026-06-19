package com.example.PELICULAS.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "peliculas")
@Data
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String director;

    @Column(name = "anio_lanzamiento")
    private Integer anioLanzamiento;

    private String genero;
    private String sinopsis;
}
