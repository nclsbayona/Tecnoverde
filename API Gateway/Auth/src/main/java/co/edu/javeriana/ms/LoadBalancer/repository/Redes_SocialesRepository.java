package co.edu.javeriana.ms.LoadBalancer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.ms.LoadBalancer.entities.Redes_Sociales;


public interface Redes_SocialesRepository extends JpaRepository<Redes_Sociales, Long>{
    List<Redes_Sociales> findByFacebook(String facebook);
    List<Redes_Sociales> findByInstagram(String instagram);
    List<Redes_Sociales> findByTwitter(String twitter);
}