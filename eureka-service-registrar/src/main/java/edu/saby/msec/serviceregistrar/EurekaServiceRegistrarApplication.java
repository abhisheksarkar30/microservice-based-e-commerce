package edu.saby.msec.serviceregistrar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceRegistrarApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceRegistrarApplication.class, args);
	}

}
