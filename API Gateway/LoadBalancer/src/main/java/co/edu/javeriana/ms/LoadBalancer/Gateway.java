package co.edu.javeriana.ms.LoadBalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Gateway {

	public static void main(String[] args) {
		SpringApplication.run(Gateway.class, args);
	}
}
