package co.edu.javeriana.ms.Calificacion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.edu.javeriana.ms.Calificacion.entities.dtos.ClienteDTO;

@Service
public class UsuariosService {

    @Autowired
    private RestTemplate restTemplate;

    public ClienteDTO getClienteById(Long id) {
        return restTemplate.getForObject("http://Auth-Service/api/cuentas/cliente?id=" + String.valueOf(id),
                ClienteDTO[].class)[0];
    }

}
