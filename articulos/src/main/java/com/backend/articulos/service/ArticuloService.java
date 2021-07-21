package com.backend.articulos.service;

import com.backend.articulos.entity.Articulo;
import com.backend.articulos.entity.crud.ArticuloCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {

    @Autowired
    private ArticuloCrudRepository articuloCrudRepository;

    public List<Articulo> getArticulos(){
        return (List<Articulo>) articuloCrudRepository.findAll();
    }

    public List<Articulo> getArticulosByDescripcion(String descripcion){
        return articuloCrudRepository.findByDescripcionContaining(descripcion);
    }

    public Optional<Articulo> getArticuloById(Integer id){
        return articuloCrudRepository.findById(id);
    }

    public Articulo saveArticulo(Articulo articulo){
        Articulo art = new Articulo();
        art.setId(articuloCrudRepository.getSequence());
        art.setDescripcion(articulo.getDescripcion());
        art.setMarca(articulo.getMarca());
        art.setModelo(articulo.getModelo());
        return articuloCrudRepository.save(art);
    }

    public Articulo updateArticulo(Articulo articulo){
        return articuloCrudRepository.save(articulo);
    }

    public boolean deleteArticulo(int ArticuloId){
        return getArticuloById(ArticuloId).map(articulo -> {articuloCrudRepository.delete(articulo);
        return true;
        }).orElse(false);
    }
}
