package co.edu.javeriana.ms.Calificacion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.javeriana.ms.Calificacion.entities.InfoAdicional;
import co.edu.javeriana.ms.Calificacion.entities.Producto;

public interface InfoAdicionalRepository extends JpaRepository<InfoAdicional, Long> {
    @Query("select i from InfoAdicional i, MultimediaXInfo x where x.producto=?1 and x.infoAdicional.id=i.id")
    List<InfoAdicional> findAllByProducto(Producto m);
}
