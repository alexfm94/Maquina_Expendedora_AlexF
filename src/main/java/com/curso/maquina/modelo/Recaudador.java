package com.curso.maquina.modelo;

import java.util.ArrayList;

public class Recaudador {
	
	private String nombre;
	private ArrayList<Informe> informes;
	
	public Recaudador(String nombre) {
		this.nombre = nombre;
		this.informes = new ArrayList<Informe>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	//Para la prueba de JUnit
	public ArrayList<Informe> getInformes() {
		return informes;
	}
	
	public void pedirInformes(MaquinaExpendedora maquina){
		this.informes = maquina.getInformes();
	}
	
	public void enseniarInforme() {
		for(Informe inf : informes) {
			System.out.println(inf.toString());
		}
	}
	
}
