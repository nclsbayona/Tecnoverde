package co.edu.javeriana.ms.Calificacion.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.javeriana.ms.Calificacion.entities.Pregunta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PreguntaDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClienteDTO cliente;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ContenidoDTO contenido;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ServicioDTO servicio;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String texto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> respuestas;

    public PreguntaDTO(Pregunta p, ClienteDTO clienteDTO, ContenidoDTO contenidoDTO, ServicioDTO servicioDTO,
            List<String> respuestas) {
        this.cliente = clienteDTO;
        this.texto = p.getPregunta();
        if (contenidoDTO != null)
            this.contenido = contenidoDTO;
        else if (servicioDTO != null)
            this.servicio = servicioDTO;
        this.respuestas = respuestas;
    }
}
