package com.backend.articulos.entity.crud;

import com.backend.articulos.entity.Articulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticuloCrudRepository extends CrudRepository<Articulo, Integer> {
    List<Articulo> findByDescripcionContaining(String descripcion);

    @Query(nativeQuery = true, value = "SELECT seq_articulo.NEXTVAL as id_Secuencia From dual")
    Integer getSequence();
}
