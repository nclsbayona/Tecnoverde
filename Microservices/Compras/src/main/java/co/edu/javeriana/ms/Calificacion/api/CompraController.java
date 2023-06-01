package co.edu.javeriana.ms.Calificacion.api;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.javeriana.ms.Calificacion.entities.Compra;
import co.edu.javeriana.ms.Calificacion.entities.aux.CrearCompra;
import co.edu.javeriana.ms.Calificacion.entities.dtos.ClienteDTO;
import co.edu.javeriana.ms.Calificacion.entities.dtos.CompraDTO;
import co.edu.javeriana.ms.Calificacion.entities.dtos.ServicioDTO;
import co.edu.javeriana.ms.Calificacion.services.CompraService;
import co.edu.javeriana.ms.Calificacion.services.ProductosService;
import co.edu.javeriana.ms.Calificacion.services.UsuariosService;

@RestController
@RequestMapping("/api/compras")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private ProductosService productosService;

    @GetMapping
    public List<CompraDTO> getAll() {
        return compraService.getAllCompras();
    }

    @PostMapping("/compra")
    public CompraDTO create(@RequestBody CrearCompra compra,
            @RequestParam(required = false, name = "card") String card_number) {
        try {
            ClienteDTO cliente = usuariosService.getClienteById(compra.getCliente());
            ServicioDTO servicio = productosService.getServicioById(compra.getServicio());
            if (cliente != null && servicio != null) {
                Compra c = new Compra();

                // Simulacion
                c.setCancelada(card_number != null);

                c.setFecha(Date.from(Instant.now()));
                c.setCliente_ID(String.valueOf(compra.getCliente()));
                c.setServicio_ID(String.valueOf(compra.getServicio()));
                return compraService.createOrUpdate(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/compra")
    public CompraDTO pagar(@RequestParam Long id, @RequestParam String card) {
        return compraService.pagarCompra(id, card);
    }
}
