package com.mx.MsVentas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.mx.MsVentas.Dao.VentasDao;
import com.mx.MsVentas.Exception.RecursoNoEncontrado;
import com.mx.MsVentas.Model.Clientes;

@Service
public class ClientesServImp implements InterfaceDeServicio {

    @Autowired
    private VentasDao ventasDao;

    @Transactional(readOnly = true)
    public List<Clientes> mostrar() {
        return ventasDao.findAll();
    }

    @Transactional
    public Clientes guardar(Clientes cliente) {
        return ventasDao.save(cliente);
    }


    @Transactional(readOnly = true)
    public Clientes buscarPorId(Integer idProd) {
        return ventasDao.findById(idProd)
                .orElseThrow(() -> new RecursoNoEncontrado("Cliente con id "
        + idProd + " no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Clientes> buscarPorTienda(Integer idTienda) {
        return ventasDao.findByIdTienda(idTienda);
    }
    
    @Transactional
    public Clientes actualizar(Integer idProd, Clientes clienteActualizado) {
        Clientes cliente = ventasDao.findById(idProd)
            .orElseThrow(() -> new RecursoNoEncontrado("Cliente con id " + idProd + " no encontrado"));

        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setApp(clienteActualizado.getApp());
        cliente.setApm(clienteActualizado.getApm());
        cliente.setNumCel(clienteActualizado.getNumCel());
        cliente.setIdTienda(clienteActualizado.getIdTienda());
        return ventasDao.save(cliente);
    }

    @Transactional
    public void eliminar(Integer idProd) {
        if (!ventasDao.existsById(idProd)) {
            throw new RecursoNoEncontrado("Cliente con id " + idProd + " no encontrado");
        }
        ventasDao.deleteById(idProd);
    }
    
    
    public List<Clientes> obtenerClientesSP() {
        return ventasDao.obtenerClientes();
    }

    public List<String> obtenerNombresClientes() {
        return ventasDao.findAll()
                .stream()
                .map(c -> c.getNombre()) 
                .toList();
    }
    //resttemplace
    public String obtenerProductoJson(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8031/ProductosWs/" + id;
        String productoJson = restTemplate.getForObject(url, String.class);
        return productoJson;  // aquí devuelves el JSON tal cual
    }
}
   

    

