package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class CoordenadasCapitalesArgentina {
    private Map<String, Coordenada> capitales;
    private ArrayList<Provincia> provincias;
    
    private int[][] matrizAdyacencia;

    public CoordenadasCapitalesArgentina() {
        this.capitales = new HashMap<>();
        
        this.provincias = new ArrayList<Provincia>(); 
        
        this.matrizAdyacencia = new int[provincias.size()][provincias.size()];
        
        // Agregar las coordenadas de las capitales de las provincias de Argentina
        agregarCoordenadas();
        agregarProvincias();
        
        
        //construirMatrizAdyacencia();
    }
    
    
    public void agregarProvincias() {
    	this.provincias.add(new Provincia("Buenos Aires", new Coordinate(-36.5, -60.5)));
    	this.provincias.add(new Provincia("Catamarca", new Coordinate(-26.5, -60.75)));
    	this.provincias.add(new Provincia("Chaco", new Coordinate(-26.5, -60.75)));
    	this.provincias.add(new Provincia("Chubut", new Coordinate(-32, -63.8)));
    	this.provincias.add(new Provincia("Córdoba", new Coordinate(-32, -63.8)));
    	this.provincias.add(new Provincia("Corrientes", new Coordinate(-28.75, -57.9)));
    	this.provincias.add(new Provincia("Entre Ríos", new Coordinate(-32, -59.35)));
    	this.provincias.add(new Provincia("Formosa", new Coordinate(-24.75, -60)));
    	this.provincias.add(new Provincia("Jujuy", new Coordinate(-23, -66)));
    	this.provincias.add(new Provincia("La Pampa", new Coordinate(-37.25, -65.5)));
    	this.provincias.add(new Provincia("La Rioja", new Coordinate(-29.4131, -67.25)));
    	this.provincias.add(new Provincia("Mendoza", new Coordinate(-34.5, -68.5)));
    	this.provincias.add(new Provincia("Misiones", new Coordinate(-27, -54.75)));
    	this.provincias.add(new Provincia("Neuquén", new Coordinate(-38.5, -70)));
    	this.provincias.add(new Provincia("Río Negro", new Coordinate(-40.25, -67.25)));
    	this.provincias.add(new Provincia("Salta", new Coordinate(-25.25, -64.75)));
    	this.provincias.add(new Provincia("San Juan", new Coordinate(-30.75, -68.8)));
    	this.provincias.add(new Provincia("San Luis", new Coordinate(-33.75, -66.1)));
    	this.provincias.add(new Provincia("Santa Cruz", new Coordinate(-48.6226, -70)));
    	this.provincias.add(new Provincia("Santa Fe", new Coordinate(-30.6107, -61)));
    	this.provincias.add(new Provincia("Santiago del Estero", new Coordinate(-27.7951, -63.5)));
    	this.provincias.add(new Provincia("Tierra del Fuego", new Coordinate(-54.25, -67.7)));
    	this.provincias.add(new Provincia("Tucumán", new Coordinate(-26.9, -65.4)));

    }
    
    
    
    private void construirMatrizAdyacencia() {
        for (int i = 0; i < provincias.size(); i++) {
            for (int j = i + 1; j < provincias.size(); j++) {
                int similaridad = 0;
                matrizAdyacencia[i][j] = similaridad;
                matrizAdyacencia[j][i] = similaridad;
            }
        }
    }
    
    
    
    
    

    private void agregarCoordenadas() {
    	
        // Coordenadas de las capitales de las provincias de Argentina
        this.capitales.put("Buenos Aires", new Coordenada(-36.5, -60.5));
        this.capitales.put("Catamarca", new Coordenada(-26.5, -60.75));
        this.capitales.put("Chaco", new Coordenada(-26.5, -60.75));
        this.capitales.put("Chubut", new Coordenada(-32, -63.8));
        this.capitales.put("Córdoba", new Coordenada(-32, -63.8));
        this.capitales.put("Corrientes", new Coordenada(-28.75, -57.9));
        this.capitales.put("Entre Ríos", new Coordenada(-32, -59.35));
        this.capitales.put("Formosa", new Coordenada(-24.75, -60));
        this.capitales.put("Jujuy", new Coordenada(-23, -66));
        this.capitales.put("La Pampa", new Coordenada(-37.25, -65.5));
        this.capitales.put("La Rioja", new Coordenada(-29.4131, -67.25));
        this.capitales.put("Mendoza", new Coordenada(-34.5, -68.5));
        this.capitales.put("Misiones", new Coordenada(-27, -54.75));
        this.capitales.put("Neuquén", new Coordenada(-38.5, -70));
        this.capitales.put("Río Negro", new Coordenada(-40.25, -67.25));
        this.capitales.put("Salta", new Coordenada(-25.25, -64.75));
        this.capitales.put("San Juan", new Coordenada(-30.75, -68.8));
        this.capitales.put("San Luis", new Coordenada(-33.75, -66.1));
        this.capitales.put("Santa Cruz", new Coordenada(-48.6226, -70));
        this.capitales.put("Santa Fe", new Coordenada(-30.6107, -61));
        this.capitales.put("Santiago del Estero", new Coordenada(-27.7951, -63.5));
        this.capitales.put("Tierra del Fuego", new Coordenada(-54.25, -67.7));
        this.capitales.put("Tucumán", new Coordenada(-26.9, -65.4));
  	
        
    }

    public Coordenada obtenerCoordenadas(String provincia) {
        return this.capitales.get(provincia);
    }

    public static class Coordenada {
        private double latitud;
        private double longitud;

        public Coordenada(double latitud, double longitud) {
            this.latitud = latitud;
            this.longitud = longitud;
        }

        public double getLatitud() {
            return latitud;
        }

        public double getLongitud() {
            return longitud;
        }
    }

    public static void main(String[] args) {
        CoordenadasCapitalesArgentina coordenadas = new CoordenadasCapitalesArgentina();
        Coordenada buenosAiresCoord = coordenadas.obtenerCoordenadas("Buenos Aires");
        System.out.println("Coordenadas de Buenos Aires:");
        System.out.println("Latitud: " + buenosAiresCoord.getLatitud());
        System.out.println("Longitud: " + buenosAiresCoord.getLongitud());
    }

	public Map<String, Coordenada> getCapitales() {
		
		return capitales;
	}
	
	public ArrayList<String> getNombreProvincias(){
		ArrayList<String> listadoNombresProv = new ArrayList<String>();
		
		for (int i = 0; i < provincias.size(); i++) {
			
			listadoNombresProv.add(provincias.get(i).toString());
			
		}
		
		return listadoNombresProv;
		
		
	}
	
	public ArrayList<Provincia> getProvincias(){
		return provincias;
	}
	
	
	
}

