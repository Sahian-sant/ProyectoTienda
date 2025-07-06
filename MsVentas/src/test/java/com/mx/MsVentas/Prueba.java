package com.mx.MsVentas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.MsVentas.Exception.RecursoNoEncontrado;
import com.mx.MsVentas.Model.Clientes;
import com.mx.MsVentas.Service.ClientesServImp;

@SpringBootTest
public class Prueba {

 @Autowired
 private ClientesServImp clientesServImp;

	   
 @Test
 void testGuardarCliente() {
	 Clientes c = new Clientes();
	        
	 c.setIdProd(999);
	 c.setNombre("pedrito");
	 c.setApp("Gomez");
	 c.setApm("Pantaleon");
	 c.setNumCel(123456789);
	 c.setIdTienda(1);
	 
	 
	 Clientes guardado = clientesServImp.guardar(c);
	 assertNotNull(guardado);
	 assertEquals("TestNombre", guardado.getNombre()); 
 }
	    
 @Test
 void testBuscarClienteNoExistente() {
	 Exception exception = assertThrows(RecursoNoEncontrado.class, () -> {
		 clientesServImp.buscarPorId(0);
	    
	 });
	 assertTrue(exception.getMessage().contains("no encontrado"));
	    }
	}
