package co.edu.javeriana.ms.Calificacion.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.ms.Calificacion.entities.TipoContenido;
public interface TipoContenidoRepository extends JpaRepository<TipoContenido, String>{

    TipoContenido findByTipo(String string);
    
}
