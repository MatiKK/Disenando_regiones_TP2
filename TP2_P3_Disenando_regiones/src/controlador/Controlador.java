package controlador;

import vista.Main;

import logica.CoordenadasProvinciasArgentina;
import logica.Provincia;
import logica.ProvinciasArgentinas;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import java.util.Map;

public class Controlador {

    private Main vista;

    public Controlador(Main vista) {
        this.vista = vista;
    }

    public void mostrarMapaConGrafo() {

    	JMapViewer mapa = vista.getMapViewer();

    	Provincia[] provincias = ProvinciasArgentinas.provinciasDeArgentina();
    	int cantProvincias = provincias.length;
    	boolean[][] relacionLimitrofe = ProvinciasArgentinas.relacionDeProvinciasLimitrofes;

    	for (int i = 0; i < cantProvincias; i++) {
    		Provincia p1 = provincias[i];
    		pintarPuntoEnProvincia(mapa, p1);
    		for (int j = i + 1; j < cantProvincias; j++) {
    			Provincia p2 = provincias[j];
    			boolean provinciasLimitrofes = relacionLimitrofe[i][j];
    			System.out.println(p1 + " limitrofe con "+p2+"? " + provinciasLimitrofes);
    			if (provinciasLimitrofes) {
    				pintarLineaEntreProvincias(mapa, p1, p2);
    			}
    		}
    	}
    }

    private void pintarPuntoEnProvincia(JMapViewer mapa, Provincia p) {
    	mapa.addMapMarker(new MapMarkerDot(
//    			p.toString(), // ver si se puede hacer la letra mas chica
    			p.coordenadas()));
    }

    private void pintarLineaEntreProvincias(JMapViewer mapa, Provincia p1, Provincia p2) {
    	mapa.addMapPolygon(new MapPolygonImpl(p1.coordenadas(), p2.coordenadas(),p1.coordenadas()));
    }

}
