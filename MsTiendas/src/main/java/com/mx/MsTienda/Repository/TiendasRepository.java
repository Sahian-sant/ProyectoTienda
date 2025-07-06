package com.mx.MsTienda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import com.mx.MsTienda.entity.Tiendas;
import java.util.List;

public interface TiendasRepository extends JpaRepository<Tiendas, Long> {
    
    @Procedure(name = "Tiendas.buscarPorNombre")
    List<Tiendas> buscarPorNombre(String P_NOMBRE);
}