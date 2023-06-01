package co.edu.javeriana.ms.Calificacion.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(MultimediaXInfoPK.class)
public class MultimediaXInfo {

    @Id
    @ManyToOne
    private Producto producto;
    @Id
    @ManyToOne
    private InfoAdicional infoAdicional;

}