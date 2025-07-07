package com.mx.MsVentas.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.mx.MsVentas.Dao.VentasDao;
import com.mx.MsVentas.Exception.RecursoNoEncontrado;
import com.mx.MsVentas.Model.Clientes;

@Service
public class ClientesServImp implements InterfaceDeServicio {

    private static final Logger log = LoggerFactory.getLogger(ClientesServImp.class);

    @Autowired
    private VentasDao ventasDao;

    @Transactional(readOnly = true)
    public List<Clientes> mostrar() {
        return ventasDao.findAll();
    }

    @Transactional
    public Clientes guardar(Clientes cliente) {
        if (cliente.getNombre() == null || cliente.getIdTienda() == null) {
            throw new IllegalArgumentException("Nombre e ID de tienda no pueden ser nulos");
        }
        try {
            return ventasDao.save(cliente);
        } catch (DataAccessException e) {
            log.error("Error al guardar cliente en BD", e);
            throw new RuntimeException("Error interno al guardar cliente");
        }
    }

    @Transactional(readOnly = true)
    public Clientes buscarPorId(Integer idProd) {
        return ventasDao.findById(idProd)
                .orElseThrow(() -> new RecursoNoEncontrado("Cliente con id " + idProd + " no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Clientes> buscarPorTienda(Integer idTienda) {
        return ventasDao.findByIdTienda(idTienda);
    }

    @Transactional
    public Clientes actualizar(Integer idProd, Clientes clienteActualizado) {
        if (clienteActualizado.getNombre() == null || clienteActualizado.getIdTienda() == null) {
            throw new IllegalArgumentException("Nombre e ID de tienda no pueden ser nulos");
        }
        try {
            Clientes cliente = ventasDao.findById(idProd)
                    .orElseThrow(() -> new RecursoNoEncontrado("Cliente con id " + idProd + " no encontrado"));

            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setApp(clienteActualizado.getApp());
            cliente.setApm(clienteActualizado.getApm());
            cliente.setNumCel(clienteActualizado.getNumCel());
            cliente.setIdTienda(clienteActualizado.getIdTienda());

            return ventasDao.save(cliente);
        } catch (DataAccessException e) {
            log.error("Error al actualizar cliente en BD", e);
            throw new RuntimeException("Error interno al actualizar cliente");
        }
    }

    @Transactional
    public void eliminar(Integer idProd) {
        try {
            if (!ventasDao.existsById(idProd)) {
                throw new RecursoNoEncontrado("Cliente con id " + idProd + " no encontrado");
            }
            ventasDao.deleteById(idProd);
        } catch (DataAccessException e) {
            log.error("Error al eliminar cliente en BD", e);
            throw new RuntimeException("Error interno al eliminar cliente");
        }
    }

    @Transactional(readOnly = true)
    public List<Clientes> obtenerClientesSP() {
        return ventasDao.obtenerClientes();
    }

    @Transactional(readOnly = true)
    public List<String> obtenerNombresClientes() {
        return ventasDao.findAll()
                .stream()
                .map(Clientes::getNombre)
                .toList();
    }

    // RESTTemplate
    public String obtenerProductoJson(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://ms-inventario:8031/ProductosWs/" + id;
        return restTemplate.getForObject(url, String.class);
    }
}
