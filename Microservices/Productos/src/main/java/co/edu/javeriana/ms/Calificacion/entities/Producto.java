package co.edu.javeriana.ms.Calificacion.entities;

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
public abstract class Producto {

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private String nombre;
    @Basic
    private String descripcion;
    @Basic
    private String proveedor_ID;
}