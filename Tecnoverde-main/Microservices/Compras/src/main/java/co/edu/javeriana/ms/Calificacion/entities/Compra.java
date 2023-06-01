package co.edu.javeriana.ms.Calificacion.entities;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Compra {

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private boolean cancelada;
    @Basic
    private Date fecha;
    @Basic
    private String servicio_ID;
    @Basic
    private String cliente_ID;
    @Basic
    private Date fecha_inicio;
    @Basic
    private Date fecha_fin;
}