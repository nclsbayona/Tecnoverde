package co.edu.javeriana.ms.Calificacion.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.ms.Calificacion.entities.aux.CalificarServicio;
import co.edu.javeriana.ms.Calificacion.entities.dtos.CalificacionDTO;
import co.edu.javeriana.ms.Calificacion.services.CalificacionService;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService productosService;

    @GetMapping("")
    public List<CalificacionDTO> getAll() {
        List<CalificacionDTO> dto = null;
        dto = productosService.getAllCalificaciones();
        return dto;
    }

    @PostMapping("")
    public CalificacionDTO create(@RequestBody CalificarServicio compra){
        CalificacionDTO dto = null;
        dto = productosService.createOrUpdate(compra);
        return dto;
    }
}
