package com.mx.MsVentas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.MsVentas.Model.Clientes;
import com.mx.MsVentas.Service.ClientesServImp;

@RestController
@RequestMapping(path = "ClientesWs")
@CrossOrigin
public class ClientesWS {

    @Autowired
    private ClientesServImp clientesServImp;

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Clientes cliente) {
        if (cliente.getNombre() == null || cliente.getIdTienda() == null) {
            return ResponseEntity.badRequest().body("Nombre e ID de tienda son obligatorios");
        }
        Clientes nuevoCliente = clientesServImp.guardar(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Clientes>> mostrar() {
        return ResponseEntity.ok(clientesServImp.mostrar());
    }

    // Buscar cliente por id
    @GetMapping(path = "/{idProd}")
    public ResponseEntity<Clientes> buscarXid(@PathVariable("idProd") Integer idProd) {
        Clientes cliente = clientesServImp.buscarPorId(idProd);
        return ResponseEntity.ok(cliente);
    }

    // Buscar clientes por idTienda
    @GetMapping(path = "/tienda/{idTienda}")
    public ResponseEntity<List<Clientes>> buscarClientesXidTienda(@PathVariable("idTienda") Integer idTienda) {
        return ResponseEntity.ok(clientesServImp.buscarPorTienda(idTienda));
    }

    // Actualizar cliente
    @PutMapping("/{idProd}")
    public ResponseEntity<Clientes> actualizar(@PathVariable Integer idProd, @RequestBody Clientes cliente) {
        Clientes actualizado = clientesServImp.actualizar(idProd, cliente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar cliente
    @DeleteMapping("/{idProd}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idProd) {
        clientesServImp.eliminar(idProd);
        return ResponseEntity.noContent().build();
    }

    // Obtener clientes desde stored procedure
    @GetMapping("/storedprocedure")
    public ResponseEntity<List<Clientes>> obtenerClientesSP() {
        return ResponseEntity.ok(clientesServImp.obtenerClientesSP());
    }

    // Obtener producto JSON vía REST template
    @GetMapping("/producto/{id}")
    public ResponseEntity<String> producto(@PathVariable Long id) {
        String productoJson = clientesServImp.obtenerProductoJson(id);
        return ResponseEntity.ok(productoJson);
    }
}
