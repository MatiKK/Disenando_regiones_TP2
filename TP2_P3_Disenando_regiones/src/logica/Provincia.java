package logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Provincia {

	private String nombre;
	private Coordinate coordenada;
	java.util.ArrayList<Provincia> limitrofes;
	public ArrayList<Arista> aristasProvLimitrofes;
	
	public Provincia(String name, Coordinate coords) {
		nombre = name;
		coordenada = coords;
		limitrofes = new java.util.ArrayList<>();
		aristasProvLimitrofes = new ArrayList<>();
	}

	public void agregarLimitrofes(Provincia... p) {
		limitrofes.addAll(Arrays.asList(p));
		
	}
	
	public void agregarAristaDeProvinciasLimitrofes(Arista a) {
		aristasProvLimitrofes.add(a);
		System.out.println("-----agregue arista con peso: " + a.getPeso());
	}

	public String toString() {
		return nombre;
	}

	public Coordinate coordenadas() {
		return coordenada;
	}
	
	public ArrayList<Arista> getAristasProvLimitrofes(){
		ArrayList<Arista> resultado = new ArrayList<Arista>();
		if (this.aristasProvLimitrofes.size() != 0 ){
			System.out.println("||||||||||||||||||Entre en getAristasProvLimitrofes");
			return resultado;
		}else {
			for (int i = 0; i > aristasProvLimitrofes.size() ;i++) {
				resultado.add(aristasProvLimitrofes.get(i));
				System.out.println("********Entre en getAristasProvLimitrofes y agregue: " + aristasProvLimitrofes.get(i).getPeso());
			}
		return resultado;
		}
	}

	

}
