package com.example.PELICULAS.controller;

import com.example.PELICULAS.model.Pelicula;
import com.example.PELICULAS.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @PostMapping
    public Pelicula crearPelicula(@RequestBody Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @GetMapping
    public List<Pelicula> obtenerTodas() {
        return peliculaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        return peliculaRepository.findById(id)
                .map(pelicula -> ResponseEntity.ok().body(pelicula))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula datosActualizados) {
        return peliculaRepository.findById(id)
                .map(pelicula -> {
                    pelicula.setTitulo(datosActualizados.getTitulo());
                    pelicula.setDirector(datosActualizados.getDirector());
                    pelicula.setAnioLanzamiento(datosActualizados.getAnioLanzamiento());
                    pelicula.setGenero(datosActualizados.getGenero());
                    pelicula.setSinopsis(datosActualizados.getSinopsis());
                    Pelicula guardada = peliculaRepository.save(pelicula);
                    return ResponseEntity.ok().body(guardada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id) {
        return peliculaRepository.findById(id)
                .map(pelicula -> {
                    peliculaRepository.delete(pelicula);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
