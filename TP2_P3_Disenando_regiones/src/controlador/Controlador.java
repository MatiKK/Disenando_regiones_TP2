package controlador;

import vista.Main;

import logica.CoordenadasProvinciasArgentina;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import java.util.Map;

public class Controlador {
    private Main vista;
    private CoordenadasProvinciasArgentina modelo;

    public Controlador(Main vista, CoordenadasProvinciasArgentina modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void mostrarMapaConGrafo() {
        // Obtener las coordenadas de las capitales de Argentina
        Map<String, CoordenadasProvinciasArgentina.Coordenada> coordenadasMap = modelo.getCapitales();

        // Agregar los puntos al mapa
        for (Map.Entry<String, CoordenadasProvinciasArgentina.Coordenada> entry : coordenadasMap.entrySet()) {
        	CoordenadasProvinciasArgentina.Coordenada coord = entry.getValue();
            vista.getMapViewer().addMapMarker(new MapMarkerDot(coord.getLatitud(), coord.getLongitud()));
        }

        // Establecer el color de los marcadores en rojo
        vista.getMapViewer().setMapMarkerVisible(true);
    }
}
