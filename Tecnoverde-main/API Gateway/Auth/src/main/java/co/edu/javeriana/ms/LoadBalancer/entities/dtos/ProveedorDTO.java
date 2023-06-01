package co.edu.javeriana.ms.LoadBalancer.entities.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.javeriana.ms.LoadBalancer.entities.Proveedor;
import lombok.Getter;

@Getter
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


    public ProveedorDTO(Proveedor proveedor) {
        this.nombre = proveedor.getNombre();
        this.edad = proveedor.getEdad();
        this.descripcion = proveedor.getDescripcion();
        this.foto = proveedor.getFoto().getUrl();
        this.telefono = proveedor.getTelefono();
        this.pagina_web = proveedor.getPagina_web();
        this.facebook = proveedor.getRedesSociales().getFacebook();
        this.instagram = proveedor.getRedesSociales().getInstagram();
        this.twitter = proveedor.getRedesSociales().getTwitter();
    }
}
