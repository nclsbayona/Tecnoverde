package co.edu.javeriana.ms.Calificacion.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.ms.Calificacion.entities.dtos.ContenidoDTO;
import co.edu.javeriana.ms.Calificacion.entities.dtos.SearchResultsDTO;
import co.edu.javeriana.ms.Calificacion.entities.dtos.ServicioDTO;
import co.edu.javeriana.ms.Calificacion.services.ProductosService;

@RestController
@RequestMapping("/api/productos/clientes")
public class ProductoController {

    @Autowired
    private ProductosService productosService;

    @GetMapping()
    public SearchResultsDTO search(@RequestParam(required = false) String tipo_servicio,
            @RequestParam(required = false) String tipo_contenido, @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion) {
        SearchResultsDTO dto = null;
        List<ServicioDTO> arrS=null;
        List<ContenidoDTO> arrC=null;
        if (tipo_servicio != null) {
            arrS = productosService.getServiciosByTipo(tipo_servicio);
            arrC = null;
            if (tipo_contenido != null) {
                arrC = productosService.getContenidosByTipo(tipo_contenido);
            } else {
                arrC = productosService.getAllContenidos();
            }
        } else if (tipo_contenido != null) {
            arrS = productosService.getAllServicios();
            arrC = productosService.getContenidosByTipo(tipo_contenido);
        } else if (nombre != null) {
            arrS = productosService.getServiciosByNombre(nombre);
            arrC = productosService.getContenidosByNombre(nombre);
        } else if (descripcion != null) {
            arrS = productosService.getServiciosByDescripcion(descripcion);
            arrC = productosService.getContenidosByDescripcion(descripcion);
        } else {
            arrS = productosService.getAllServicios();
            arrC = productosService.getAllContenidos();
        }
        dto = new SearchResultsDTO(arrS, arrC);
        return dto;
    }
    
}
