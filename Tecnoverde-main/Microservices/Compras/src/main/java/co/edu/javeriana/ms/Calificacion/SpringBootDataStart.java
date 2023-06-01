package co.edu.javeriana.ms.Calificacion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SpringBootDataStart {
    @Bean
    CommandLineRunner startCommandLineRunner(){
        return args -> {
            System.out.println("Spring Boot started correctly!");
        };
    }

    @Bean
    CommandLineRunner sayCommandLineRunner(){
        return args -> {
            System.out.println("Spring Boot ready!");
        };
    }
}



