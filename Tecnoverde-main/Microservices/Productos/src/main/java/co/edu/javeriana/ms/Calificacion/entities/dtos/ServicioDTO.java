package co.edu.javeriana.ms.Calificacion.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.javeriana.ms.Calificacion.entities.Servicio;
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

    public ServicioDTO(Servicio s, ProveedorDTO proveedor, List<InfoAdicionalDTO> infoAdicional,
            List<PreguntaDTO> preguntaDTOs) {
        this.nombre = s.getNombre();
        this.descripcion = s.getDescripcion();
        this.precio = s.getPrecio();
        this.proveedor = proveedor;
        this.infoAdicional=infoAdicional;
        this.preguntas = preguntaDTOs;
        this.tipo = s.getTipoServicio().getTipo();
    }

}
