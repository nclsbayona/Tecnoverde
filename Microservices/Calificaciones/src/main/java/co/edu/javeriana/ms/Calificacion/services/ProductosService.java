package co.edu.javeriana.ms.Calificacion.services;

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
}
