package co.edu.javeriana.ms.Calificacion.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.javeriana.ms.Calificacion.entities.Compra;
import co.edu.javeriana.ms.Calificacion.entities.dtos.CompraDTO;
import co.edu.javeriana.ms.Calificacion.repositories.CompraRepository;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private ProductosService productosService;

    public List<CompraDTO> getAllCompras() {
        List<Compra> compras = compraRepository.findAll();
        List<CompraDTO> comprasDTO = new ArrayList<>();
        for (Compra compra : compras) {
            comprasDTO.add(new CompraDTO(compra,
                    usuariosService.getClienteById(Long.parseLong(compra.getCliente_ID())),
                    productosService.getServicioById(Long.parseLong(compra.getServicio_ID()))));
        }
        return comprasDTO;
    }

    public CompraDTO getCompraById(Long id) {
        Compra compra = compraRepository.findById(id).orElse(null);
        if (compra != null) {
            return new CompraDTO(compra,
                    usuariosService.getClienteById(Long.parseLong(compra.getCliente_ID())),
                    productosService.getServicioById(Long.parseLong(compra.getServicio_ID())));
        }
        return null;
    }

    public CompraDTO pagarCompra(Long id, String card) {
        if (card != null) {
            Compra compra = compraRepository.findById(id).orElse(null);
            if (compra != null) {
                compra.setCancelada(true);
                compra = compraRepository.save(compra);
                return new CompraDTO(compra,
                        usuariosService.getClienteById(Long.parseLong(compra.getCliente_ID())),
                        productosService.getServicioById(Long.parseLong(compra.getServicio_ID())));
            }
        }
        return null;
    }

    public List<CompraDTO> getAllComprasByClienteID(Long id) {
        List<Compra> compras = compraRepository.findByClienteId(id);
        List<CompraDTO> comprasDTO = new ArrayList<>();
        for (Compra compra : compras) {
            comprasDTO.add(new CompraDTO(compra,
                    usuariosService.getClienteById(Long.parseLong(compra.getCliente_ID())),
                    productosService.getServicioById(Long.parseLong(compra.getServicio_ID()))));
        }
        return comprasDTO;
    }

    public CompraDTO createOrUpdate(Compra c) {
        c = compraRepository.save(c);
        return new CompraDTO(c, usuariosService.getClienteById(Long.parseLong(c.getCliente_ID())),
                productosService.getServicioById(Long.parseLong(c.getServicio_ID())));
    }

}
