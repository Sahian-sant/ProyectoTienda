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

    //http://localhost:8031/ProductosWs
    @PostMapping
    public ResponseEntity<Productos> guardar(@RequestBody Productos producto) {
        return new ResponseEntity<>(productosSerImp.guardar(producto), HttpStatus.CREATED);
    }
    // http://localhost:8031/ProductosWs
    @GetMapping
    public List<Productos> mostrar() {
        return productosSerImp.mostrar();
    }

    //http://localhost:8031/ProductosWs/1
    @GetMapping(path = "/{idProducto}")
    public Productos buscarXid(@PathVariable("idProducto") Long idProducto) {
        return productosSerImp.buscarXid(idProducto);
    }
//http://localhost:8031/ProductosWs/tienda/1
    @GetMapping(path = "/tienda/{idTienda}")
    public List<Productos> buscarProductoXidTienda(@PathVariable("idTienda") Long idTienda) {
        return productosSerImp.buscarProductosXtiendaid(idTienda);
    }
    //http://localhost:8031/ProductosWs/1
    @PutMapping("/{idProducto}")
    public ResponseEntity<Productos> actualizar(@PathVariable Long idProducto, @RequestBody Productos producto) {
        Productos actualizado = productosSerImp.actualizar(idProducto, producto);
        return ResponseEntity.ok(actualizado);
    }

    
  //  http://localhost:8031/ProductosWs/1
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
    // http://localhost:8031/ProductosWs/filtrar/precio/150
    @GetMapping("/filtrar/precio/{precioMax}")
    public List<Productos> filtrarPorPrecio(@PathVariable("precioMax") float precioMax) {
        return productosSerImp.filtrarPorPrecioMax(precioMax);
    }

}

