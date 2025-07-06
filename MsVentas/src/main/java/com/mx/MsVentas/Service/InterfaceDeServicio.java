package com.mx.MsVentas.Service;
import java.util.List;
import com.mx.MsVentas.Model.Clientes;

public interface InterfaceDeServicio {
	
	List<Clientes> mostrar();
	Clientes guardar(Clientes cliente);
	Clientes buscarPorId(Integer idProd);
	List<Clientes> buscarPorTienda(Integer idTienda);
	Clientes actualizar(Integer idProd, Clientes cliente);
	void eliminar(Integer idProd);
	
	}