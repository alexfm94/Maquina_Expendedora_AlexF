package com.curso.maquina.modelo;

import java.util.ArrayList;

import com.curso.maquina.excepciones.DineroClienteInsuficienteParaCompraException;
import com.curso.maquina.excepciones.ImporteInsuficienteParaCompraException;
import com.curso.maquina.excepciones.NoQuedaRefrescoException;

public class MaquinaExpendedora {
	
	private ArrayList<Refresco> refrescos;
	private ArrayList<Informe> informes;
	private double dinero;

	//Cuando recibes la maquina siempre tienen estos 10 tipos de bebida y 30 euros para el cambio
	public MaquinaExpendedora() {
		this.informes = new ArrayList<Informe>();
		this.dinero = 30;
		this.refrescos = new ArrayList<Refresco>();
		refrescos.add(new Refresco(TiposRefresco.COCA_COLA_ZERO, 10));
		refrescos.add(new Refresco(TiposRefresco.COCA_COLA, 10));
		//Se ha dejado el AQUARIUS a 0 para la prueba con JUnit
		refrescos.add(new Refresco(TiposRefresco.AQUARIUS, 0));
		//Se ha dejado el BURN a 0 para la prueba con JUnit
		refrescos.add(new Refresco(TiposRefresco.BURN, 0));
		refrescos.add(new Refresco(TiposRefresco.FANTA_NARANJA, 10));
		refrescos.add(new Refresco(TiposRefresco.MONSTER, 10));
		refrescos.add(new Refresco(TiposRefresco.KAS_LIMON, 10));
		refrescos.add(new Refresco(TiposRefresco.PEPSI, 10));
		refrescos.add(new Refresco(TiposRefresco.BURN, 10));
		refrescos.add(new Refresco(TiposRefresco.REDBULL, 10));
	}
	
	public double getDinero() {
		return dinero;
	}
	
	public ArrayList<Informe> getInformes() {
		return informes;
	}
	
	public int obtenerCantidadRefrescosDiferentes() {
		return this.refrescos.size();
	}
	
	public Refresco obtenerRefresco(TiposRefresco tipoRefresco) {
		Refresco refresco = null;
		for(int i = 0; i < refrescos.size(); i++) {
			if(refrescos.get(i).getTipo() == tipoRefresco) {
				refresco = refrescos.get(i);
				break;
			}
		}
		return refresco;
	}
	
	public double obtenerPrecioRefresco(Refresco refresco){
		return refresco.getTipo().getCoste();
	}
	
	public int obtenerStockRefresco(Refresco refresco) {
		return refresco.getStock();
	}

	public void comprarRefresco(Cliente c, Refresco refresco, double importeDado) throws ImporteInsuficienteParaCompraException, DineroClienteInsuficienteParaCompraException, NoQuedaRefrescoException {
		if(refresco.getStock() == 0) {
			throw new NoQuedaRefrescoException("No quedan mas " + refresco.getTipo().getNombre());
		}
		double coste = refresco.getTipo().getCoste();
		if((importeDado - coste) < 0) {
			double dineroFaltante = coste - importeDado;
			throw new ImporteInsuficienteParaCompraException("No puede comprarlo. Le faltan " + dineroFaltante + "centimos.");
		}
		double cambios = importeDado - coste;
		refresco.comprarPorducto();
		this.dinero +=coste;
		c.recibirCambios(cambios);
		añadirInforme(c, refresco, importeDado, cambios);
	}

	private void añadirInforme(Cliente c, Refresco refresco, double importeDado, double cambios) {
		String nombreCliente = c.getNombre();
		TiposRefresco tipoRefresco = refresco.getTipo();
		int stockAntes = refresco.getStock()+1;
		int stockDespues = refresco.getStock();
		double precioProducto = refresco.getTipo().getCoste();
		double dineroMaquinaTrasCompra = this.getDinero();
		this.informes.add(new Informe(nombreCliente, tipoRefresco, stockAntes, stockDespues,precioProducto,importeDado,cambios, dineroMaquinaTrasCompra));
	}
	
}
