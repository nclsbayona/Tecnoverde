package co.edu.javeriana.ms.MicroserviciosApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroserviciosAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosAppApplication.class, args);
	}

}