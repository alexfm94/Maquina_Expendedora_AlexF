package com.curso.maquina.excepciones;

public class DineroClienteInsuficienteParaCompraException extends Exception {
	
private String msg;
	
	public DineroClienteInsuficienteParaCompraException(String msg) {
		super(msg);
	}
	
}
