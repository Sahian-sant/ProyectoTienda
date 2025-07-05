package com.mx.MsInventario.Service;

import java.util.List;
import com.mx.MsInventario.Model.Productos;

public interface Interfazservice {

    List<Productos> mostrar();
    Productos guardar(Productos producto);
    Productos buscarXid(Long idProducto);
    List<Productos> buscarProductosXtiendaid(Long idTienda);
    Productos actualizar(Long idProducto, Productos productoActualizado);
    void eliminar(Long idProducto);
}
