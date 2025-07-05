package com.mx.EurekaServert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  // HABILITAMOS EL SERVICIO DE EUREKA 
public class EurekaServertApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServertApplication.class, args);
	}

}
