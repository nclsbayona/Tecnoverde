package co.edu.javeriana.ms.Calificacion.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Calificacion {

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private String puntaje;
    @Basic
    private String comentario;
    @Basic
    private String cliente_ID;
    @Basic
    private String servicio_ID;

}