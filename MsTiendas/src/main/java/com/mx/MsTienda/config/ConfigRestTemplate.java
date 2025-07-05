package com.mx.MsTienda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration //La  configuracion del RestTemmplate

public class ConfigRestTemplate {

	
	/*Crear un objeto de una clase RestsTemplate
	 * @Bean -- no es mas que una clase ,en este caso para realizar una
	 * instancia a la clase RestTemplate*/
	
	/*RestTemplate ---es una clase de spring que nos indica o ayuda a trabajar
	 * con los servicios rest---utilizando el protocolo http--la comunicacion de los ms*/
	
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
}
