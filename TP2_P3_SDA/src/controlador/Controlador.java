package controlador;

import vista.Main;
import logica.Arista;
import logica.CoordenadasCapitalesArgentina;
import logica.CoordenadasCapitalesArgentina.Coordenada;
import logica.Provincia;
import logica.ProvinciasArgentinas;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Controlador {
    private Main vista;
    private CoordenadasCapitalesArgentina listaDeProvincias;
    private ArrayList<Arista> ListaDeEnlacesEntreProvincias;
    
    private int countLlamadosADibujarEnlaces = 0;
    

    public Controlador(Main vista, CoordenadasCapitalesArgentina modelo) {
        this.vista = vista;
        this.listaDeProvincias = modelo;
        
        this.ListaDeEnlacesEntreProvincias = new ArrayList<Arista>();
    }

    public void mostrarMapaConGrafo() {
        // Obtener las coordenadas de las capitales de Argentina
        Map<String, CoordenadasCapitalesArgentina.Coordenada> coordenadasMap = listaDeProvincias.getCapitales();

        // Agregar los puntos al mapa
        for (Map.Entry<String, CoordenadasCapitalesArgentina.Coordenada> entry : coordenadasMap.entrySet()) {
            CoordenadasCapitalesArgentina.Coordenada coord = entry.getValue();
            vista.getMapViewer().addMapMarker(new MapMarkerDot(coord.getLatitud(), coord.getLongitud()));
        }
        
        agregarEnlacesDeProvincias();
        dibujarEnlaces(vista.getMapViewer(),ListaDeEnlacesEntreProvincias);
        

        // Establecer el color de los marcadores en rojo
        vista.getMapViewer().setMapMarkerVisible(true);
        
        retornarStringListaDeEnlaces();
    }
    
    public void dibujarEnlaces(JMapViewer mapa , ArrayList<Arista> ListaDeEnlacesEntreProvincias) {

    	System.out.println(ListaDeEnlacesEntreProvincias);
    	if (ListaDeEnlacesEntreProvincias.size() > 0) {
    		System.out.println("Entre a dibujar enlaces pero el tamaño de ListaDeEnalaces es: " + ListaDeEnlacesEntreProvincias.size());
    		
    		for (int i = 0; i < ListaDeEnlacesEntreProvincias.size(); i++) {
    			countLlamadosADibujarEnlaces++;
    			System.out.println("cantidad de llamadooooos: " + countLlamadosADibujarEnlaces);
    			Coordinate coordenadaOrigen = ListaDeEnlacesEntreProvincias.get(i).getOrigen();
    			Coordinate coordenadaDestino = ListaDeEnlacesEntreProvincias.get(i).getDestino();
    			System.out.println("Entrereee al foooor");
    			
    			String pesoEnTexto = ListaDeEnlacesEntreProvincias.get(i).toStringPeso();

    	        vista.graficarEnlaces(coordenadaOrigen, coordenadaDestino,pesoEnTexto);
    	        
			}
    	}
    			
    }
    
    
    public void agregarEnlacesDeProvincias() {

    	Provincia[] provincias = ProvinciasArgentinas.provinciasDeArgentina();
    	int cantProvincias = provincias.length;
    	boolean[][] relacionLimitrofe = ProvinciasArgentinas.relacionDeProvinciasLimitrofes;

    	for (int i = 0; i < cantProvincias; i++) {
    		Provincia p1 = provincias[i];
    		
    		for (int j = i + 1; j < cantProvincias; j++) {
    			Provincia p2 = provincias[j];
    			boolean provinciasLimitrofes = relacionLimitrofe[i][j];
    			System.out.println(p1 + " limitrofe con "+p2+"? " + provinciasLimitrofes);
    			if (provinciasLimitrofes) {			
    		    	
    		    	Arista NuevaArista = new Arista(p1.coordenadas(),p2.coordenadas()); 
    		    	
    		    	ListaDeEnlacesEntreProvincias.add(NuevaArista);
    				
    	
    			}
    		}
    		
    	}
    }
    	
    	
    	
    	
    	
    	
    
    
    
    
    private void pintarLineaEntreProvincias(JMapViewer mapa, Provincia p1, Provincia p2) {
    	mapa.addMapPolygon(new MapPolygonImpl(p1.coordenadas(), p2.coordenadas(),p1.coordenadas()));
    }
    
    public String retornarStringListaDeEnlaces() {
    	
    	String listaFinal = "";
    	
    	for (int i = 0; i < ListaDeEnlacesEntreProvincias.size(); i++) {
    		listaFinal = listaFinal + " ||| " + ListaDeEnlacesEntreProvincias.get(i).toString();
		}
    	
    	return listaFinal;
    	
    	
    }
    
    public static Coordinate encontrarCoordenadaIntermedia(Coordinate coordenada1, Coordinate coordenada2) {
        double latitudIntermedia = (coordenada1.getLat() + coordenada2.getLat()) / 2.0;
        double longitudIntermedia = (coordenada1.getLon() + coordenada2.getLon()) / 2.0;
        return new Coordinate(latitudIntermedia, longitudIntermedia);
    }
    
    public CoordenadasCapitalesArgentina getListaDePronvincias() {
    	return listaDeProvincias;
    }

	public void agregarPesoEnRelacion(String provinciaComboBox1, String provinciaComboBox2, String valorPesoIngresado) {
		
		Coordenada cordenadaProvincia1 = listaDeProvincias.getCapitales().get(provinciaComboBox1);
		Coordenada cordenadaProvincia2 = listaDeProvincias.getCapitales().get(provinciaComboBox2);
		
		
		double latitudCordenadaProvincia1 = cordenadaProvincia1.getLatitud();
		double longitudCordenadaProvincia1 = cordenadaProvincia1.getLongitud();
		
		double latitudCordenadaProvincia2 = cordenadaProvincia2.getLatitud();
		double longitudCordenadaProvincia2 = cordenadaProvincia2.getLongitud();
		
		
		for (int i = 0; i < ListaDeEnlacesEntreProvincias.size(); i++) {
			System.out.println("ENTRE A LA LISTA DE ENLACES ENTRE PROVINCIAS");
			double latitudCoordinateOrigenListaDeEnlacesPronvincia1 = ListaDeEnlacesEntreProvincias.get(i).getOrigen().getLat(); 		
			double longitudCoordinateOrigenListaDeEnlacesPronvincia1 = ListaDeEnlacesEntreProvincias.get(i).getOrigen().getLon();
			
			
			if (latitudCoordinateOrigenListaDeEnlacesPronvincia1 == latitudCordenadaProvincia1 && longitudCordenadaProvincia1 == longitudCoordinateOrigenListaDeEnlacesPronvincia1) {
				//comprobando la coordenada de la segunda pronvincia
				double latitudCoordinateDestinoListaDeEnlacesPronvincia1 = ListaDeEnlacesEntreProvincias.get(i).getDestino().getLat(); 		
				double longitudCoordinateDestinoListaDeEnlacesPronvincia1 = ListaDeEnlacesEntreProvincias.get(i).getDestino().getLon();
				
				System.out.println("Comprobe que tengo una coordenada origen igual con la provincia: " + provinciaComboBox1 + 
				" con latitud: " + latitudCoordinateDestinoListaDeEnlacesPronvincia1 +  
				" y longitud: " + longitudCoordinateDestinoListaDeEnlacesPronvincia1);
				
				
				System.out.println("estoy validando: " + provinciaComboBox2);
				
				System.out.println("estoy comparando estas latitudes: " + latitudCoordinateDestinoListaDeEnlacesPronvincia1 + " y: " + latitudCordenadaProvincia2);
				
				
				
				System.out.println("estoy comparando estas longitudes: " + longitudCoordinateDestinoListaDeEnlacesPronvincia1 + " y: " + longitudCordenadaProvincia2);
				
				System.out.println("######################");
				
				if (latitudCoordinateDestinoListaDeEnlacesPronvincia1 == latitudCordenadaProvincia2 && longitudCoordinateDestinoListaDeEnlacesPronvincia1 == longitudCordenadaProvincia2) {
					//AHORA COMO LA COORDENADA EXISTE PUEDO AGREGAR PESO
					System.out.println("Comprobe que tengo una coordenada destino igual");
					int PesoEnNumero = Integer.valueOf(valorPesoIngresado);
					
					agregarPesoEnRelacionEntrePronvincias(ListaDeEnlacesEntreProvincias.get(i),PesoEnNumero);
						
					
					
					
				}
				
				
				
			}
			
			
		}
		
	}

	private void agregarPesoEnRelacionEntrePronvincias(Arista arista, int pesoEnNumero) {
		
		arista.actualizarPeso(pesoEnNumero);
		System.out.println("Entre a intentar agergar el peso en el mapa");
		dibujarEnlaces(vista.getMapViewer(),ListaDeEnlacesEntreProvincias);
		
	}

    
    
}
