package com.curso.maquina.modelo;

public class Informe {
	
	//Cliente c, Refresco refresco, double importeDado, double cambios
	private String nombreCliente;
	private TiposRefresco tipoRefresco;
	private int stockAntes;
	private int stockDespues;
	private double precioProducto;
	private double importeDado;
	private double cambios;
	private double dineroMaquinaTrasCompra;

	public Informe(String nombreCliente, TiposRefresco tipoRefresco, int stockAntes, int stockDespues,
			double precioProducto, double importeDado, double cambios, double dineroMaquinaTrasCompra) {
		this.nombreCliente = nombreCliente;
		this.tipoRefresco = tipoRefresco;
		this.stockAntes = stockAntes;
		this.stockDespues = stockDespues;
		this.precioProducto = precioProducto;
		this.importeDado = importeDado;
		this.cambios = cambios;
		this.dineroMaquinaTrasCompra = dineroMaquinaTrasCompra;
	}

	@Override
	public String toString() {
		return "Informe [nombreCliente=" + nombreCliente + ", tipoRefresco=" + tipoRefresco + ", stockAntes="
				+ stockAntes + ", stockDespues=" + stockDespues + ", precioProducto=" + precioProducto
				+ ", importeDado=" + importeDado + ", cambios=" + cambios + ", dineroMaquinaTrasCompra="
				+ dineroMaquinaTrasCompra + "]";
	}
	
}
