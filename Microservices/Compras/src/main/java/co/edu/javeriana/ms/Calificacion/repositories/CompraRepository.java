package co.edu.javeriana.ms.Calificacion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.javeriana.ms.Calificacion.entities.Compra;


public interface CompraRepository extends JpaRepository<Compra, Long>{
    @Query("select c from Compra c where c.cliente_ID = ?1")
    List<Compra> findByClienteId(Long id);
    @Query("select c from Compra c where c.servicio_ID = ?1")
    List<Compra> findByServicioId(Long id);
}