package co.edu.javeriana.ms.Calificacion.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.javeriana.ms.Calificacion.entities.Calificacion;


public interface CalificacionRepository extends JpaRepository<Calificacion, Long>{
    @Query("select c from Calificacion c where c.id = ?1")
    Optional<Calificacion> findById(Long id);
    @Query("select c from Calificacion c where c.cliente_ID = ?1")
    List<Calificacion> findByClienteId(Long id);
    @Query("select c from Calificacion c where c.servicio_ID = ?1")
    List<Calificacion> findByServicioId(Long id);
}
