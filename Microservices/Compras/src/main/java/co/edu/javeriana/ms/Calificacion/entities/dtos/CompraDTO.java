package co.edu.javeriana.ms.Calificacion.entities.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.javeriana.ms.Calificacion.entities.Compra;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompraDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean pagado;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date fecha;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date fecha_inicio;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date fecha_fin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClienteDTO cliente;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ServicioDTO servicio;
    
    public CompraDTO(Compra c, ClienteDTO cliente, ServicioDTO servicio) {
        this.pagado = c.isCancelada();
        this.fecha = c.getFecha();
        this.cliente = cliente;
        this.servicio = servicio;
        this.fecha_inicio=c.getFecha_inicio();
        this.fecha_fin=c.getFecha_fin();
    }
    
}
