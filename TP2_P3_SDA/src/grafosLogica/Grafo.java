package grafosLogica;

import java.util.*;

public class Grafo {

	private TreeMap<Integer, TreeSet<Arista>> adjList;

	public Grafo() {
		this(0);
	}

	public Grafo(int n) {
		crearMapa();
		for (int i = 0; i < n; i++)
			agregarVerticeIgnorarChequeo(i);
	}

	public Grafo(Collection<Integer> vertices) {
		this(vertices, null);
	}

	@SuppressWarnings("unused")
	public Grafo(Collection<Integer> vertices, Collection<Arista> aristas) {

		crearMapa();

		if (vertices.isEmpty() && !aristas.isEmpty())
			throw new IllegalArgumentException("Inconsistencia: sin vertices y con aristas");

		for (int vertice: vertices)
			agregarVerticeIgnorarChequeo(vertice);

		if (aristas == null) return; // Grafo sin aristas

		loop:
		for (Arista arista: aristas) {

			int vertInicio = arista.obtenerVerticeInicio();
			int vertDestino = arista.obtenerVerticeDestino();
			chequearQueSeanDistintosVertices(vertInicio, vertDestino);

			TreeSet<Arista> aristasDelVertice = adjList.get(vertInicio);

			for (Arista a: aristasDelVertice) {
				if (a.equals(arista)) {
//					continue loop;
					System.out.println(a + " == " + arista);
					throw new IllegalArgumentException("Arista repetida");
				}
			}

			aristasDelVertice.add(arista);

			Arista aristaAux = new Arista(vertDestino, vertInicio, arista.obtenerPeso());
			aristasDelVertice = adjList.get(vertDestino);
			aristasDelVertice.add(aristaAux);
		}
	}

	private void crearMapa() {
		adjList = new TreeMap<>();
	}

	protected Map<Integer, TreeSet<Arista>> listaDeAdyacencias() {
		return adjList;
	}

	public int tamanio() {
		return adjList.size();
	}

	private void chequearValidezVertice(int v) {
		if (!adjList.containsKey(v))
			throw new IllegalArgumentException("Vértice no existente");
	}

	private void chequearValidezPosibleArista(int v1, int v2, double p) {
		if (p < 0)
			throw new IllegalArgumentException("peso negativo no válido");
		chequearValidezVertice(v1);
		chequearValidezVertice(v2);
		chequearQueSeanDistintosVertices(v1, v2);
	}

	private void chequearQueSeanDistintosVertices(int v1, int v2) {
		if (v1 == v2) throw new IllegalArgumentException
		("Invalidado crear arista entre un mismo vértice");
	}

	public void agregarVertice(int n) {
		chequearValidezVertice(n);
		if (!adjList.containsKey(n)) {
			agregarVerticeIgnorarChequeo(n);
		}
	}

	private void agregarVerticeIgnorarChequeo(int v) {
		adjList.put(v, new TreeSet<>(Arista.aristaComparator()));
	}
	
	public void agregarAristaEntreVertices(int v1, int v2, double p) {
		chequearValidezPosibleArista(v1,v2,p);
		adjList.get(v1).add(new Arista(v1,v2,p));
		adjList.get(v2).add(new Arista(v2,v1,p));
	}

	public Set<Integer> vecinosDeVertice(int v) {
		Set<Integer> vecinos = new HashSet<>();
		
		for (Arista arista: adjList.get(v))
			vecinos.add(arista.obtenerVerticeDestino());
		
		return vecinos;
	}

	protected int primerVertice() {
		return adjList.firstKey();
	}

	// para testear bfs
	public void printData() {
		for (int vertice: adjList.keySet()) {
			System.out.print(vertice + " es vecino de: ");
			TreeSet<Arista> aristas = adjList.get(vertice);
			for (Arista a: aristas) {
				System.out.print(a.obtenerVerticeDestino()+", ");
			}
			System.out.println();
		}
//		for (int vertice: adjList.keySet()) {
//			System.out.print((char)(vertice + 65)+ " es vecino de: ");
//			TreeSet<Arista> aristas = adjList.get(vertice);
//			for (Arista a: aristas) {
//				System.out.print(((char) (a.obtenerVerticeDestino() + 65))+", ");
//			}
//			System.out.println();
//		}
	}
	
	public boolean esConexo() {
		return BFS.esConexo(this);
	}

	public Grafo obtenerAGM() {
		return AGM.AGMdelGrafo(this);
	}

}
