package vista;

import java.awt.*; // Importa clases para diseño y gráficos
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*; // Importa componentes de Swing
import javax.swing.border.*; // Importa bordes

import controlador.Main;
import modelo.Dificultad;
import modelo.Tablero;
import modelo.Usuario;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;

	// Declaración de paneles y componentes
	private Map<String, ImageIcon> imagenesCacheadas = new HashMap<>();
	private JPanel contentPanel;
	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JPanel panelMinas;
	private JPanel panelReset;
	private JPanel panelTemporizador;
	private JLabel decenasMinas;
	private JLabel unidadesMinas;
	private JLabel temporizadorUd;
	private JLabel temporizadorDecenas;
	private JLabel temporizadorCentenas;
	private JButton reset;
	private int filas;
	private int columnas;
	private int minasRestantes;
	private int tiempo = 0;
	private GridBagConstraints gbc;
	private Color bordes = new Color(134, 96, 67); // Color personalizado
	private JButton[][] tableroInterfaz;
	private Tablero tablero;
	private Timer timer;
	public boolean partidaPerdida = false;
	private boolean partidaGanada = false;
	private Usuario user;
	private int celdasRestantes;
	private Dificultad dificultad;

	// Constructor de la ventana de juego
	public VentanaJuego(Usuario user, Dificultad dificultad) {
		// Carga el nombre de usuario
		this.user = user;
		this.dificultad = dificultad;

		// Precarga todas las imagenes
		cargarImagenes();
		tablero = new Tablero(dificultad); // Inicializa la lógica del tablero
		setTitle("Juego Buscaminas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);

		this.minasRestantes = dificultad.getminas(); // Número de minas
		temporizador();
		// Panel principal
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(bordes, 4));
		setContentPane(contentPanel);

		// Panel superior
		panelSuperior = new JPanel();
		panelSuperior.setPreferredSize(new Dimension(200, 50));
		panelSuperior.setBackground(bordes); // Todo marrón
		panelSuperior.setLayout(new BorderLayout());
		panelSuperior.setBorder(new LineBorder(bordes, 4));

		// Panel minas restantes
		panelMinas = new JPanel();
		panelMinas.setLayout(new GridLayout(1, 2));
		panelMinas.setBackground(bordes); // Fondo marrón
		panelMinas.setBorder(null); // Sin borde
		panelSuperior.add(panelMinas, BorderLayout.WEST);

		int unidades = this.minasRestantes % 10;
		int decenas = this.minasRestantes / 10;

		decenasMinas = new JLabel();
		actualizarContador(decenasMinas, decenas);
		panelMinas.add(decenasMinas);

		unidadesMinas = new JLabel();
		actualizarContador(unidadesMinas, unidades);
		panelMinas.add(unidadesMinas);

		// Panel reset
		panelReset = new JPanel();
		panelReset.setBackground(bordes); // Fondo marrón
		panelReset.setBorder(null);
		panelReset.setLayout(new GridBagLayout()); // Centrado del botón

		reset = new JButton();
		ImageIcon iconoReset = new ImageIcon("src/imagenes/reset.gif");
		Image image = iconoReset.getImage();
		Image scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		iconoReset = new ImageIcon(scaledImage);
		reset.setIcon(iconoReset);

		reset.setContentAreaFilled(false);
		reset.setFocusPainted(false);
		reset.setOpaque(false);
		reset.setBorder(new MatteBorder(2, 2, 2, 2, new Color(155, 155, 155))); // Borde para distinguir el botón de
																				// reset
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Reseteo del panel inferior al completo
				panelInferior.removeAll();
				panelInferior.setLayout(new GridBagLayout());
				gbc = generarEstructuraTablero();

				// Reinicio de la variable partida perdida
				partidaPerdida = false;
				partidaGanada = false;
				celdasRestantes = dificultad.getFilas() * dificultad.getColumnas();
				// Reinicio del timer
				timer.start();

				// Reseteo de la logica del tablero
				tablero = new Tablero(dificultad);
				generarTableroMinas();
				// Reset del contador de minas
				minasRestantes = dificultad.getminas();
				int unidades = minasRestantes % 10;
				int decenas = minasRestantes / 10;
				actualizarContador(unidadesMinas, unidades);
				actualizarContador(decenasMinas, decenas);

				// Reset del contador del tiempo
				tiempo = 0;
				int unidad = tiempo % 10;
				int decena = (tiempo / 10) % 10;
				int centena = tiempo / 100;

				actualizarContador(temporizadorUd, unidad);
				actualizarContador(temporizadorDecenas, decena);
				actualizarContador(temporizadorCentenas, centena);

				contentPanel.revalidate();
				contentPanel.repaint();
			}
		});

		panelReset.add(reset); // Agrega el botón reset al panel
		panelSuperior.add(panelReset, BorderLayout.CENTER); // Ahora agregas el panelReset

		// Panel temporizador
		panelTemporizador = new JPanel();
		panelTemporizador.setLayout(new GridLayout(1, 3));
		panelTemporizador.setBackground(bordes); // Fondo marrón
		panelTemporizador.setBorder(null);
		panelSuperior.add(panelTemporizador, BorderLayout.EAST);

		int unidad = tiempo % 10;
		int decena = (tiempo / 10) % 10;
		int centena = tiempo % 100;

		temporizadorCentenas = new JLabel();
		actualizarContador(temporizadorCentenas, centena);
		panelTemporizador.add(temporizadorCentenas);

		temporizadorDecenas = new JLabel();
		actualizarContador(temporizadorDecenas, decena);
		panelTemporizador.add(temporizadorDecenas);

		temporizadorUd = new JLabel();
		actualizarContador(temporizadorUd, unidad);
		panelTemporizador.add(temporizadorUd);

		// Panel inferior (tablero)
		panelInferior = new JPanel();
		panelInferior.setLayout(new GridBagLayout());

		contentPanel.add(panelSuperior, BorderLayout.NORTH);
		contentPanel.add(panelInferior, BorderLayout.CENTER);

		// Crea tablero
		gbc = generarEstructuraTablero();
		generarTableroMinas();

		// Celdas restantes incia en el máximo
		this.celdasRestantes = this.filas * this.columnas;

		// Hace el panel general visible y agrupa los paneles para que no queden
		// imagenes sueltas
		setVisible(true);
		pack();
	}

	// Inicializa cada botón del tablero
	public void tableroInicial(int i, int j) {
		tableroInterfaz[i][j] = new JButton();
		Color borde = new Color(0, 0, 0);

		// Define los bordes de cada celda según su posición
		if (i == 0 && j == 0) {
			tableroInterfaz[i][j].setBorder(new MatteBorder(1, 1, 1, 1, borde));
		} else if (i == 0) {
			tableroInterfaz[i][j].setBorder(new MatteBorder(1, 0, 1, 1, borde));
		} else if (j == 0) {
			tableroInterfaz[i][j].setBorder(new MatteBorder(0, 1, 1, 1, borde));
		} else {
			tableroInterfaz[i][j].setBorder(new MatteBorder(0, 0, 1, 1, borde));
		}
		actualizarCelda("celda0.gif", i, j); // Pone imagen inicial
	}

	// Genera el tablero visualmente
	public void generarTableroMinas() {
		tablero.generarTablero(); // Genera matriz
		tablero.colocarMinas(tablero.getTablero()); // Coloca minas

		this.filas = tablero.getTablero().length;
		this.columnas = tablero.getTablero()[0].length;
		tableroInterfaz = new JButton[filas][columnas];

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				tableroInicial(i, j);

				final int x = i;
				final int y = j;

				// Añade mouse listener para clic izquierdo o derecho
				tableroInterfaz[i][j].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isLeftMouseButton(e)) {
							Icon icono = tableroInterfaz[x][y].getIcon();
							ImageIcon iconoImg = (ImageIcon) icono;
							String ruta = iconoImg.getDescription();

							if (!ruta.equals("bandera.gif")) {
								int numero = tablero.conseguirNumeroCasilla(tablero.getTablero(), x, y);
								destaparCelda(x, y, numero);
								celdasRestantes--;
								if (celdasRestantes == dificultad.getminas()) {
									comprobarVictoria(tableroInterfaz);
									if (partidaGanada) {
										timer.stop();
										int puntuacion = 1000 - tiempo;
										
										if(dificultad.equals(Dificultad.Intermedio)) {
											puntuacion *= 2;
										} else if (dificultad.equals(Dificultad.Dificil)) {
											puntuacion *= 3;
										}
										
										BufferedWriter out = null;
										try {
											out = new BufferedWriter(new FileWriter("src/clasificacion.txt",true));
											out.write(user.getNombre() + "," + puntuacion);
											out.newLine();
										} catch (IOException e1) {
											e1.printStackTrace();
										} finally {
											try {
												out.close();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
										}
										
										Main.abrirClasificacion(user);
									}
								}
							}
						} else if (SwingUtilities.isRightMouseButton(e)) {
							comprobarBandera(x, y);
						}
					}
				});

				// Mientras se presiona cualquier celda, la cara del reset cambia
				tableroInterfaz[i][j].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						if (!partidaPerdida) {
							actualizarReset("reset2.gif");
						}
					}
				});

				tableroInterfaz[i][j].addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent e) {
						if (!partidaPerdida) {
							actualizarReset("reset.gif");
						}
					}
				});

				// Añade el botón a la cuadrícula
				this.gbc.gridx = j;
				this.gbc.gridy = i + 1;
				panelInferior.add(tableroInterfaz[i][j], gbc);
			}
		}
	}

	// Carga previamente las imagenes para no tener que hacerlo cada vez que se
	// actualizan (optimización)
	private void cargarImagenes() {
		String[] nombres = { "celda0.gif", "bandera.gif", "interrogante.gif", "explotada.gif", "mina.gif", "1Mina.gif",
				"2Mina.gif", "3Mina.gif", "4Mina.gif", "5Mina.gif", "6Mina.gif", "7Mina.gif", "8Mina.gif",
				"sinMina.gif", "reset.gif", "reset2.gif" };

		for (String nombre : nombres) {
			ImageIcon icono = new ImageIcon("src/imagenes/" + nombre);
			Image image = icono.getImage();
			Image scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			imagenesCacheadas.put(nombre, new ImageIcon(scaledImage));
		}
	}

	// Actualiza las imagenes del tablero
	public void actualizarCelda(String fotoSrc, int i, int j) {
		ImageIcon iconLogo = imagenesCacheadas.get(fotoSrc);
		tableroInterfaz[i][j].setIcon(iconLogo);
		iconLogo.setDescription(fotoSrc);
	}

	// Actualiza la imagen del botón de reset
	public void actualizarReset(String fotoSrc) {
		ImageIcon iconLogo = imagenesCacheadas.get(fotoSrc);
		reset.setIcon(iconLogo);
		iconLogo.setDescription(fotoSrc);
	}

	// Destapa una celda dependiendo de su contenido
	public void destaparCelda(int x, int y, int numero) {
		if (tableroInterfaz[x][y].isEnabled()) {
			String fotoSrc;

			// Selecciona imagen en función del número
			if (numero > 0 && numero <= 8) {
				fotoSrc = numero + "Mina.gif";
				actualizarCelda(fotoSrc, x, y);
			} else if (numero == -1) {
				fotoSrc = "explotada.gif"; // Si es una mina, explota
				destaparMinas(x, y);
				return;
			} else {
				actualizarCelda("sinMina.gif", x, y);
			}

			// Deshabilita el botón
			tableroInterfaz[x][y].setDisabledIcon(tableroInterfaz[x][y].getIcon());
			tableroInterfaz[x][y].setEnabled(false);

			// Si no hay minas alrededor, destapa vecinos
			if (numero == 0) {
				int[] calcularPosicionX = { -1, -1, -1, 0, 0, 1, 1, 1 };
				int[] calcularPosicionY = { -1, 0, 1, -1, 1, -1, 0, 1 };

				for (int i = 0; i < 8; i++) {
					int nuevoX = x + calcularPosicionX[i];
					int nuevoY = y + calcularPosicionY[i];

					if (condicionDestaparAgua(nuevoX, nuevoY)) {
						int nuevo = tablero.conseguirNumeroCasilla(tablero.getTablero(), nuevoX, nuevoY);
						if (nuevo >= 0) {
							destaparCelda(nuevoX, nuevoY, nuevo);
							celdasRestantes--;
						}
					}
				}
			}
		}
	}

	// Comprueba si una celda puede ser destapada (está dentro del tablero y no está
	// ya destapada)
	public boolean condicionDestaparAgua(int nuevoX, int nuevoY) {
		return nuevoX >= 0 && nuevoX < tablero.getTablero().length && nuevoY >= 0
				&& nuevoY < tablero.getTablero()[0].length && tableroInterfaz[nuevoX][nuevoY].isEnabled();
	}

	// Destapa todas las minas al explotar una
	public void destaparMinas(int x, int y) {
		timer.stop();
		partidaPerdida = true;

		// Marca la mina que explotó
		actualizarCelda("explotada.gif", x, y);
		tableroInterfaz[x][y].setDisabledIcon(tableroInterfaz[x][y].getIcon());
		tableroInterfaz[x][y].setEnabled(false);

		// Muestra todas las demás minas
		for (int i = 0; i < tablero.getTablero().length; i++) {
			for (int j = 0; j < tablero.getTablero()[i].length; j++) {
				if (tablero.getTablero()[i][j].isMina() && !(i == x && j == y)) {
					actualizarCelda("mina.gif", i, j);
				}
				tableroInterfaz[i][j].setDisabledIcon(tableroInterfaz[i][j].getIcon());
				tableroInterfaz[i][j].setEnabled(false);
			}
		}
	}

	public void finalizarPartida() {
		timer.stop();
		partidaPerdida = true;
		for (int i = 0; i < tablero.getTablero().length; i++) {
			for (int j = 0; j < tablero.getTablero()[i].length; j++) {
				actualizarCelda("mina.gif", i, j);
				tableroInterfaz[i][j].setDisabledIcon(tableroInterfaz[i][j].getIcon());
				tableroInterfaz[i][j].setEnabled(false);
			}
		}
	}

	// Cambia el icono de la celda al hacer clic derecho
	public void comprobarBandera(int x, int y) {
		Icon icono = tableroInterfaz[x][y].getIcon();
		ImageIcon iconoImg = (ImageIcon) icono;
		String ruta = iconoImg.getDescription();

		if (ruta.equals("celda0.gif") && minasRestantes > 0) {
			actualizarCelda("bandera.gif", x, y);
			if (tableroInterfaz[x][y].isEnabled()) {
				this.minasRestantes--;
			}
		} else if (ruta.equals("bandera.gif")) {
			actualizarCelda("interrogante.gif", x, y);
			if (tableroInterfaz[x][y].isEnabled()) {
				this.minasRestantes++;
			}
		} else {
			actualizarCelda("celda0.gif", x, y);
		}

		// Actualiza el contador de minas
		int unidades = this.minasRestantes % 10;
		int decenas = this.minasRestantes / 10;
		actualizarContador(decenasMinas, decenas);
		actualizarContador(unidadesMinas, unidades);
	}

	// Método para comprobar si es una victoria
	private void comprobarVictoria(JButton[][] tableroInterfaz) {
		boolean victoria = true;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				Icon icono = tableroInterfaz[i][j].getIcon();
				ImageIcon iconoImg = (ImageIcon) icono;
				String ruta = iconoImg.getDescription();

				if (ruta.equals("celda0.gif") && ruta.equals("bandera.gif")) {
					victoria = false;
					break;
				}
			}
		}

		if (victoria) {
			partidaGanada = true;
		}
	}

	// Configura las propiedades de los botones del tablero
	public GridBagConstraints generarEstructuraTablero() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		gbc.weighty = 1;

		return gbc;
	}

	// Actualiza el contador visual de minas restantes
	public void actualizarContador(JLabel cifra, int ud) {
		String ruta = "time" + ud + ".gif";
		ImageIcon iconLogo = new ImageIcon("src/imagenes/" + ruta);
		Image image = iconLogo.getImage();
		Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		iconLogo = new ImageIcon(scaledImage);
		cifra.setIcon(iconLogo);
	}

	public void temporizador() {
		// Crea un Timer que se ejecuta cada 1000 milisegundos (1 segundo)
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Si el timepo es 999 la partida acaba
				if (tiempo >= 999) {
		            JOptionPane.showMessageDialog(VentanaJuego.this, "Tiempo limite excedido. GAME OVER.");
					finalizarPartida();
				} else {
					tiempo++; // Aumenta el tiempo en 1 segundo

					// Actualiza el temporizador visual (unidades, decenas, centenas)
					int unidad = tiempo % 10;
					int decena = (tiempo / 10) % 10;
					int centena = tiempo / 100;

					actualizarContador(temporizadorUd, unidad);
					actualizarContador(temporizadorDecenas, decena);
					actualizarContador(temporizadorCentenas, centena);
				}
			}
		});

		// Inicia el temporizador
		timer.start();
	}

}