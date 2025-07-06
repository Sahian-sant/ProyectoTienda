package com.mx.MsTienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.MsTienda.dto.ProductosDTO;
import com.mx.MsTienda.entity.Tiendas;
import com.mx.MsTienda.service.InterfazdeServicio;

@RestController
@RequestMapping(path="TiendasWs")
@CrossOrigin
public class TiendaWs {
    
    @Autowired
    private InterfazdeServicio tiendasService;
    
    @GetMapping
    public List<Tiendas> mostrar(){
        return tiendasService.mostrar();
    }
    
    @PostMapping
    public ResponseEntity<Tiendas> guardar(@RequestBody Tiendas tienda){
        return new ResponseEntity<>(tiendasService.guardar(tienda), HttpStatus.CREATED);
    }

    @GetMapping(path="/productos/{idTienda}")
    public List<ProductosDTO> buscarProductosXidTienda(@PathVariable("idTienda") Long idTienda){
        return tiendasService.lbuscarProductosXtiendaid(idTienda);
    }

    @PostMapping(path="/guardarProducto")
    public ResponseEntity<ProductosDTO> guardarProducto(@RequestBody ProductosDTO producto){
        return new ResponseEntity<>(tiendasService.guardarProductos(producto), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Tiendas> actualizar(@PathVariable Long id, @RequestBody Tiendas tiendaActualizada) {
        Tiendas tienda = tiendasService.actualizar(id, tiendaActualizada);
        return new ResponseEntity<>(tienda, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tiendasService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filtrarPorNombre/{texto}")
    public List<Tiendas> filtrarPorNombre(@PathVariable String texto) {
        return tiendasService.filtrarTiendasPorNombre(texto);
    }
    
    @GetMapping("/buscarSP/{nombre}")
    public List<Tiendas> buscarPorSP(@PathVariable String nombre) {
        return tiendasService.buscarTiendasSP(nombre);
    }
}

