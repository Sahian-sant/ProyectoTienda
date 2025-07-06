package com.mx.MsTienda.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.mx.MsTienda.Exepciones.RecursoNoEncontrado;
import com.mx.MsTienda.Repository.TiendasRepository;
import com.mx.MsTienda.dto.ProductosDTO;
import com.mx.MsTienda.entity.Tiendas;
import com.mx.MsTienda.feingClientInventario.IfeingClientInventario;

//Metodos propios del ms-inventario--------------------
//Para realizar la comunicacion de los microservicios de puede realizar de 
//dos formas
/*1.-RestTemplate
 * 2.-OpenFeigClient---cliente fingido*/
@Service
public class TiendasSerImp implements InterfazdeServicio {

    @Autowired
    private TiendasRepository tiendasRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IfeingClientInventario ifeingClientInventario;

    @Override
    @Transactional
    public List<Tiendas> mostrar() {
        return tiendasRepository.findAll();
    }

    @Override
    @Transactional
    public Tiendas guardar(Tiendas tienda) {
        return tiendasRepository.save(tienda);
    }

    @Override
    public List<ProductosDTO> lbuscarProductosXtiendaid(Long idTienda) {
        return restTemplate.getForObject("http://localhost:8031/ProductosWs/productos/" + idTienda, List.class);
    }

    @Override
    @Transactional
    public ProductosDTO guardarProductos(ProductosDTO producto) {
        return ifeingClientInventario.guardar(producto);
    }

    @Override
    public List<Tiendas> buscarTiendasSP(String nombre) {
        return tiendasRepository.buscarPorNombre(nombre);
    }

    @Override
    public List<Tiendas> filtrarTiendasPorNombre(String texto) {
        return tiendasRepository.findAll().stream()
                .filter(t -> t.getNombre().toLowerCase().contains(texto.toLowerCase()))
                .toList();
    }

    @Override
    @Transactional
    public Tiendas actualizar(Long idTienda, Tiendas tienda) {
        Tiendas existente = tiendasRepository.findById(idTienda)
                .orElseThrow(() -> new RecursoNoEncontrado("Tienda no encontrada con id: " + idTienda));

        existente.setNombre(tienda.getNombre());
        existente.setFechaApertura(tienda.getFechaApertura());

        return tiendasRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminar(Long idTienda) {
        Tiendas existente = tiendasRepository.findById(idTienda)
                .orElseThrow(() -> new RecursoNoEncontrado("Tienda no encontrada con id: " + idTienda));

        tiendasRepository.delete(existente);
    }
}
