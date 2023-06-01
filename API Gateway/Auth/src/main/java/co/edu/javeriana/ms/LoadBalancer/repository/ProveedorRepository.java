package co.edu.javeriana.ms.LoadBalancer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.ms.LoadBalancer.entities.Foto;
import co.edu.javeriana.ms.LoadBalancer.entities.Proveedor;
import co.edu.javeriana.ms.LoadBalancer.entities.Redes_Sociales;


public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{
    List<Proveedor> findByFoto(Foto f);
    List<Proveedor> findByNombre(String nombre);
    List<Proveedor> findByEdad(String edad);
    List<Proveedor> findByRedesSociales(Redes_Sociales redes);
    List<Proveedor> findByUsername(String username);
}
