package co.edu.javeriana.ms.Calificacion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import co.edu.javeriana.ms.Calificacion.entities.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long>{
    List<Servicio> findByTipoServicioTipo(String tipo);

    @Query("select s from Servicio s where s.nombre like %:nombre%")
    List<Servicio> findByNombre(@Param(value = "nombre") String nombre);

    List<Servicio> findByDescripcion(String descripcion);

    @Query("select c from Servicio c where c.proveedor_ID = ?1")
    List<Servicio> findByProveedorID(String proveedor);
}
