package co.edu.javeriana.ms.LoadBalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Auth {

	public static void main(String[] args) {
		SpringApplication.run(Auth.class, args);
	}

}
