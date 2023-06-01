package co.edu.javeriana.ms.LoadBalancer.entities;

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
public class Redes_Sociales {

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private String facebook;
    @Basic
    private String instagram;
    @Basic
    private String twitter;
}