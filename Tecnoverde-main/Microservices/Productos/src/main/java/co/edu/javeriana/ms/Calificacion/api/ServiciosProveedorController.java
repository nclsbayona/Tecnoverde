package co.edu.javeriana.ms.Calificacion.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.ms.Calificacion.entities.aux.CrearServicio;
import co.edu.javeriana.ms.Calificacion.entities.dtos.ServicioDTO;
import co.edu.javeriana.ms.Calificacion.services.ProductosService;

@RestController
@RequestMapping("/api/servicios/proveedores")
public class ServiciosProveedorController {

    @Autowired
    private ProductosService productosService;

    @PostMapping("/servicio")
    public ServicioDTO createService(@RequestBody CrearServicio crearServicio) {
        return productosService.createOrUpdateService(crearServicio);
    }
}