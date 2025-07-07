package com.mx.MsInventario.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.MsInventario.Model.Productos;

public interface ProductoDao extends JpaRepository<Productos, Long> {  


    List<Productos> findByIdTienda(Long idTienda);

    // Método para llamar stored procedure
    @Query(value = "CALL sp_getProductosPorTienda(:idTienda)", nativeQuery = true)
    List<Productos> obtenerProductosPorTienda(@Param("idTienda") Long idTienda);
}
