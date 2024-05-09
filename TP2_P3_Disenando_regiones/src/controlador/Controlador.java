package controlador;

import vista.Main;
import logica.Arista;
import logica.CoordenadasProvinciasArgentina;
import logica.Provincia;
import logica.ProvinciasArgentinas;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Controlador {

    private Main vista;
    private Arista conexionProvincia;

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
    				conexionProvincia = new Arista(p1.coordenadas(),p2.coordenadas(),100);
    				provincias[i].agregarAristaDeProvinciasLimitrofes(conexionProvincia);
    				System.out.println("a la provincia: " + provincias[i].toString() + " con coordenada: " + provincias[i].coordenadas());
    				System.out.println("AGREGUE ARISTA: COORD X" + conexionProvincia.getOrigen() + " coord Y"+  conexionProvincia.getDestino());
    				//escribirPesoEntreProvincias(mapa,provincias[i], p2);
    				//System.out.println("-------------" + provincias[i].getAristasProvLimitrofes().get(0));
    				pintarLineaEntreProvincias(mapa, p1, p2);
    				
    				int pesoRandom = generarNumeroRandom();
    				System.out.println("agrego enlace con peso: " + pesoRandom);
    			}
    			
    		}
    		escribirPesoEntreProvincias2(mapa,provincias[i]);
    		
    		System.out.println(provincias[i].getAristasProvLimitrofes());
    		
    	}
    	
    }
    
    public static int generarNumeroRandom() {
        Random random = new Random();
        int[] opciones = {100, 200, 300};
        int indice = random.nextInt(opciones.length);
        return opciones[indice];
    }


	private void pintarPuntoEnProvincia(JMapViewer mapa, Provincia p) {
    	mapa.addMapMarker(new MapMarkerDot(
//    			p.toString(), // ver si se puede hacer la letra mas chica
    			
    			//p.getAristasProvLimitrofes();
    			
    			p.coordenadas()));
    }

	
	
    private void pintarLineaEntreProvincias(JMapViewer mapa, Provincia p1, Provincia p2) {
    	mapa.addMapPolygon(new MapPolygonImpl(p1.coordenadas(), p2.coordenadas(),p1.coordenadas()));
    	
    	/*ArrayList<Arista> listaDeAristaDeConexionesConProvincia = new ArrayList<Arista>();
    	
    	listaDeAristaDeConexionesConProvincia = p1.getAristasProvLimitrofes();

    	for (int i = 0; i > listaDeAristaDeConexionesConProvincia.size(); i++) {
    		int PesoDeEnlace = listaDeAristaDeConexionesConProvincia.get(i).getPeso();
    		
    		Coordinate cordenadaIntermedia;
    		
    		String PesoString = String.valueOf(PesoDeEnlace);
    		cordenadaIntermedia = encontrarCoordenadaIntermedia(p1.coordenadas(),p2.coordenadas());
    		
    		MapMarkerDot markerDot = new MapMarkerDot(cordenadaIntermedia);
            markerDot.setText("ejemplo");
    		
            mapa.addMapMarker(markerDot);
		}*/
    	
    	
    	
    }
    
    
	private void escribirPesoEntreProvincias(JMapViewer mapa, Provincia p1, Provincia p2) {
		
	 Coordinate coordenadaIntermedia = encontrarCoordenadaIntermedia(p1.coordenadas(), p2.coordenadas());
	 mapa.addMapMarker(new MapMarkerDot (coordenadaIntermedia));   

	    ArrayList<Arista> listaDeAristaDeConexionesConProvincia = p1.getAristasProvLimitrofes();
	    System.out.println("estoy afuera del for");
	    
	    int test = listaDeAristaDeConexionesConProvincia.size();
	    
	    System.out.println("el valor de test es :" + test);
	    
	    for (int i = 0; i < listaDeAristaDeConexionesConProvincia.size(); i++) {
	    	System.out.println("XXXXXXXXXXX---------entre al for");
	        String pesoDeEnlace = listaDeAristaDeConexionesConProvincia.get(i).toStringPeso();
	        
	        System.out.println("eeeeeeeeeeeel peso es : " + pesoDeEnlace);
	        
	        mapa.addMapMarker(new MapMarkerDot (pesoDeEnlace,coordenadaIntermedia));
	        
	        //markerDot.setText(String.valueOf(pesoDeEnlace)); // Texto personalizado con el peso
	        
	        //listaDeAristaDeConexionesConProvincia.get(i).toStringPeso(),
	        //coordenadaIntermedia);
	        System.out.println("intentando graficar peso: " + listaDeAristaDeConexionesConProvincia.get(i).toStringPeso());
	        //markerDot.setBackColor(Color.RED); // Color de fondo opcional
	        //markerDot.setForeColor(Color.WHITE); // Color del texto opcional

	        
	    }
	}
	
	
	private void escribirPesoEntreProvincias2(JMapViewer mapa, Provincia p1) {
		
		 //Coordinate coordenadaIntermedia = encontrarCoordenadaIntermedia(p1.coordenadas(), p2.coordenadas());
		
		
		
		
		  

		    ArrayList<Arista> listaDeAristaDeConexionesConProvincia = p1.getAristasProvLimitrofes();
		    System.out.println("estoy afuera del for");
		    
		    int test = listaDeAristaDeConexionesConProvincia.size();
		    
		    for (int i = 0; i > listaDeAristaDeConexionesConProvincia.size(); i++) {
				System.out.println("ENTREEEEEE AL FOOOOOOR");
				Coordinate coordenadaIntermedia = encontrarCoordenadaIntermedia(p1.coordenadas(), listaDeAristaDeConexionesConProvincia.get(i).getDestino());
				mapa.addMapMarker(new MapMarkerDot (coordenadaIntermedia));  
			}
		    //Coordinate coordenadaIntermedia = encontrarCoordenadaIntermedia(p1.coordenadas(), p2.coordenadas());
		    //mapa.addMapMarker(new MapMarkerDot (coordenadaIntermedia));  

		    
		}
	
	


		


    
    
    
    public static Coordinate encontrarCoordenadaIntermedia(Coordinate coordenada1, Coordinate coordenada2) {
        double latitudIntermedia = (coordenada1.getLat() + coordenada2.getLat()) / 2.0;
        double longitudIntermedia = (coordenada1.getLon() + coordenada2.getLon()) / 2.0;
        return new Coordinate(latitudIntermedia, longitudIntermedia);
    }
    

}
