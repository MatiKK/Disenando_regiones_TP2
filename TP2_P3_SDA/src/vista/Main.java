package vista;//

import controlador.Controlador;

import logica.Provincia;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends JFrame {

	private static final long serialVersionUID = 7776727856633335088L;

	private Controlador controlador;
	private JButton showGraphButton;
	private JButton exitButton;
	private JMapViewer mapViewer;
	private JTextField valorPesoEntradaUser;
	JComboBox<Provincia> comboBox1;
	JComboBox<Provincia> comboBox2;
	private JButton quitarAristasAGM;
	private boolean agmEnPantalla;

	public Main() {
		agmEnPantalla = false;
		mapViewer = new JMapViewer();
		controlador = new Controlador(this, true);
		initializeUI();
	}

	private void initializeUI() {
		setTitle("Diseño de Regiones de un País");
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel();
		JPanel mapPanel = new JPanel(new BorderLayout());
		showGraphButton = new JButton("Ver Mapa con Grafo");
		exitButton = new JButton("Salir");

		// Mapa
		mapViewer.setZoom(5);
		mapViewer.setTileSource(new org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource.Mapnik());
		mapViewer.setDisplayPosition(new Coordinate(-40.6037, -65.3816), 4);
		mapViewer.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 2) {
					String nombreProvincia;
//					nombreProvincia = "test";
					nombreProvincia = JOptionPane.showInputDialog("Ingrese nombre de provincia:");
					if (nombreProvincia == null)
						return;
					Point punto = e.getPoint();
					Coordinate c = (Coordinate) mapViewer.getPosition(punto);
					Provincia prov = new Provincia(nombreProvincia, c);
					controlador.nuevaProvincia(prov);
					comboBox1.addItem(prov);
					comboBox1.setSelectedItem(prov);
					actualizarComboBox2();
					quitarAristasAGM.setVisible(false);
					if (agmEnPantalla)
						controlador.mostrarMapaConGrafo();
					agmEnPantalla = false;
				}
			}
		});

		mapPanel.add(mapViewer, BorderLayout.CENTER);

		comboBox1 = new JComboBox<>();
		buttonPanel.add(comboBox1);
		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarComboBox2();
			}
		});

		comboBox2 = new JComboBox<>();
		buttonPanel.add(comboBox2);

		JLabel lblNewLabel_2 = new JLabel("Indique peso:");
		buttonPanel.add(lblNewLabel_2);

		valorPesoEntradaUser = new JTextField();
		buttonPanel.add(valorPesoEntradaUser);
		valorPesoEntradaUser.setColumns(10);

		// AGREGO PESO A RELACION AL MAPA
		JButton btnAgregarRelacion = new JButton("Agregar relación");
		buttonPanel.add(btnAgregarRelacion);

		btnAgregarRelacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double peso = Double.valueOf(valorPesoEntradaUser.getText());
					Provincia p1 = (Provincia) comboBox1.getSelectedItem();
					Provincia p2 = (Provincia) comboBox2.getSelectedItem();
					controlador.nuevaArista(p1, p2, peso);
				} catch (NumberFormatException err) {
					mostrarAlerta("Error: no ingresó un valor numérico");
				} finally {
					valorPesoEntradaUser.setText(null);
				}
			}
		});

		showGraphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarMapaConGrafo();
			}
		});
		buttonPanel.add(showGraphButton);

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

		quitarAristasAGM = new JButton("Generar regiones");
		quitarAristasAGM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String input = JOptionPane.showInputDialog("¿Cuántas regiones desea mostrar (quitando las aristas más pesadas)?");
					int n = Integer.valueOf(input);
					controlador.quitarAristasDelAGM(n);
				} catch (NumberFormatException ex) {
					mostrarAlerta("No ingresó un número entero");
				}
			}
		});

		JButton btnGenerarAGM = new JButton("Generar AGM");
		btnGenerarAGM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controlador.dibujarAGM()) {
				agmEnPantalla = true;
				quitarAristasAGM.setVisible(true);
				}
			}
		});

		buttonPanel.add(btnGenerarAGM);
		buttonPanel.add(quitarAristasAGM);
		quitarAristasAGM.setVisible(false);

		getContentPane().add(mapPanel, BorderLayout.CENTER);

	}

	// previene que se pueda añadir arista entre mismo vertice
	private void actualizarComboBox2() {
		comboBox2.removeAllItems();
		for (int i = 0; i < comboBox1.getItemCount(); i++) {
			Provincia p = (Provincia) comboBox1.getItemAt(i);
			if (!p.equals(comboBox1.getSelectedItem()))
				comboBox2.addItem(p);
		}
	}

	public JMapViewer getMapViewer() {
		return mapViewer;
	}

	public void mostrarAlerta(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
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
