package co.edu.javeriana.ms.Calificacion.entities.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.javeriana.ms.Calificacion.entities.Calificacion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CalificacionDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String puntaje;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String comentario;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClienteDTO cliente;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ServicioDTO servicio;

    public CalificacionDTO(Calificacion c, ClienteDTO cliente, ServicioDTO servicio) {
        this.puntaje = c.getPuntaje();
        this.comentario = c.getComentario();
        this.cliente = cliente;
        this.servicio = servicio;
    }
}
