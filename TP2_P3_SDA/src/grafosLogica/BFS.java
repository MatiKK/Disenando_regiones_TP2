package grafosLogica;

import java.util.*;

public class BFS {

	private static List<Integer> verticesNoVisitados;
	private static Set<Integer> visitados;

	public static boolean esConexo(Grafo g) {
		if (g == null) throw new IllegalArgumentException("Grafo null");
		return g.tamanio() == 0 ||
		alcanzablesDesdeVertice(g, g.primerVertice()).size() == g.tamanio();
	}

	private static void inicializarObjetosUtiles(Grafo g, int verticeOrigen) {
		verticesNoVisitados = new ArrayList<>();
		verticesNoVisitados.add(verticeOrigen);
		visitados = new HashSet<>();
	}

	private static Set<Integer> alcanzablesDesdeVertice(Grafo g, int verticeOrigen){
		inicializarObjetosUtiles(g, verticeOrigen);
		while (!verticesNoVisitados.isEmpty()) {
			int v = verticesNoVisitados.get(0);
			visitados.add(v);
			agregarVecinosPendientes(g, v);
			verticesNoVisitados.remove(0);
		}
		return visitados;
	}

	private static void agregarVecinosPendientes(Grafo g, int v) {
		for (int vecino: g.vecinosDeVertice(v)) {
			if (!visitados.contains(vecino) &&
				!verticesNoVisitados.contains(vecino)) {

				verticesNoVisitados.add(vecino);
			}
		}
	}
}
