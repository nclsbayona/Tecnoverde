package co.edu.javeriana.ms.Calificacion.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServicioDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombre;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String descripcion;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String tipo;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String precio;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProveedorDTO proveedor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<InfoAdicionalDTO> infoAdicional;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PreguntaDTO> preguntas;

}
