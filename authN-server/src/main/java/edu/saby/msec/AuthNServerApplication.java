package edu.saby.msec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 * @author Abhishek Sarkar
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthNServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthNServerApplication.class, args);
	}

}
