package grafosLogica;

import java.util.*;

@SuppressWarnings("unused")
public class TesteoGrafos {

	public static void main(String[] args) {

		// grafo de la diapositiva de AGM
		Grafo g = new Grafo(9);
		g.agregarAristaEntreVertices(0, 1, 4);
		g.agregarAristaEntreVertices(0, 7, 8);
		g.agregarAristaEntreVertices(1, 7, 12);
		g.agregarAristaEntreVertices(1, 2, 8);
		g.agregarAristaEntreVertices(2, 3, 6);
		g.agregarAristaEntreVertices(2, 8, 3);
		g.agregarAristaEntreVertices(2, 5, 4);
		g.agregarAristaEntreVertices(3, 4, 9);
		g.agregarAristaEntreVertices(4, 5, 10);
		g.agregarAristaEntreVertices(5, 6, 3);
		g.agregarAristaEntreVertices(6, 7, 1);
		g.agregarAristaEntreVertices(6, 8, 5);
		g.agregarAristaEntreVertices(7, 8, 6);

//		Collection<Integer> vertices = new ArrayList<>(Arrays.asList(1,2,3,4,5));
//		Collection<Arista> aristas = new ArrayList<Arista>(Arrays.asList(
//				new Arista(1,2,8),
//				new Arista(1,3,1),
//				new Arista(2,3,7),
//				new Arista(3,4,6),
//				new Arista(3,5,5),
//				new Arista(4,5,10)
//				
//		));
//		Grafo g = new Grafo(vertices,aristas);

		System.out.println("G es conexo: " + g.esConexo());
		g.printData();

		System.out.println("\nAGM de G:");
		Grafo g_AGM = g.obtenerAGM();
		g_AGM.printData();

	}

}
