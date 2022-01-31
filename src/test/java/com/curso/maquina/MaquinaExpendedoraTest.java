package com.curso.maquina;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.curso.maquina.excepciones.DineroClienteInsuficienteParaCompraException;
import com.curso.maquina.excepciones.ImporteInsuficienteParaCompraException;
import com.curso.maquina.excepciones.NoQuedaRefrescoException;
import com.curso.maquina.modelo.Cliente;
import com.curso.maquina.modelo.Informe;
import com.curso.maquina.modelo.MaquinaExpendedora;
import com.curso.maquina.modelo.Recaudador;
import com.curso.maquina.modelo.Refresco;
import com.curso.maquina.modelo.Reponedor;
import com.curso.maquina.modelo.TiposRefresco;


public class MaquinaExpendedoraTest {

	
private MaquinaExpendedora maquinaExpendedora;
	
	//metodo que se ejecutara antes de cada test
	@Before
	public void prepararPrueba() {
		this.maquinaExpendedora = new MaquinaExpendedora();
	}
	
	// Tiene 10 tipos diferentes de refrescos
	@Test
	public void whenPedirCantidadTiposDeRefrescosThenObtenerDiez() {

		// espero que obtenga 10
		int espero = 10;

		//ejecuto la accion a probar
		int resultado = maquinaExpendedora.obtenerCantidadRefrescosDiferentes();

		if(espero != resultado) {
			fail("Espero un 10 pero he recibido " + resultado);
		}
	}
	
	// Los refrescos deberan de tener un costo en centimos
	@Test
	public void whenPedirCosteDeRefrescoThenObtenerPrecioEnCentimos() {

		// espero que obtenga 50 (precio en centimos de la Coca-Cola)
		double espero = 0.5;

		//ejecuto la accion a probar
		Refresco refresco = maquinaExpendedora.obtenerRefresco(TiposRefresco.COCA_COLA);
		double resultado = maquinaExpendedora.obtenerPrecioRefresco(refresco);

		if(espero != resultado) {
			fail("Espero un 50 pero he recibido " + resultado);
		}
	}
	
	// Los refrescos deben tener un stock actual disponible
	@Test
	public void whenPedirStockThenObtenerStockActual() {

		// espero que obtenga 10 (stock de Coca-Colas)
		int espero = 10;

		//ejecuto la accion a probar
		Refresco refresco = maquinaExpendedora.obtenerRefresco(TiposRefresco.COCA_COLA);
		int resultado = refresco.getStock();

		if(espero != resultado) {
			fail("Espero un 10 pero he recibido " + resultado);
		}
	}
	
	// Los refrescos deben tener un stock actual disponible
	@Test
	public void whenMaquinaCargaThenCargarRefrescosYCambios() {

		// espero que obtenga 10 (numero de refrescos diferentes) y 30(dinero para cambios)
		int espero1 = 10;
		double espero2 = 30;

		//ejecuto la accion a probar
		int resultado1 = maquinaExpendedora.obtenerCantidadRefrescosDiferentes();
		double resultado2 = maquinaExpendedora.getDinero();

		if(espero1 != resultado1 || espero2 != resultado2) {
			fail("Espero un 10 y 30 pero he recibido " + resultado1 + " y " + resultado2);
		}
	}
	
	// Los clientes pueden soicitar saber el precio de un producto
	@Test
	public void whenClientePidePecioThenMaquinaDevueveElPrecio() {
		// espero que obtenga 50 (precio en centimos de la Coca-Cola)
		double espero = 0.5;

		//ejecuto la accion a probar
		Cliente c = new Cliente("Carlos",2);
		Refresco refresco = maquinaExpendedora.obtenerRefresco(TiposRefresco.COCA_COLA);
		double resultado = c.pedirPrecioProducto(refresco, maquinaExpendedora);

		if(espero != resultado) {
			fail("Espero un 0.5 pero he recibido " + resultado);
		}
	}
	
	// Si el dinero es insuficiente avisa
	@Test(expected = ImporteInsuficienteParaCompraException.class)
	public void whenImporteInsuficienteThenAvisar() throws ImporteInsuficienteParaCompraException, DineroClienteInsuficienteParaCompraException, NoQuedaRefrescoException {
		Cliente c = new Cliente("Carlos",2);
		Refresco refresco = maquinaExpendedora.obtenerRefresco(TiposRefresco.COCA_COLA);
		c.comprarRefrescoMaquina(maquinaExpendedora, refresco, 0.4);
	}
	
	// Si e cliente compra con exito devuelve cambio
	@Test
	public void whenClienteCompraYImporteIntroducidoSuperiorThenMaquinaDevuelveCambios() throws ImporteInsuficienteParaCompraException, DineroClienteInsuficienteParaCompraException, NoQuedaRefrescoException {
		// espero que obtenga 1.5 (dinero del cliente tras recibir cambios)
		double espero = 1.5;
		//ejecuto la accion a probar
		Cliente c = new Cliente("Carlos",2);
		Refresco refresco = maquinaExpendedora.obtenerRefresco(TiposRefresco.COCA_COLA);
		c.comprarRefrescoMaquina(maquinaExpendedora,refresco, 0.6);
		double resultado = c.getDinero();

		if(espero != resultado) {
			fail("Espero un 1.5 pero he recibido " + resultado);
		}
	}
	
	// Si no queda refresco avisa
	@Test(expected = NoQuedaRefrescoException.class)
	public void whenNoQuedaRefrescoThenAvisar() throws ImporteInsuficienteParaCompraException, DineroClienteInsuficienteParaCompraException, NoQuedaRefrescoException {
		Cliente c = new Cliente("Carlos",2);
		Refresco refresco = maquinaExpendedora.obtenerRefresco(TiposRefresco.AQUARIUS);
		c.comprarRefrescoMaquina(maquinaExpendedora, refresco, 0.4);
	}
	
	// Los reponedores pueden cargar más refrescos a las maquinas
	@Test
	public void whenReponerdorCargaProductosThenStockProductosAumenta() {
		// espero que obtenga 10 (Burn despues de reponer)
		int espero = 10;
		//ejecuto la accion a probar
		Reponedor r = new Reponedor("Maria");
		Refresco refresco = maquinaExpendedora.obtenerRefresco(TiposRefresco.BURN);
		r.reponerRefresco(refresco, 10);
		int resultado = refresco.getStock();

		if(espero != resultado) {
			fail("Espero un 10 pero he recibido " + resultado);
		}
	}
	
	//Los recaudadores pueden obtener un informe de la recaudacion obtenida
	@Test
	public void whenRecaudadorPideInforeThenObtieneInforme() throws ImporteInsuficienteParaCompraException, DineroClienteInsuficienteParaCompraException, NoQuedaRefrescoException {
		// espero que obtenga un informe con todas las compras
		ArrayList<Informe> espero = maquinaExpendedora.getInformes();
		//ejecuto la accion a probar
		Cliente c = new Cliente("Carlos",2);
		Refresco refresco = maquinaExpendedora.obtenerRefresco(TiposRefresco.COCA_COLA);
		c.comprarRefrescoMaquina(maquinaExpendedora,refresco, 0.6);
		Recaudador r = new Recaudador("Diana");
		r.pedirInformes(maquinaExpendedora);
		r.enseniarInforme();
		ArrayList<Informe> resultado = r.getInformes();

		if(espero != resultado) {
			fail("No he recibido el mismo informe");
		}
	}
	
}
