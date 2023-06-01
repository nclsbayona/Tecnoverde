package co.edu.javeriana.ms.Calificacion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.ms.Calificacion.entities.TipoInformacion;

public interface TipoInformacionRepository extends JpaRepository<TipoInformacion, String>{
    Optional<TipoInformacion> getByTipo(String tipo);
}
