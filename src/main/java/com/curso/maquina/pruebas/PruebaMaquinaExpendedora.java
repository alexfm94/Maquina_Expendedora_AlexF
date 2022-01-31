package com.curso.maquina.pruebas;

import java.util.ArrayList;

import com.curso.maquina.excepciones.DineroClienteInsuficienteParaCompraException;
import com.curso.maquina.excepciones.ImporteInsuficienteParaCompraException;
import com.curso.maquina.excepciones.NoQuedaRefrescoException;
import com.curso.maquina.modelo.Cliente;
import com.curso.maquina.modelo.MaquinaExpendedora;
import com.curso.maquina.modelo.Recaudador;
import com.curso.maquina.modelo.Refresco;
import com.curso.maquina.modelo.Reponedor;
import com.curso.maquina.modelo.TiposRefresco;

public class PruebaMaquinaExpendedora {

	public static void main(String[] args) {
		
		MaquinaExpendedora m = new MaquinaExpendedora();
		Cliente c1 = new Cliente("Carlos", 2);
		Cliente c2 = new Cliente("Juan", 0.4);
		Cliente c3 = new Cliente("Matilde", 2);
		Reponedor rep = new Reponedor("Maria");
		Recaudador rec = new Recaudador("Diana");
		
		Refresco refresco1 = m.obtenerRefresco(TiposRefresco.COCA_COLA);
		Refresco refresco2 = m.obtenerRefresco(TiposRefresco.AQUARIUS);
		Refresco refresco3 = m.obtenerRefresco(TiposRefresco.BURN);
		try {
			//c1.comprarRefrescoMaquina(m, refresco1, 0.4); -> Prueba meter dinero insuficiente
			//c2.comprarRefrescoMaquina(m, refresco1, 0.5); -> Prueba fondos del cliente insuficientes
			//c1.comprarRefrescoMaquina(m, refresco2, 0.6); -> Prueba refrescos insuficentes;
			c1.comprarRefrescoMaquina(m, refresco1, 0.7);
			c3.comprarRefrescoMaquina(m, refresco1, 0.5);
			c1.comprarRefrescoMaquina(m, refresco1, 0.8);
		} catch (ImporteInsuficienteParaCompraException e) {
			System.out.println("El importe introducido no es suficiente.");
		} catch (DineroClienteInsuficienteParaCompraException e) {
			System.out.println("El cliente no tiene suficientes fondos como para introducir el importe que desea");
		} catch (NoQuedaRefrescoException e) {
			System.out.println("No quedan refrescos");
		}
		
		System.out.println("stock disponible de AQUARIUS: " + refresco3.getStock());
		rep.reponerRefresco(refresco3, 10);
		System.out.println();
		System.out.println("stock disponible de AQUARIUS: " + refresco3.getStock());
		System.out.println();
		rec.pedirInformes(m);
		rec.enseniarInforme();
		
	}

}
