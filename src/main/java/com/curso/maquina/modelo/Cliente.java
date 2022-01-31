package com.curso.maquina.modelo;

import com.curso.maquina.excepciones.DineroClienteInsuficienteParaCompraException;
import com.curso.maquina.excepciones.ImporteInsuficienteParaCompraException;
import com.curso.maquina.excepciones.NoQuedaRefrescoException;

public class Cliente {
	
	private String nombre;
	private double dinero;
	
	public Cliente(String nombre, double dinero) {
		this.nombre = nombre;
		this.dinero=dinero;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getDinero() {
		return dinero;
	}
	
	public void comprarRefrescoMaquina(MaquinaExpendedora maquina,Refresco refresco, double dineroMeter) throws ImporteInsuficienteParaCompraException, DineroClienteInsuficienteParaCompraException, NoQuedaRefrescoException {
		this.meterDineroMaquina(dineroMeter);
		maquina.comprarRefresco(this, refresco, dineroMeter);
	}
	
	public double pedirPrecioProducto(Refresco refresco, MaquinaExpendedora maquina) {
		return maquina.obtenerPrecioRefresco(refresco);
	}
	
	public void recibirCambios(double cambios) {
		this.dinero += cambios;
	}

	public void meterDineroMaquina(double importeDado) throws DineroClienteInsuficienteParaCompraException {
		if(this.dinero < importeDado) {
			throw new DineroClienteInsuficienteParaCompraException("El cliente" + this.nombre + "no tiene dinero suficiente para la compra.");
		}
		this.dinero -= importeDado;
	}
	
}
