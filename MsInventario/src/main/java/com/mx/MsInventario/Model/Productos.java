package com.mx.MsInventario.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRODUCTOS_BOD")
@Data
public class Productos {
	@Id
	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private Long idProducto;
	private String nombre;
	private Float precio;
	private Integer stock;
	private Long idTienda;

}