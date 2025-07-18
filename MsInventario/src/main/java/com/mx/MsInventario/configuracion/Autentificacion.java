package com.mx.MsInventario.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Autentificacion {

    @Autowired
    private AutErrores  authEntryPoint;  // Inyección del manejador de errores 

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) // Todas las peticiones requieren autenticación
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF (para pruebas REST)
            .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(authEntryPoint)); // Usar autenticación básica y manejador de error personalizado
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("admin")
            .password("{noop}admin123")  // Contraseña sin codificar (solo para pruebas)
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

