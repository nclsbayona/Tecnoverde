package co.edu.javeriana.ms.Calificacion.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.ms.Calificacion.entities.Calificacion;
import co.edu.javeriana.ms.Calificacion.entities.aux.CalificarServicio;
import co.edu.javeriana.ms.Calificacion.entities.dtos.CalificacionDTO;
import co.edu.javeriana.ms.Calificacion.repositories.CalificacionRepository;

@Service
public class CalificacionService {
    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private ProductosService productosService;

    public List<CalificacionDTO> getAllCalificaciones() {
        List<CalificacionDTO> calificaciones = new ArrayList<>();
        for (Calificacion calificacion : calificacionRepository.findAll()) {
            calificaciones.add(new CalificacionDTO(calificacion,
                    usuariosService.getClienteById(Long.parseLong(calificacion.getCliente_ID())),
                    productosService.getServicioById(Long.parseLong(calificacion.getServicio_ID()))));
        }
        return calificaciones;
    }

    public CalificacionDTO createOrUpdate(CalificarServicio compra) {
        Calificacion c=new Calificacion();
        c.setCliente_ID(compra.getCliente().toString());
        c.setServicio_ID(compra.getServicio().toString());
        c.setPuntaje(compra.getCalificacion());
        c.setComentario(compra.getComentario());
        c = calificacionRepository.save(c);
        return new CalificacionDTO(c, usuariosService.getClienteById(compra.getCliente()), productosService.getServicioById(compra.getServicio()));
    }

}
