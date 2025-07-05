package com.mx.MsTienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.MsTienda.dto.ProductosDTO;
import com.mx.MsTienda.entity.Tiendas;
import com.mx.MsTienda.service.TiendasSerImp;

@RestController
@RequestMapping(path="TiendasWs")
@CrossOrigin
public class TiendaWs {
	
	@Autowired
	TiendasSerImp tiendasSerImp;
	
	//
	@GetMapping
	public List<Tiendas>mostrar(){
		return tiendasSerImp.mostrar();
	}
	@PostMapping
	public ResponseEntity<Tiendas>guardar(@RequestBody Tiendas tienda){
		return new ResponseEntity<Tiendas>(tiendasSerImp.guardar(tienda),HttpStatus.CREATED);
	}
//peticiones propios del ms-inventario
	
	@GetMapping(path="/productos/{idTienda}")
	public List<ProductosDTO>buscarProductosXidTienda(@PathVariable("idTienda")Long idTienda){
		return tiendasSerImp.lbuscarProductosXtiendaid(idTienda);
	}
	
	//http://localhost:8030/TiendaWs/guardarProducto
	@PostMapping(path="/guardarProducto")
	public ResponseEntity<ProductosDTO>guardarProducto(@RequestBody ProductosDTO producto){
		return new ResponseEntity<ProductosDTO>(tiendasSerImp.guardarProductos(producto),HttpStatus.CREATED);
	}
	
}
