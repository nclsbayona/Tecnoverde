package co.edu.javeriana.ms.Calificacion.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TipoInformacion {

    @Id
    private String tipo;

}