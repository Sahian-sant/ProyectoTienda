package com.mx.MsTienda.feingClientInventario;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.MsTienda.dto.ProductosDTO;


//nota para sahian es igual para clientes solo cambias parametros

//Para empezar a comunicar nuestro ms
//FeignClient---Recibe parametros:NOMBRE_MS,URL_SERVIDOR_LOCOL_MS,PAT_CLASE_MS
	
@FeignClient(name="MS-INVENTARIO",url="http://localhost:8031",path="/ProductoWs")
public interface IfeingClientInventario {
	
	@PostMapping
	public ProductosDTO guardar(@RequestBody ProductosDTO producto);
	
		
	}
	


