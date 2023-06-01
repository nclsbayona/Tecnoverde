package co.edu.javeriana.ms.LoadBalancer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.ms.LoadBalancer.entities.Cliente;
import co.edu.javeriana.ms.LoadBalancer.entities.Foto;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    List<Cliente> findByFoto(Foto f);
    List<Cliente> findByNombre(String nombre);
    List<Cliente> findByEdad(String edad);
    List<Cliente> findByUsername(String username);
}
