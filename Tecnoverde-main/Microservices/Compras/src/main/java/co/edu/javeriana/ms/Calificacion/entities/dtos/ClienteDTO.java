package co.edu.javeriana.ms.Calificacion.entities.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombre;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String edad;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String descripcion;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String foto;
    
}
