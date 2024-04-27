package controlador;

import vista.Main;
import logica.CoordenadasCapitalesArgentina;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import java.util.Map;

public class Controlador {
    private Main vista;
    private CoordenadasCapitalesArgentina modelo;

    public Controlador(Main vista, CoordenadasCapitalesArgentina modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void mostrarMapaConGrafo() {
        // Obtener las coordenadas de las capitales de Argentina
        Map<String, CoordenadasCapitalesArgentina.Coordenada> coordenadasMap = modelo.getCapitales();

        // Agregar los puntos al mapa
        for (Map.Entry<String, CoordenadasCapitalesArgentina.Coordenada> entry : coordenadasMap.entrySet()) {
            CoordenadasCapitalesArgentina.Coordenada coord = entry.getValue();
            vista.getMapViewer().addMapMarker(new MapMarkerDot(coord.getLatitud(), coord.getLongitud()));
        }

        // Establecer el color de los marcadores en rojo
        vista.getMapViewer().setMapMarkerVisible(true);
    }
}
