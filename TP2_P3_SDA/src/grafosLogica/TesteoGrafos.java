package grafosLogica;

import java.util.*;
@SuppressWarnings("all")
public class TesteoGrafos {

	public static void main(String[] args) {

		Grafo<Character> g = (Grafo<Character>) EjemploGrafos.grafo1();
		boolean esConexo = g.esConexo();
		System.out.println("g es conexo? " + esConexo);
		g.printData();
		System.out.println(g.existeAristaEntreVertices('A', 'H'));
		System.out.println(g.pesoDeLaAristaEntreVertices('A', 'H'));
		System.out.println();

		if (esConexo) {
			Grafo<?> g_AGM = g.obtenerAGM();
			System.out.println("AGM de g:");
			g_AGM.printData();
		}

	}

}
