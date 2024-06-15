package grafosLogica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class EjemploGrafos {

	private static Grafo<?> grafo1;
	private static Grafo<?> grafo2;
	private static Grafo<?> grafo3;

	public static Grafo<?> grafo1(){
		if (grafo1 == null) inicializarGrafo1();
		return grafo1;
	}

	public static Grafo<?> grafo2(){
		if (grafo2 == null) inicializarGrafo2();
		return grafo2;
	}

	public static Grafo<?> grafo3(){
		if (grafo3 == null) inicializarGrafo3();
		return grafo3;
	}

	private static void inicializarGrafo1() {
		// conexo
		// Grafo de la diapositiva de Árbol Generador Mínimo
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
		grafo1 = g;
	}

	private static void inicializarGrafo2() {
		// conexo
		Collection<Integer> vertices = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		Collection<Arista<Integer>> aristas = new ArrayList<>(Arrays.asList(
				new Arista<>(1,2,8),
				new Arista<>(1,3,1),
				new Arista<>(2,3,7),
				new Arista<>(3,4,6),
				new Arista<>(3,5,5),
				new Arista<>(4,5,10)
		));
		grafo2 = new Grafo<>(vertices,aristas);
	}

	private static void inicializarGrafo3() {
		// no conexo
		Collection<Character> vertices = new HashSet<>(Arrays.asList('a','b','c','d','e','f'));
		Collection<Arista<Character>> aristas = new HashSet<>(Arrays.asList(
				new Arista<>('a','b',2),
				new Arista<>('b','c',2),
				new Arista<>('f','e',4),
//				new Arista<>('c','f',8),
//				new Arista<>('e','a',5),
//				new Arista<>('f','d',3),
				new Arista<>('d','a',1)

				));
		grafo3 = new Grafo<>(vertices, aristas);
	}

}
