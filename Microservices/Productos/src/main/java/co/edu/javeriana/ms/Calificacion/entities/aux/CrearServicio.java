package co.edu.javeriana.ms.Calificacion.entities.aux;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CrearServicio {
    private String nombre;
    private String descripcion;
    private String proveedor_ID;
    private String precio;
    private String tipo_servicio;
    private Map<String, String> informacionAdicional;
}