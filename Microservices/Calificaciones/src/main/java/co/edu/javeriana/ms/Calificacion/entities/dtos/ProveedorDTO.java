package co.edu.javeriana.ms.Calificacion.entities.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProveedorDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombre;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String edad;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String descripcion;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String foto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String telefono;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pagina_web;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String facebook;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String instagram;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String twitter;
}
