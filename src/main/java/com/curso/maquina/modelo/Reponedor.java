package com.curso.maquina.modelo;

public class Reponedor {
	
	private String nombre;
	
	public Reponedor(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void reponerRefresco(Refresco refresco, int cantidad) {
		refresco.reponerStock(10);
	}
	
}
