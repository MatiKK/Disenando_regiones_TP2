package grafosLogica;

import java.util.*;

public class AGM {

	private static Map<Integer, TreeSet<Arista>> adjList;
	private static TreeSet<Arista> aristasConExtremoFuera;
	private static Set<Arista> aristasDelAGM;
	private static List<Integer> verticesConAristasPotenciales;

	public static Grafo AGMdelGrafo(Grafo g) {
		if (!g.esConexo())
			throw new IllegalArgumentException("Grafo no conexo");
		return algoritmoAGM(g);
	}

	private static void inicializarObjetosUtiles(Grafo g) {
		adjList = g.listaDeAdyacencias();
		aristasConExtremoFuera = new TreeSet<>(Arista.aristaComparator());
		aristasDelAGM = new HashSet<>();
		verticesConAristasPotenciales = new ArrayList<>();
		verticesConAristasPotenciales.add(g.primerVertice());
	}

	// creo de sería de kruskal
	private static Grafo algoritmoAGM(Grafo g) {

		inicializarObjetosUtiles(g);

		while (verticesConAristasPotenciales.size() != g.tamanio()) {
			agregarAristasConExtremos();
			Arista aristaMenorPeso = obtenerAristaDeMenorPesoEntreLasPosibles();
			verticesConAristasPotenciales.add(aristaMenorPeso.obtenerVerticeDestino());
			aristasDelAGM.add(aristaMenorPeso);
			descartarAristasQueGenerarianCiclos();
		}
		return new Grafo(verticesConAristasPotenciales, aristasDelAGM);
	}

	private static Arista obtenerAristaDeMenorPesoEntreLasPosibles() {
//		System.out.println(aristasConExtremoFuera + "\n");
		return aristasConExtremoFuera.pollFirst();
	}

	private static void agregarAristasConExtremos() {
		for (int vertice: verticesConAristasPotenciales) {
			for (Arista arista: adjList.get(vertice)) {
				if (!aristasDelAGM.contains(arista) &&
				!verticesConAristasPotenciales.contains(arista.obtenerVerticeDestino()))
				{
					aristasConExtremoFuera.add(arista);
				}
			}
		}
	}

	private static void descartarAristasQueGenerarianCiclos() {
		Set<Arista> aristasParaDescartar = new HashSet<>();
		for (Arista arista: aristasConExtremoFuera) {
			if (verticesConAristasPotenciales.contains(arista.obtenerVerticeInicio())
			&& verticesConAristasPotenciales.contains(arista.obtenerVerticeDestino())) {
				aristasParaDescartar.add(arista);
			}
		}
		aristasConExtremoFuera.removeAll(aristasParaDescartar);
	}

}