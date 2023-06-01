package co.edu.javeriana.ms.Calificacion.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.ms.Calificacion.entities.dtos.ServicioDTO;
import co.edu.javeriana.ms.Calificacion.services.ProductosService;

@RestController
@RequestMapping("/api/servicios/clientes")
public class ServiciosController {

    @Autowired
    private ProductosService productosService;

    @GetMapping("")
    public List<ServicioDTO> getAllServices(@RequestParam(required = false) String tipo) {
        List<ServicioDTO> servicioDTOs = null;
        if (tipo == null)
            servicioDTOs = productosService.getAllServicios();
        else
            servicioDTOs = productosService.getServiciosByTipo(tipo);
        return servicioDTOs;
    }

    @GetMapping("/servicio")
    public ServicioDTO getServiceById(@RequestParam Long id) {
        return productosService.getServicioById(id);
    }
}
