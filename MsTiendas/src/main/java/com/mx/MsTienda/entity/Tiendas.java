package com.mx.MsTienda.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

import lombok.Data;

//Indica que vamos a usar un procedimiento almacenado desde esta clase
@NamedStoredProcedureQuery(
 name = "Tiendas.buscarPorNombre", // Nombre que usamos en el repositorio
 procedureName = "SP_BUSCAR_TIENDA_NOMBRE", // Nombre del procedimiento en Oracle(sql)
 parameters = {
     // Parámetro que enviamos al procedimiento (el nombre a buscar)
     @StoredProcedureParameter(
         mode = ParameterMode.IN, // Es de entrada
         name = "P_NOMBRE",       // Así se llama en Oracle
         type = String.class      // Tipo 
     ),
     // Parámetro que recibe el resultado 
     @StoredProcedureParameter(
         mode = ParameterMode.REF_CURSOR, // Devuelve varios datos
         name = "C_RESULTADO",           
         type = void.class                
     )
 },
 resultClasses = Tiendas.class // El resultado 
)
 
@Entity
@Table(name = "TIENDAS_BOD") 
@Data
public class Tiendas {

    @Id
    @Column(name = "ID_TIENDA")
    private Long idTienda;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "FECHA_APERTURA")
    private Date fechaApertura;

}
