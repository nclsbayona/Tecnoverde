package co.edu.javeriana.ms.Calificacion.entities.aux;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CrearCompra {
    private Long cliente;
    private Long servicio;
    private Date fecha_inicio;
    private Date fecha_fin;
}