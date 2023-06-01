package co.edu.javeriana.ms.Calificacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.javeriana.ms.Calificacion.entities.Calificacion;
import co.edu.javeriana.ms.Calificacion.repositories.CalificacionRepository;

@Configuration
class SpringBootDataStart {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Bean
    CommandLineRunner startCommandLineRunner(){
        return args -> {
            System.out.println("Spring Boot started correctly!");
        };
    }

    @Bean
    CommandLineRunner initCalificaciones(){
        return args -> {
            Calificacion c=new Calificacion();
            c.setCliente_ID("1");
            c.setServicio_ID("1");
            c.setPuntaje("4.3");
            c.setComentario(null);
            calificacionRepository.save(c);

            c=new Calificacion();
            c.setCliente_ID("2");
            c.setServicio_ID("2");
            c.setPuntaje("5.0");
            c.setComentario("Hola, me gusto mucho");
            calificacionRepository.save(c);
        };
    }

    @Bean
    CommandLineRunner sayCommandLineRunner(){
        return args -> {
            System.out.println("Spring Boot ready!");
        };
    }
}



