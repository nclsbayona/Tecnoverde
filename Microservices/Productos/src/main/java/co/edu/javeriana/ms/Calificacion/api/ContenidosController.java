package co.edu.javeriana.ms.Calificacion.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.ms.Calificacion.entities.dtos.ContenidoDTO;
import co.edu.javeriana.ms.Calificacion.services.ProductosService;

@RestController
@RequestMapping("/api/contenidos/clientes")
public class ContenidosController {
    @Autowired
    private ProductosService productosService;

    @GetMapping("")
    public List<ContenidoDTO> getAllContenidos(@RequestParam(required = false) String tipo) {
        List<ContenidoDTO> servicioDTOs = null;
        if (tipo == null)
            servicioDTOs=productosService.getAllContenidos();
        else
            servicioDTOs=productosService.getContenidosByTipo(tipo);
        return servicioDTOs;
    }

    @GetMapping("/contenido")
    public ContenidoDTO getContenidoById(@RequestParam Long id) {
        return productosService.getContenidoById(id);
    }
}
