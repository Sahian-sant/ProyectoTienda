package com.mx.MsInventario.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.MsInventario.Dao.ProductoDao;
import com.mx.MsInventario.Model.Productos;
import com.mx.MsInventario.excepciones.RecursoNoEncontrado;

@Service
public class ProductosSerImp implements Interfazservice  {  

    @Autowired
    ProductoDao productoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Productos> mostrar() {
        return (List<Productos>) productoDao.findAll();
    }
    
    @Override
    @Transactional
    public Productos guardar(Productos producto) {
        return productoDao.save(producto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Productos buscarXid(Long idProducto) {
        return productoDao.findById(idProducto)
                .orElseThrow(() -> new RecursoNoEncontrado("Producto no encontrado"));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Productos> buscarProductosXtiendaid(Long idTienda) {
        return productoDao.findByIdTienda(idTienda);
    }
    
    @Override
    @Transactional
    public Productos actualizar(Long idProducto, Productos productoActualizado) {
        Productos producto = productoDao.findById(idProducto)
                .orElseThrow(() -> new RecursoNoEncontrado("Producto no encontrado"));
        producto.setNombre(productoActualizado.getNombre());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());
        producto.setIdTienda(productoActualizado.getIdTienda());
        return productoDao.save(producto);
    }
    
    @Override
    @Transactional
    public void eliminar(Long idProducto) {
        Productos producto = productoDao.findById(idProducto)
                .orElseThrow(() -> new RecursoNoEncontrado("Producto no encontrado"));
        productoDao.delete(producto);
    }
    
    // método que usa lambda para filtrar productos por precio máximo
    public List<Productos> filtrarPorPrecioMax(float precioMax) {
        return mostrar().stream()
            .filter(p -> p.getPrecio() != null && p.getPrecio() <= precioMax)
            .collect(Collectors.toList());
    }
}

