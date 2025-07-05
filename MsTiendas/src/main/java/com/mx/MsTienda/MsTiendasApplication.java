package com.mx.MsTienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients    //es una libreria de java que permite alos desarrolladores crear facilmente 
                       //clientes https--para comunicar los ms  
 
public class MsTiendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTiendasApplication.class, args);
	}
}
