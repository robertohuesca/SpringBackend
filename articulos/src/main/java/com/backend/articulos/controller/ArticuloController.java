package com.backend.articulos.controller;

import com.backend.articulos.entity.Articulo;
import com.backend.articulos.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/articulos")
    public ResponseEntity<List<Articulo>> getAll(@RequestParam(required = false, defaultValue = "1") String descripcion){
        if (!descripcion.equals("1")){
            return new ResponseEntity<>(articuloService.getArticulosByDescripcion(descripcion), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(articuloService.getArticulos(), HttpStatus.OK);
        }
    }

    @GetMapping("/articulos/{id}")
    public ResponseEntity<Optional<Articulo>> getArticulo(@PathVariable("id") Integer id){
        return new ResponseEntity<>(articuloService.getArticuloById(id), HttpStatus.OK);
    }

    @PostMapping("/articulos")
    public ResponseEntity<Articulo> createArticulo(@RequestBody Articulo articulo){
        return new ResponseEntity<>(articuloService.saveArticulo(articulo), HttpStatus.CREATED);
    }

    @PutMapping("/articulos/{id}")
    public ResponseEntity<Articulo> updateArticulo(@PathVariable("id") long id, @RequestBody Articulo articulo){
        return new ResponseEntity<>(articuloService.updateArticulo(articulo), HttpStatus.OK);
    }

    @DeleteMapping("/articulos/{id}")
    public ResponseEntity deleteArticulo(@PathVariable("id") int id){
        if (articuloService.deleteArticulo(id)){
            return new ResponseEntity(HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
