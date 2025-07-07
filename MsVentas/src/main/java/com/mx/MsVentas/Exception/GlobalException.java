package com.mx.MsVentas.Exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(RecursoNoEncontrado.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(RecursoNoEncontrado ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", "Recurso no encontrado");
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("fechaHora", LocalDateTime.now());
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> manejarErrorBaseDatos(DataAccessException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", "Error en base de datos");
        respuesta.put("mensaje", "Error interno al procesar la solicitud");
        respuesta.put("fechaHora", LocalDateTime.now());
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcionGeneral(Exception ex) {
        String mensaje = "Ha ocurrido un error inesperado. Por favor contacte al administrador.";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN); 

        return new ResponseEntity<>(mensaje, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
