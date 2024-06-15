package controlador;

import vista.Main;
import logica.Provincia;
import grafosLogica.*;
import java.awt.Color;
import java.util.*;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

public class Controlador {
    private Main vista;
    private Grafo<Provincia> grafo;
    private Set<Provincia> provincias;
    private Set<Arista<Provincia>> aristasG;
    private TreeSet<Arista<Provincia>> aristasAGM;

    public Controlador(Main vista) {
    	this.vista = vista;
    	inicializarObjetos();
    }

    private void mostrarMapaConGrafo(Set<Arista<Provincia>> aristas) {
    	limpiarMapa();
    	for (Provincia p: provincias)
    		mostrarPunto(vista.getMapViewer(),p.coordenadas(), p.toString(), Color.YELLOW);
    	for (Arista<Provincia> ar: aristas) {
    		graficarArista(vista.getMapViewer(), ar);
    	}
    }

    public void mostrarMapaConGrafo() {
    	mostrarMapaConGrafo(aristasG);
    }

    // true si se pudo calcular agm, false si no
    public boolean dibujarAGM() {
    	try {
    		aristasAGM = grafo.aristasDelAGM();
    		limpiarMapa();
    		mostrarMapaConGrafo(aristasAGM);
    		return true;
    	} catch (GrafoNoConexoException e) {
    		vista.mostrarAlerta("El grafo aún no es conexo, añada más aristas");
    	} catch (NoSuchElementException e) {
    		vista.mostrarAlerta("¡Aún no hay vértices!");
    	}
    	return false;
    }

    private void limpiarPuntos() {
    	vista.getMapViewer().removeAllMapMarkers();
    }
    
    private void limpiarAristas() {
    	vista.getMapViewer().removeAllMapPolygons();
    }

    public void limpiarMapa() {
    	limpiarPuntos();
    	limpiarAristas();
    }

    public void quitarAristasDelAGM(int n) {
    	int cantRegiones = n - 1;
    	if (cantRegiones < 0) {
    		vista.mostrarAlerta("No puede ingresar un valor negativo.");
    		return;
    	}
    	if (cantRegiones >= grafo.tamanio()) {
    		vista.mostrarAlerta("Por favor, indique una cantidad menor.");
    		return;
    	}
    	for (int i = 0; i < cantRegiones; i++)
    		aristasAGM.pollLast();

    	limpiarMapa();
    	mostrarMapaConGrafo(aristasAGM);
    }


    private void graficarArista(JMapViewer map, Arista<Provincia> ar) {

    	Coordinate c1 = ar.obtenerVerticeInicio().coordenadas();
    	Coordinate c2 = ar.obtenerVerticeDestino().coordenadas();

    	mostrarPunto(map,c1,ar.obtenerVerticeInicio().toString(), Color.YELLOW);
    	mostrarPunto(map,c2,ar.obtenerVerticeDestino().toString(), Color.YELLOW);

    	dibujarLineaEntreCoordenadas(map, c1,c2);
    	dibujarPesoEnElMedio(map,c1,c2,ar.obtenerPeso());
    }

    private void dibujarLineaEntreCoordenadas(JMapViewer map, Coordinate c1, Coordinate c2) {
    	map.addMapPolygon(new MapPolygonImpl(c1,c2,c1));
    }

    private void dibujarPesoEnElMedio(JMapViewer map, Coordinate c1, Coordinate c2, double peso) {
    	Coordinate coordPeso;
    	double x = (c1.getLat() + c2.getLat()) / 2;
    	double y = (c1.getLon() + c2.getLon()) / 2;
    	coordPeso = new Coordinate(x, y);
    	mostrarPunto(map, coordPeso, String.valueOf((int) peso), Color.RED);
    }

    public void agregarNuevaProvincia(Provincia p) {
    	grafo.agregarVertice(p);
    	provincias.add(p);
    	mostrarPunto(vista.getMapViewer(), p.coordenadas(), p.toString(), Color.YELLOW);
    }

    private void mostrarPunto(JMapViewer map, Coordinate c, String text, Color color) {
    	MapMarkerDot punto = new MapMarkerDot(c);
    	if (text != null) punto.setName(text);
    	punto.setBackColor(color);
    	map.addMapMarker(punto);
    }

    public void nuevaArista(Provincia p1, Provincia p2, double peso) {
    	try {
    		grafo.agregarAristaEntreVertices(p1, p2, peso);
    		Arista<Provincia> ar = new Arista<>(p1,p2,peso);
    		if (agregarArista(ar)) {
    			graficarArista(vista.getMapViewer(), ar);
    		} else {
    			// No enconté forma de poder hacerlo
    			vista.mostrarAlerta("¡No puede cambiar el peso de la arista!");
    		}
    	} catch (IllegalArgumentException e) {
    		vista.mostrarAlerta("¡No puede añadir una relación entre un mismo vértice!");
    	}
    }
    
    public boolean agregarArista(Arista<Provincia> ar) {
    	return aristasG.add(ar);
    }

    private void inicializarObjetos(){
    	grafo = new Grafo<>();
        provincias = new HashSet<>();
        inicializarAristas();
    }

    private void inicializarAristas(){
    	aristasG = new HashSet<>();
    	aristasAGM = new TreeSet<>();
    }

    public void quitarTodosLosPuntos() {
    	inicializarObjetos();
    }

    public void grafoCompletoAristasAleatorias() {
    	inicializarAristas();
		for (Provincia p: provincias) {
			for (Provincia p2: provincias) {
				if (p.equals(p2)) continue;
					double peso = (double) new java.util.Random().nextInt(1, 100);
					Arista<Provincia> ar = new Arista<>(p,p2,peso);
					if (agregarArista(ar))
						grafo.agregarAristaEntreVertices(p, p2, peso);
				}
		}
		grafo = new Grafo<>(provincias, aristasG);
		mostrarMapaConGrafo();
    }

    public void provinciasArgentinasPesosAleatorios() {
		for (Provincia p: provincias) {
			for (Provincia p2: p.obtenerLimitrofes()) {
					double peso = (double) new java.util.Random().nextInt(1, 100);
					Arista<Provincia> ar = new Arista<>(p,p2,peso);
					if (agregarArista(ar))
						grafo.agregarAristaEntreVertices(p, p2, peso);
				}
		}
		mostrarMapaConGrafo();
    }

}
