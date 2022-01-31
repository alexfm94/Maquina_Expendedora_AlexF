package com.curso.maquina.excepciones;

public class NoQuedaRefrescoException extends Exception {
	
private String msg;
	
	public NoQuedaRefrescoException(String msg) {
		super(msg);
	}
	
}
