package com.mx.MsTienda;



import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import com.mx.MsTienda.entity.Tiendas;
import com.mx.MsTienda.service.TiendasSerImp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PruebasUnitarias {

    @Autowired
    TiendasSerImp tiendasService;

    @Test
    void guardarTienda() {
        Tiendas tienda = new Tiendas();
        tienda.setIdTienda(100L); // cambia por ID no usado
        tienda.setNombre("Tienda Nueva");
        tienda.setFechaApertura(Date.valueOf("2024-01-01"));

        Tiendas guardada = tiendasService.guardar(tienda);
        assertNotNull(guardada);
        assertEquals("Tienda Nueva", guardada.getNombre());
    }

    @Test
    void filtrarTiendasPorNombre() {
        List<Tiendas> resultado = tiendasService.filtrarTiendasPorNombre("tienda");
        assertTrue(resultado.stream().allMatch(t -> t.getNombre().toLowerCase().contains("tienda")));
    }
}
