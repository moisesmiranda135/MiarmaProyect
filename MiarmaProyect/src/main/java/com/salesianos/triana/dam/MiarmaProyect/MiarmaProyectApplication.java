package com.salesianos.triana.dam.MiarmaProyect;

import com.salesianos.triana.dam.MiarmaProyect.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class MiarmaProyectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiarmaProyectApplication.class, args);
	}

}
