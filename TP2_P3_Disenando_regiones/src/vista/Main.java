package vista;

import controlador.Controlador;
import logica.CoordenadasProvinciasArgentina;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private Controlador controlador;
    private JButton showGraphButton;
    private JButton exitButton;
    private JMapViewer mapViewer;

    public Main() {
        controlador = new Controlador(this, new CoordenadasProvinciasArgentina());
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Diseño de Regiones de un País");
        setSize(1200, 800);
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
//        mapViewer.setDisplayPosition(new Coordinate(-34.6037, -58.3816), 10); // Posición inicial (Buenos Aires)
		
        mapPanel.add(mapViewer, BorderLayout.CENTER);
        buttonPanel.add(showGraphButton);
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
