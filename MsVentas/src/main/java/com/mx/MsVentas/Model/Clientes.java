package com.mx.MsVentas.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="CLIENTES_BOD")

public class Clientes{

	
@Id
@Column(name="ID_PRODUCTO")
private Integer idProd;
@Column(name="ID_TIENDA")
private Integer idTienda;
@Column(name="NOMBRE")
private String nombre;
@Column(name="APP")
private String app;
@Column(name="APM")
private String apm;
@Column(name="NUM_CEL")
private Integer numCel;

}
