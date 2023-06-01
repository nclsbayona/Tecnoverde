package co.edu.javeriana.ms.Calificacion.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Servicio extends Producto {

    @Basic
    private String precio;
    @ManyToOne
    private TipoServicio tipoServicio;

}