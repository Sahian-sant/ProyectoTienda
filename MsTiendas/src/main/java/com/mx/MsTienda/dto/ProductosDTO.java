package com.mx.MsTienda.dto;

import lombok.Getter;
import lombok.Setter;

//DTO--es un patron de diseño se utiliza para transportar datos del objeto
//entrediferentes capas de una apilcacion



@Getter
@Setter
public class ProductosDTO {
	
	private String nombre;
	private Float precio;
	private Integer stock;
	private Long idTienda;

}
