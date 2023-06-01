package co.edu.javeriana.ms.Calificacion.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

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
    private ServicioDTO servicio;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String texto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> respuestas;
}
