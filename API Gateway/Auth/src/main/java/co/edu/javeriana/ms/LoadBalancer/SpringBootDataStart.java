package co.edu.javeriana.ms.LoadBalancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.edu.javeriana.ms.LoadBalancer.entities.Cliente;
import co.edu.javeriana.ms.LoadBalancer.entities.Foto;
import co.edu.javeriana.ms.LoadBalancer.entities.Proveedor;
import co.edu.javeriana.ms.LoadBalancer.entities.Redes_Sociales;
import co.edu.javeriana.ms.LoadBalancer.repository.ClienteRepository;
import co.edu.javeriana.ms.LoadBalancer.repository.FotosRepository;
import co.edu.javeriana.ms.LoadBalancer.repository.ProveedorRepository;
import co.edu.javeriana.ms.LoadBalancer.repository.Redes_SocialesRepository;
@Configuration
class SpringBootDataStart {

    @Autowired
    private FotosRepository fotosRepository;

    @Autowired
    private Redes_SocialesRepository redes_SocialesRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Bean
    CommandLineRunner startCommandLineRunner(){
        return args -> {
            System.out.println("Spring Boot started correctly!");
        };
    }

    @Bean
    CommandLineRunner initFotos(){
        return args -> {
            Foto f=new Foto();
            f.setUrl("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg");
            fotosRepository.save(f);

            f=new Foto();
            f.setUrl("https://images.pexels.com/photos/1222271/pexels-photo-1222271.jpeg");
            fotosRepository.save(f);

            f=new Foto();
            f.setUrl("https://images.pexels.com/photos/1239291/pexels-photo-1239291.jpeg");
            fotosRepository.save(f);

            f=new Foto();
            f.setUrl("https://avatars.githubusercontent.com/u/59931437");
            fotosRepository.save(f);
        };
    }

    @Bean 
    CommandLineRunner initRedes_Sociales(){
        return args -> {
            Redes_Sociales r=new Redes_Sociales();
            r.setFacebook("https://www.facebook.com/andres.gonzalez.581");
            r.setInstagram("https://www.instagram.com/andres.gonzalez.581");
            r.setTwitter("https://twitter.com/andres.gonzalez.581");
            redes_SocialesRepository.save(r);

            r=new Redes_Sociales();
            r.setFacebook("https://www.facebook.com/juan_peres.2");
            r.setInstagram("https://www.instagram.com/juan_peres.2");
            r.setTwitter("https://twitter.com/juan_peres.2");
            redes_SocialesRepository.save(r);

            r=new Redes_Sociales();
            r.setFacebook("https://www.facebook.com/carlos_barriga_0071xd");
            r.setInstagram("https://www.instagram.com/carlos_barriga_0071xd");
            r.setTwitter("https://twitter.com/carlos_barriga_0071xd");
            redes_SocialesRepository.save(r);

            r=new Redes_Sociales();
            r.setFacebook("https://www.facebook.com/veronica_santamaria");
            r.setInstagram("https://www.instagram.com/veronica_santamaria");
            r.setTwitter("https://twitter.com/veronica_santamaria");
            redes_SocialesRepository.save(r);
        };
    }

    @Bean
    CommandLineRunner initClientesAndProveedores(){
        return args -> {
            PasswordEncoder encoder= new BCryptPasswordEncoder();
            Cliente c=new Cliente();
            c.setNombre("Andres Echeverri");
            c.setUsername("andres");
            c.setPassword(encoder.encode("andru"));
            c.setEdad("20");
            c.setDescripcion("Hola yo soy Andres!");
            c.setFoto(fotosRepository.findById("https://images.pexels.com/photos/1239291/pexels-photo-1239291.jpeg").get());
            clienteRepository.save(c);

            c=new Cliente();
            c.setNombre("Nicolas Bayona");
            c.setUsername("bayona.n");
            c.setPassword(encoder.encode("Nicol4$"));
            c.setEdad("21");
            c.setDescripcion("Roam'n around !");
            c.setFoto(fotosRepository.findById("https://avatars.githubusercontent.com/u/59931437").get());
            clienteRepository.save(c);

            Proveedor p=new Proveedor();
            p.setNombre("Juan Servicios");
            p.setUsername("juan");
            p.setPassword(encoder.encode("jujujuan"));
            p.setEdad("29");
            p.setDescripcion("Juan Servicios BB");
            p.setFoto(fotosRepository.findById("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg").get());
            p.setTelefono("+34 513-2109");
            p.setPagina_web("https://hola.com");
            p.setRedesSociales(redes_SocialesRepository.findById(2L).get());
            proveedorRepository.save(p);

            p=new Proveedor();
            p.setNombre("Veronica Te Cuida");
            p.setUsername("vero");
            p.setPassword(encoder.encode("verito<3"));
            p.setEdad("23");
            p.setDescripcion("Hola, como estas? Me llamo Veronica y yo te voy a cuidar ante cualquier emergencia");
            p.setFoto(fotosRepository.findById("https://images.pexels.com/photos/1222271/pexels-photo-1222271.jpeg").get());
            p.setTelefono("+57 201-211-3410");
            p.setPagina_web("https://example.com");
            p.setRedesSociales(redes_SocialesRepository.findById(4L).get());
            proveedorRepository.save(p);
        };
    }
    @Bean
    CommandLineRunner sayCommandLineRunner(){
        return args -> {
            System.out.println("Spring Boot ready!");
        };
    }
}