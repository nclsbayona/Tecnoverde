package co.edu.javeriana.ms.Calificacion.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.edu.javeriana.ms.Calificacion.entities.dtos.ServicioDTO;

@Service
public class ProductosService {

    @Autowired
    private RestTemplate restTemplate;

    public ServicioDTO getServicioById(Long id) {
        return restTemplate.getForObject("http://Productos/api/servicios/clientes/servicio?id=" + String.valueOf(id),
                ServicioDTO.class);
    }

    public List<ServicioDTO> getServiciosByTipo(String tipo) {
        List<ServicioDTO> l = new ArrayList<>();
        for (ServicioDTO s : restTemplate.getForObject("http://Productos/api/servicios/clientes?tipo=" + tipo,
                ServicioDTO[].class)) {
            l.add(s);
        }
        return l;
    }
}