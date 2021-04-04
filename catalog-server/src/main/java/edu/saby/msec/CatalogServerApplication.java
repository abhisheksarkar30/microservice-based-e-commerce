package edu.saby.msec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Soumya Banerjee
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CatalogServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogServerApplication.class, args);
	}

}
