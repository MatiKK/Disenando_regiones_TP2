package logica;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import logica.CoordenadasCapitalesArgentina.Coordenada;

public class Arista {
    private Coordinate origen;
    private Coordinate destino;
    private int peso;

    public Arista(Coordinate origen, Coordinate destino) {
        this.origen = origen;
        this.destino = destino;
        //this.peso = peso;
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
    
    public String toString() {
    	
    	
    	String resultado = "Arista: origen: " + origen.toString() + " destino: " + destino.toString() + " peso: " + peso;
    	
    	return resultado;
    }

	public void actualizarPeso(int pesoEnNumero) {
		this.peso = pesoEnNumero;
		
	}
    

    
    
    
 
    
}

