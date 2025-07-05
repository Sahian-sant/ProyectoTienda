package com.mx.MsInventario.excepciones;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionGlobal {

    @ExceptionHandler(RecursoNoEncontrado.class)
    public ResponseEntity<String> manejarRecursoNoEncontrado(RecursoNoEncontrado ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcionesGenerales(Exception ex) {
        return new ResponseEntity<>("Error interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
