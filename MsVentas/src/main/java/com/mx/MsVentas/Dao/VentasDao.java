package com.mx.MsVentas.Dao;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mx.MsVentas.Model.Clientes;


public interface VentasDao extends JpaRepository<Clientes, Integer>{
	  List<Clientes> findByIdTienda(Integer idTienda);

	    @Query(value = "{call GET_CLIENTES}", nativeQuery = true)
	    List<Clientes> obtenerClientes();
}
