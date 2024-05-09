package logica;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Arista {
    private Coordinate origen;
    private Coordinate destino;
    private int peso;

    public Arista(Coordinate origen, Coordinate destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Coordinate getOrigen() {
        return origen;
    }

    public Coordinate getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }
    
    public String toStringPeso() {
		return String.valueOf(peso);
	}
    
}

