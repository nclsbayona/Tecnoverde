package co.edu.javeriana.ms.Calificacion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.javeriana.ms.Calificacion.entities.Pregunta;
import co.edu.javeriana.ms.Calificacion.entities.Producto;


public interface PreguntaRepository extends JpaRepository<Pregunta, Long>{
    @Query("select p from Pregunta p where p.producto=?1")
    List<Pregunta> findAllByProducto(Producto p);
    @Query("select p from Pregunta p where p.id=?1")
    Pregunta findAllById(Long id);
}
