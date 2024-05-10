package grafosLogica;

import java.util.*;

@SuppressWarnings("unused")
public class TesteoGrafos {

	public static void main(String[] args) {
		Grafo<?> g = grafoTest1();
		System.out.println("g es conexo? " + g.esConexo());
		g.printData();

		System.out.println();
		Grafo<?> g_AGM = g.obtenerAGM();
		System.out.println("AGM de g:");
		g_AGM.printData();
	}

	private static Grafo<?> grafoTest1(){
		// grafo de la diapositiva de AGM
		Grafo<Character> g = new Grafo<>();
		g.agregarVertices('A','B','C','D','E','F','G','H','I');
		g.agregarAristaEntreVertices('A', 'B', 4);
		g.agregarAristaEntreVertices('A', 'H', 8);
		g.agregarAristaEntreVertices('B', 'H', 12);
		g.agregarAristaEntreVertices('B', 'C', 8);
		g.agregarAristaEntreVertices('C', 'D', 6);
		g.agregarAristaEntreVertices('C', 'I', 3);
		g.agregarAristaEntreVertices('C', 'F', 4);
		g.agregarAristaEntreVertices('D', 'E', 9);
		g.agregarAristaEntreVertices('E', 'F', 10);
		g.agregarAristaEntreVertices('F', 'G', 3);
		g.agregarAristaEntreVertices('G', 'H', 1);
		g.agregarAristaEntreVertices('G', 'I', 5);
		g.agregarAristaEntreVertices('H', 'I', 6);
		return g;
	}

	private static Grafo<?> grafoTest2(){
		Collection<Integer> vertices = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		Collection<Arista<Integer>> aristas = new ArrayList<>(Arrays.asList(
				new Arista<>(1,2,8),
				new Arista<>(1,3,1),
				new Arista<>(2,3,7),
				new Arista<>(3,4,6),
				new Arista<>(3,5,5),
				new Arista<>(4,5,10)
		));
		return new Grafo<>(vertices,aristas);
	}

}
