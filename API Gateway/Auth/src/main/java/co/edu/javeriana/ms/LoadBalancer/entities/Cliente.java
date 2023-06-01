package co.edu.javeriana.ms.LoadBalancer.entities;

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
public class Cliente {

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private String username;
    @Basic
    private String password;
    @Basic
    private String nombre;
    @Basic
    private String edad;
    @Basic
    private String descripcion;
    @ManyToOne
    private Foto foto;

}