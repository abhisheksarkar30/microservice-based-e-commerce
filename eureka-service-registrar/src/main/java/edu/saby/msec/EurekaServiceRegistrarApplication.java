package edu.saby.msec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * @author Abhishek Sarkar
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceRegistrarApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceRegistrarApplication.class, args);
	}

}
