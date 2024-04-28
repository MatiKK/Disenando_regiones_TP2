package logica;

import java.util.HashMap;
import java.util.Map;


public class CoordenadasProvinciasArgentina {
    private Map<String, Coordenada> capitales;

    public CoordenadasProvinciasArgentina() {
        this.capitales = new HashMap<>();
        // Agregar las coordenadas de las capitales de las provincias de Argentina
        agregarCoordenadas();
    }

    private void agregarCoordenadas() {
    	this.capitales.put("Buenos Aires", new Coordenada(-36.5, -60.5));
    	// En el enunciado se incluye CABA. Para fines prácticos, se la considera provincia
    	this.capitales.put("Ciudad Autónoma de Buenos Aires", new Coordenada(-34.6037, -58.3816));
    	this.capitales.put("Catamarca", new Coordenada(-27.25, -67.25));
    	this.capitales.put("Chaco", new Coordenada(-26.5, -60.75));
    	this.capitales.put("Chubut", new Coordenada(-44, -68.5));
    	this.capitales.put("Córdoba", new Coordenada(-32, -63.8));
    	this.capitales.put("Corrientes", new Coordenada(-28.75, -57.9));
    	this.capitales.put("Entre Ríos", new Coordenada(-32, -59.35));
    	this.capitales.put("Formosa", new Coordenada(-24.75, -60));
    	this.capitales.put("Jujuy", new Coordenada(-23, -66));
    	this.capitales.put("La Rioja", new Coordenada(-29.4131, -67.25));
    	this.capitales.put("La Pampa", new Coordenada(-37.25, -65.5));
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
    	CoordenadasProvinciasArgentina coordenadas = new CoordenadasProvinciasArgentina();
        Coordenada buenosAiresCoord = coordenadas.obtenerCoordenadas("Buenos Aires");
        System.out.println("Coordenadas de Buenos Aires:");
        System.out.println("Latitud: " + buenosAiresCoord.getLatitud());
        System.out.println("Longitud: " + buenosAiresCoord.getLongitud());
    }

	public Map<String, Coordenada> getCapitales() {
		
		return capitales;
	}
}

