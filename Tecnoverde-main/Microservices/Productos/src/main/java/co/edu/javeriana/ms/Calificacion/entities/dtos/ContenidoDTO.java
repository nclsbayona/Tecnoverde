package co.edu.javeriana.ms.Calificacion.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.javeriana.ms.Calificacion.entities.Contenido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContenidoDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombre;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String descripcion;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tipo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProveedorDTO proveedor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<InfoAdicionalDTO> infoAdicional;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PreguntaDTO> preguntas;

    public ContenidoDTO(Contenido contenido, ProveedorDTO proveedorDTO, List<InfoAdicionalDTO> infoAdicional, List<PreguntaDTO> preguntas) {
        this.nombre = contenido.getNombre();
        this.descripcion = contenido.getDescripcion();
        this.proveedor = proveedorDTO;
        this.tipo = contenido.getTipoContenido().getTipo();
        this.infoAdicional = infoAdicional;
        this.preguntas = preguntas;
    }
}
