package com.mx.MsInventario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.mx.MsInventario.Model.Productos;
import com.mx.MsInventario.Service.ProductosSerImp;

@RestController
@RequestMapping(path = "ProductosWs")
@CrossOrigin
public class ProductosWeb {

    @Autowired
    ProductosSerImp productosSerImp;

    @PostMapping
    public ResponseEntity<Productos> guardar(@RequestBody Productos producto) {
        return new ResponseEntity<>(productosSerImp.guardar(producto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Productos> mostrar() {
        return productosSerImp.mostrar();
    }

    @GetMapping(path = "/{idProducto}")
    public Productos buscarXid(@PathVariable("idProducto") Long idProducto) {
        return productosSerImp.buscarXid(idProducto);
    }

    @GetMapping(path = "/tienda/{idTienda}")
    public List<Productos> buscarProductoXidTienda(@PathVariable("idTienda") Long idTienda) {
        return productosSerImp.buscarProductosXtiendaid(idTienda);
    }
    
    @PutMapping("/{idProducto}")
    public ResponseEntity<Productos> actualizar(@PathVariable Long idProducto, @RequestBody Productos producto) {
        Productos actualizado = productosSerImp.actualizar(idProducto, producto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idProducto) {
        productosSerImp.eliminar(idProducto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/externo")
    public String obtenerApiExterna() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        return restTemplate.getForObject(url, String.class);
    }
}

