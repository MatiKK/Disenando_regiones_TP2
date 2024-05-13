package logica;


import java.util.Arrays;
import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Provincia implements Comparable<Provincia>{

	private String nombre;
	private Coordinate coordenada;
	private ArrayList<Provincia> limitrofes;
	
	public Provincia(String name, Coordinate coords) {
		nombre = name;
		coordenada = coords;
		limitrofes = new java.util.ArrayList<>();
	}

	public void agregarLimitrofes(Provincia... p) {
		limitrofes.addAll(Arrays.asList(p));
		
	}

	public int compareTo(Provincia p) {
		Coordinate coordp = p.coordenadas();
		int a;
		a = Double.compare(coordenada.getLat(), coordp.getLat());
		if (a == 0) {
			a = Double.compare(coordenada.getLon(), coordp.getLon());
			if (a == 0)
				a = nombre.compareTo(p.nombre);
		}
		return a;
	}

	public ArrayList<Provincia> obtenerLimitrofes(){
//		return (ArrayList<Provincia>) limitrofes.clone();
		return limitrofes;
	}

	public String toString() {
		return nombre;
	}

	public Coordinate coordenadas() {
		return coordenada;
	}

}
