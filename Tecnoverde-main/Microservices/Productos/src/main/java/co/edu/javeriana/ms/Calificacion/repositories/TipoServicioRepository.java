package co.edu.javeriana.ms.Calificacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.javeriana.ms.Calificacion.entities.TipoServicio;

public interface TipoServicioRepository extends JpaRepository<TipoServicio, String>{

    TipoServicio findByTipo(String string);
}
