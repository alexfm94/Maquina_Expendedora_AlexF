package com.curso.maquina.modelo;

public class Refresco {
	
	TiposRefresco tipo;
	int stock;
	
	//CONSTRUCTOR
	public Refresco(TiposRefresco tipo, int stock) {
		this.tipo = tipo;
		this.stock = stock;
	}
	
	//GETTERS/SETTERS
	public TiposRefresco getTipo() {
		return tipo;
	}
	
	public int getStock() {
		return stock;
	}

	public void comprarPorducto() {
		this.stock--;
	}
	
	public void reponerStock(int cantidadReponer) {
		this.stock += cantidadReponer;
	}
	
}
