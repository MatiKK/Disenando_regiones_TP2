package vista;//

import controlador.Controlador;
import logica.CoordenadasCapitalesArgentina;
import logica.Provincia;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JFrame {
    private Controlador controlador;
    private JButton showGraphButton;
    private JButton exitButton;
    private JMapViewer mapViewer;
    private JTextField valorPesoEntradaUser;
    

    public Main() {
        controlador = new Controlador(this, new CoordenadasCapitalesArgentina());
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Diseño de Regiones de un País");
        setSize(1600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        JPanel mapPanel = new JPanel(new BorderLayout());
        showGraphButton = new JButton("Ver Mapa con Grafo");
        exitButton = new JButton("Salir");

        // Mapa
        mapViewer = new JMapViewer();
        mapViewer.setZoom(5);
        mapViewer.setTileSource(new org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource.Mapnik());
        mapViewer.setDisplayPosition(new Coordinate(-40.6037, -65.3816), 4);

        mapPanel.add(mapViewer, BorderLayout.CENTER);
        
        System.out.println("resultado de ver getcapitales: " + controlador.getListaDePronvincias().getCapitales());
        
        ArrayList<String> listaCombo = new ArrayList<String>();
        listaCombo = controlador.getListaDePronvincias().getNombreProvincias();
        
        String[] opciones = listaCombo.toArray(new String[1]);
        
        JLabel lblNewLabel = new JLabel("Seleccione provincias a enlazar:");
        buttonPanel.add(lblNewLabel);
        
        
        
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel<>(opciones));
        //comboBox.setModel(new DefaultComboBoxModel(new String[] listaCombo));
        
        comboBox.setToolTipText("Seleccionar Provincia");
        buttonPanel.add(comboBox);

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seleccionProv1 = (String) comboBox.getSelectedItem();
                System.out.println("Elemento seleccionado: " + seleccionProv1);
                // Puedes realizar otras acciones con el elemento seleccionado aquí
            }
        });
        
        //puedo tomar la opcion elegida
        //String provinciaComboBox1 = (String)comboBox.getSelectedItem();
        String[] provinciaComboBox1 = new String[1];
        System.out.println("el valor del provincia comboBox1 es: " + provinciaComboBox1);
        
        
        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setToolTipText("Seleccionar Provincia");
        buttonPanel.add(comboBox_1);
        
        comboBox_1.setModel(new DefaultComboBoxModel<>(opciones));
        
        comboBox_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seleccionProv2 = (String) comboBox_1.getSelectedItem();
                System.out.println("Elemento seleccionado: " + seleccionProv2);
                
                //provinciaComboBox1 = seleccion1;
                
            }
        });
        
        String provinciaComboBox2 = (String)comboBox_1.getSelectedItem();
        System.out.println("el valor del provincia comboBox2 es: " + provinciaComboBox2);
        
        
        
        
        
        //comboBox.setModel(new DefaultComboBoxModel(String [] controlador.getListaDePronvincias().getNombreProvincias());

        
        
        
        JLabel lblNewLabel_2 = new JLabel("Indique peso:");
        buttonPanel.add(lblNewLabel_2);
        
        valorPesoEntradaUser = new JTextField();
        buttonPanel.add(valorPesoEntradaUser);
        valorPesoEntradaUser.setColumns(10);
        
        String valorPesoIngresado = valorPesoEntradaUser.getText();
        
        JButton btnAgregarRelacion = new JButton("Agregar relación");
        buttonPanel.add(btnAgregarRelacion);
        
        
        btnAgregarRelacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para mostrar el mapa con el grafo a través del controlador
            	
            	System.out.println("los valores son: " + " provincia1: " + comboBox.getSelectedItem() + " prov2: " + comboBox_1.getSelectedItem() + " peso: " + valorPesoEntradaUser.getText()) ;
            	
            	controlador.agregarPesoEnRelacion(comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString(), valorPesoEntradaUser.getText());
            }
        });
        
        
        
        showGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para mostrar el mapa con el grafo a través del controlador
                controlador.mostrarMapaConGrafo();
            }
        });
        
        
        
        
        
        JLabel lblNewLabel_1 = new JLabel("          ");
        buttonPanel.add(lblNewLabel_1);
        buttonPanel.add(showGraphButton);
        
        
        
        
        
        
        
        JButton testAgregoEnlace = new JButton("Agregar Limitrofes");
        buttonPanel.add(testAgregoEnlace);
        
        
        testAgregoEnlace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la aplicación
            	controlador.agregarEnlacesDeProvincias();
            }
        });
        buttonPanel.add(exitButton);

        showGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para mostrar el mapa con el grafo a través del controlador
                controlador.mostrarMapaConGrafo();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la aplicación
                System.exit(0);
            }
        });
        
        

        // Configurar el diseño de la ventana
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(mapPanel, BorderLayout.CENTER);
        
        
        
        
        
    }

    public JMapViewer getMapViewer() {
        return mapViewer;
    }
    
    public void graficarEnlaces(Coordinate coordenadaOrigen, Coordinate coordenadaDestino, String tex) {
        // Crea un objeto MapPolygonImpl con las coordenadas de origen y destino
        MapPolygonImpl linea = new MapPolygonImpl(coordenadaOrigen, coordenadaDestino,coordenadaOrigen);

        // Agrega la línea al mapa
        mapViewer.addMapPolygon(linea);
        //controlador.mostrarMapaConGrafo();
        
        Coordinate CordIntermedia = Controlador.encontrarCoordenadaIntermedia(coordenadaOrigen, coordenadaDestino);
        int pesoEnInt = Integer.parseInt(tex);
        
        if (pesoEnInt != 0) {
        	MapMarkerDot markerDot = new MapMarkerDot(tex, CordIntermedia);
            markerDot.setBackColor(Color.RED);
            Font font = new Font("Arial", Font.BOLD, 18); 
            markerDot.setFont(font);
            
            mapViewer.addMapMarker(markerDot);
        }else {
        	System.out.println("estoy agregando valor 0");
        }

    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setVisible(true);
            }
        });
    }
}
