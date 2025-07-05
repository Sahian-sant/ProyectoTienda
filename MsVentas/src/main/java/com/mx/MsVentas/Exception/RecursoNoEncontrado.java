package com.mx.MsVentas.Exception;


public class RecursoNoEncontrado extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecursoNoEncontrado(String mensaje) {
        super(mensaje);
    }
}