package co.edu.javeriana.ms.Calificacion.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pregunta {

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private String pregunta;
    @Basic
    private String cliente_ID;
    @ManyToOne
    private Producto producto;

}