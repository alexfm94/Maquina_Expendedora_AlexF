package com.curso.maquina.excepciones;

public class ImporteInsuficienteParaCompraException extends Exception {
	
	private String msg;
	
	public ImporteInsuficienteParaCompraException(String msg) {
		super(msg);
	}
	
}
