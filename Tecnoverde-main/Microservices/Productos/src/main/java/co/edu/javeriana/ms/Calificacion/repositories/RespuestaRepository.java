package co.edu.javeriana.ms.Calificacion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.javeriana.ms.Calificacion.entities.Pregunta;
import co.edu.javeriana.ms.Calificacion.entities.Respuesta;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long>{
    @Query("select r from Respuesta r where r.pregunta=?1")
    List<Respuesta> findAllByPregunta(Pregunta p);
    @Query("select r.texto from Respuesta r where r.pregunta=?1")
    List<String> findAllTextosByPregunta(Pregunta p);
    @Query("select r.texto from Respuesta r")
    List<String> findAllTextos();
}
