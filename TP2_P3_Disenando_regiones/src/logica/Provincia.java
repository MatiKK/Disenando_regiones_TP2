package logica;

import java.util.Arrays;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Provincia {

	private String nombre;
	private Coordinate coordenada;
	java.util.ArrayList<Provincia> limitrofes;
	public Provincia(String name, Coordinate coords) {
		nombre = name;
		coordenada = coords;
		limitrofes = new java.util.ArrayList<>();
	}

	public void agregarLimitrofes(Provincia... p) {
		limitrofes.addAll(Arrays.asList(p));
	}

	public String toString() {
		return nombre;
	}

	public Coordinate coordenadas() {
		return coordenada;
	}

}
