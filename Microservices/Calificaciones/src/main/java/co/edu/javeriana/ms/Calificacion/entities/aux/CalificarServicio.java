package co.edu.javeriana.ms.Calificacion.entities.aux;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CalificarServicio {
    private Long cliente;
    private Long servicio;
    private String calificacion;
    private String comentario;
}
