package grafosLogica;

import java.util.*;

public class AGM<T extends Comparable<T>>{

	private Map<T, TreeSet<Arista<T>>> adjList;
	private TreeSet<Arista<T>> aristasConExtremoFuera;
	private TreeSet<Arista<T>> aristasDelAGM;
	private List<T> verticesConAristasPotenciales;
	private Grafo<T> g;

	private AGM(Grafo<T> g) {
		this.g = g;
		algoritmoAGM();
	}

	public static <T extends Comparable<T>> TreeSet<Arista<T>> aristasDelAGM(Grafo<T> g) {
		return new AGM<>(g).aristasDelAGM;
	}

	public static <T extends Comparable<T>> Grafo<T> AGMdelGrafo(Grafo<T> g) {
		if (!g.esConexo())
			throw new IllegalArgumentException("Grafo no conexo");
		return new AGM<T>(g).algoritmoAGM();
	}

	private void inicializarObjetosUtiles() {
		adjList = g.listaDeAdyacencias();
		aristasConExtremoFuera = new TreeSet<>(Arista.aristaComparator());
		aristasDelAGM = new TreeSet<>();
		verticesConAristasPotenciales = new ArrayList<>();
		verticesConAristasPotenciales.add(g.primerVertice());
	}

	// creo de sería de kruskal
	private Grafo<T> algoritmoAGM() {

		inicializarObjetosUtiles();

		while (verticesConAristasPotenciales.size() != g.tamanio()) {
			agregarAristasConExtremos();
			Arista<T> aristaMenorPeso = obtenerAristaDeMenorPesoEntreLasPosibles();
			verticesConAristasPotenciales.add(aristaMenorPeso.obtenerVerticeDestino());
			aristasDelAGM.add(aristaMenorPeso);
			descartarAristasQueGenerarianCiclos();
		}
		return new Grafo<>(verticesConAristasPotenciales, aristasDelAGM);
	}

	private Arista<T> obtenerAristaDeMenorPesoEntreLasPosibles() {
//		System.out.println(aristasConExtremoFuera + "\n");
		return aristasConExtremoFuera.pollFirst();
	}

	private void agregarAristasConExtremos() {
		for (T vertice: verticesConAristasPotenciales) {
			for (Arista<T> arista: adjList.get(vertice)) {
				if (!aristasDelAGM.contains(arista) &&
				!verticesConAristasPotenciales.contains(arista.obtenerVerticeDestino()))
				{
					aristasConExtremoFuera.add(arista);
				}
			}
		}
	}

	private void descartarAristasQueGenerarianCiclos() {
		Set<Arista<T>> aristasParaDescartar = new HashSet<>();
		for (Arista<T> arista: aristasConExtremoFuera) {
			if (verticesConAristasPotenciales.contains(arista.obtenerVerticeInicio())
			&& verticesConAristasPotenciales.contains(arista.obtenerVerticeDestino())) {
				aristasParaDescartar.add(arista);
			}
		}
		aristasConExtremoFuera.removeAll(aristasParaDescartar);
	}

}