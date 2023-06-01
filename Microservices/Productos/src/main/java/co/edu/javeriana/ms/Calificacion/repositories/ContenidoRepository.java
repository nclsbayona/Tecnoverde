package co.edu.javeriana.ms.Calificacion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.javeriana.ms.Calificacion.entities.Contenido;
import co.edu.javeriana.ms.Calificacion.entities.TipoContenido;


public interface ContenidoRepository extends JpaRepository<Contenido, Long>{
    List<Contenido> findByTipoContenido(TipoContenido t);

    @Query("select c from Contenido c where c.nombre like %:nombre%")
    List<Contenido> findByNombre(@Param(value = "nombre") String nombre);

    List<Contenido> findByDescripcion(String descripcion);

    List<Contenido> findByTipoContenidoTipo(String t);

    @Query("select c from Contenido c where c.proveedor_ID = ?1")
    List<Contenido> findByProveedor_ID(String proveedor_id);
}
