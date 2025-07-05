package com.mx.MsTienda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mx.MsTienda.entity.Tiendas;

public interface TiendasRepository extends JpaRepository<Tiendas, Long> {
}