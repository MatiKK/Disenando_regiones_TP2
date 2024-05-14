package vista;//

import controlador.Controlador;
import logica.Provincia;
import logica.ProvinciasArgentinas;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JFrame frameParaElegirRelacion;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Main frame = new Main();
				frame.setVisible(true);
			}
		});
	}

	public Main() {
		agmEnPantalla = false;
		mapViewer = new JMapViewer();
		controlador = new Controlador(this);
		initializeUI();
	}

	private void agregarProvinciasArgentinas() {
		for (Provincia p: ProvinciasArgentinas.provinciasDeArgentina())
			nuevaProvincia(p);
	}
	private void agregarPesosAleatorios() {
		controlador.provinciasArgentinasPesosAleatorios();
	}
	

	private void initializeUI() {
		setTitle("Diseño de Regiones de un País");
//		setSize(1000, 500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel();
		JPanel mapPanel = new JPanel(new BorderLayout());
		showGraphButton = new JButton("Grafo original");
		exitButton = new JButton("Salir");

		// Mapa
		mapViewer.setZoom(5);
		mapViewer.setTileSource(new org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource.Mapnik());
		mapViewer.setDisplayPosition(new Coordinate(-40.6037, -65.3816), 4);
		mapViewer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 2) {
					String nombreProvincia;
					nombreProvincia = JOptionPane.showInputDialog("Ingrese nombre de provincia:");
					if (nombreProvincia == null)
						return;
					Point punto = e.getPoint();
					Coordinate c = (Coordinate) mapViewer.getPosition(punto);
					Provincia prov = new Provincia(nombreProvincia, c);
					nuevaProvincia(prov);
				}
			}
		});

		mapPanel.add(mapViewer, BorderLayout.CENTER);
		
		JLabel labelAyuda = new JLabel("Doble clic derecho para una nueva locación");
		buttonPanel.add(labelAyuda);
		labelAyuda.setHorizontalAlignment(SwingConstants.LEFT);

		comboBox1 = new JComboBox<>();
		buttonPanel.add(comboBox1);
		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarComboBox2();
			}
		});

		comboBox2 = new JComboBox<>();
		buttonPanel.add(comboBox2);

		// AGREGO PESO A RELACION AL MAPA
		JButton btnAgregarRelacion = new JButton("Agregar relación");
		buttonPanel.add(btnAgregarRelacion);

		btnAgregarRelacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cantidadProvincias() == 0)
					mostrarAlerta("Aún no hay provincias disponibles.");
				else {
					frameParaElegirRelacion.setVisible(true);
				}
			}
		});

		showGraphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarMapaConGrafo();
				quitarAristasAGM.setVisible(false);
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
					quitarAristasAGM.setVisible(false);
				} catch (NumberFormatException ex) {
					mostrarAlerta("No ingresó un número entero");
				}
			}
		});

		JButton btnGenerarAGM = new JButton("AGM");
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

		crearFrameAgregarRelacion();

		JButton cargarDatosArgentina = new JButton("Provincias Argentinas");
		JButton cargarLimitrofesAleatoriosArgentina = new JButton("Relaciones aleatorias Provincias Argentinas");
		buttonPanel.add(cargarLimitrofesAleatoriosArgentina);
		buttonPanel.add(cargarDatosArgentina);
		cargarLimitrofesAleatoriosArgentina.setVisible(false);

		cargarDatosArgentina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProvinciasArgentinas();
				cargarLimitrofesAleatoriosArgentina.setVisible(true);
				buttonPanel.remove(cargarDatosArgentina);
				buttonPanel.repaint();
			}
		});

		cargarLimitrofesAleatoriosArgentina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarPesosAleatorios();
				buttonPanel.remove(cargarLimitrofesAleatoriosArgentina);
				buttonPanel.repaint();
			}
		});

		
		
		JButton grafoCompleto = new JButton("Grafo completo aleatorio");
		buttonPanel.add(grafoCompleto);
		grafoCompleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cantidadProvincias() == 0) {
					mostrarAlerta("Aún no hay provincias disponibles.");
					return;
				}
				controlador.grafoCompletoAristasAleatorias();
				quitarAristasAGM.setVisible(false);
			}
		});

		JButton quitarPuntos = new JButton("Borrar todo");
		buttonPanel.add(quitarPuntos);
		quitarPuntos .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarTodosLosPuntos();
				buttonPanel.add(cargarDatosArgentina);
				buttonPanel.add(cargarLimitrofesAleatoriosArgentina);
				cargarLimitrofesAleatoriosArgentina.setVisible(false);
				repaint();
			}
		});
		
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

	private void crearFrameAgregarRelacion() {
		frameParaElegirRelacion = new JFrame();
		JPanel panel = new JPanel();
		panel.add(comboBox1);
		panel.add(comboBox2);
		JLabel lblNewLabel_2 = new JLabel("       Indique peso:");
		panel.add(lblNewLabel_2);
		valorPesoEntradaUser = new JTextField();
		panel.add(valorPesoEntradaUser);
		valorPesoEntradaUser.setColumns(10);

		JButton cargarRelacion = new JButton("Cargar relación");
		panel.add(cargarRelacion);
		cargarRelacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarNuevaArista();				
			}
		});
		frameParaElegirRelacion.setBounds(100,100,550,200);
		frameParaElegirRelacion.add(panel);
		frameParaElegirRelacion.setVisible(false);
		
	}

	private void quitarTodosLosPuntos() {
		controlador.quitarTodosLosPuntos();
		mapViewer.removeAllMapMarkers();
		mapViewer.removeAllMapPolygons();
		comboBox1.removeAllItems();
		actualizarComboBox2();
	}

	private int cantidadProvincias() {
		return comboBox1.getItemCount();
	}

	public JMapViewer getMapViewer() {
		return mapViewer;
	}

	public void mostrarAlerta(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	private void nuevaProvincia(Provincia prov) {
		controlador.agregarNuevaProvincia(prov);
		comboBox1.addItem(prov);
		comboBox1.setSelectedItem(prov);
		actualizarComboBox2();
		quitarAristasAGM.setVisible(false);
		if (agmEnPantalla)
			controlador.mostrarMapaConGrafo();
		agmEnPantalla = false;
	}

	private void cargarNuevaArista() {
		try {
			if (agmEnPantalla) {
				controlador.mostrarMapaConGrafo();
			}
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

}