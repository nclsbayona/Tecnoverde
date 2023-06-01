package co.edu.javeriana.ms.LoadBalancer.entities.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.javeriana.ms.LoadBalancer.entities.Cliente;
import lombok.Getter;

@Getter
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

	public ClienteDTO(Cliente cliente) {
        this.username = cliente.getUsername();
        this.nombre = cliente.getNombre();
        this.edad = cliente.getEdad();
        this.descripcion = cliente.getDescripcion();
        this.foto = cliente.getFoto().getUrl();
	}
    
}
