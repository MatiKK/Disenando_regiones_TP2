package logica;

import java.util.HashMap;
import java.util.Map;

public class CoordenadasCapitalesArgentina {
    private Map<String, Coordenada> capitales;

    public CoordenadasCapitalesArgentina() {
        this.capitales = new HashMap<>();
        // Agregar las coordenadas de las capitales de las provincias de Argentina
        agregarCoordenadas();
    }

    private void agregarCoordenadas() {
    	
        // Coordenadas de las capitales de las provincias de Argentina
        this.capitales.put("Buenos Aires", new Coordenada(-34.6037, -58.3816));
        this.capitales.put("Catamarca", new Coordenada(-28.4696, -65.7852));
        this.capitales.put("Chaco", new Coordenada(-27.4512, -58.9866));
        this.capitales.put("Chubut", new Coordenada(-43.2987, -65.1004));
        this.capitales.put("Córdoba", new Coordenada(-31.4201, -64.1888));
        this.capitales.put("Corrientes", new Coordenada(-27.4691, -58.8309));
        this.capitales.put("Entre Ríos", new Coordenada(-31.7747, -60.4959));
        this.capitales.put("Formosa", new Coordenada(-26.1775, -58.1781));
        this.capitales.put("Jujuy", new Coordenada(-24.1855, -65.2995));
        this.capitales.put("La Pampa", new Coordenada(-36.6141, -64.2833));
        this.capitales.put("La Rioja", new Coordenada(-29.4131, -66.8558));
        this.capitales.put("Mendoza", new Coordenada(-32.8895, -68.8458));
        this.capitales.put("Misiones", new Coordenada(-27.3661, -55.8961));
        this.capitales.put("Neuquén", new Coordenada(-38.9516, -68.0591));
        this.capitales.put("Río Negro", new Coordenada(-40.8135, -62.9967));
        this.capitales.put("Salta", new Coordenada(-24.7829, -65.4128));
        this.capitales.put("San Juan", new Coordenada(-31.5375, -68.5364));
        this.capitales.put("San Luis", new Coordenada(-33.3017, -66.3378));
        this.capitales.put("Santa Cruz", new Coordenada(-51.6226, -69.2181));
        this.capitales.put("Santa Fe", new Coordenada(-31.6107, -60.6973));
        this.capitales.put("Santiago del Estero", new Coordenada(-27.7951, -64.2615));
        this.capitales.put("Tierra del Fuego", new Coordenada(-54.8019, -68.302));
        this.capitales.put("Tucumán", new Coordenada(-26.8083, -65.2176));
        this.capitales.put("Islas Malvinas", new Coordenada(-51.7963, -59.5236));

        
    	
        
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
}

