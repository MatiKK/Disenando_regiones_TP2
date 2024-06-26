package grafosLogica;

import java.util.*;
@SuppressWarnings("all")
public class TesteoGrafos {

	public static void main(String[] args) {

		Grafo<?> g = EjemploGrafos.grafo1();
		boolean esConexo = g.esConexo();
		System.out.println("g es conexo? " + esConexo);
		g.printData();
		System.out.println();

		if (esConexo) {

			System.out.println("Aristas del AGM: ");
			Set<?> aristas = new AGM<>(g).aristasDelAGM();
			for (Object arista: aristas)
				System.out.println(arista);

		}

	}

}
