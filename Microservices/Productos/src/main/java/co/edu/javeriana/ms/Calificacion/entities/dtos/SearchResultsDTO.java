package co.edu.javeriana.ms.Calificacion.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchResultsDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ServicioDTO> servicios;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ContenidoDTO> contenidos;

    public SearchResultsDTO(List<ServicioDTO> servicios, List<ContenidoDTO> contenidos) {
        this.servicios = servicios;
        this.contenidos = contenidos;
    }
}
