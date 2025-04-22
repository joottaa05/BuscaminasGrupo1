package vista;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import modelo.Dificultad;
import modelo.Tablero;
import modelo.Usuario;

import java.awt.GridLayout;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel numeroMinas;
	private JLabel temporizador;
	private JButton[][] tableroInterfaz;
	private Tablero tablero;
	private int banderasRestantes;
	private int espaciosSinMinas;

	// ----------------------------------------------------------------------------------------------------
	
	// Falta añadir el numero de banderas (que vaya cambiando segun añadas o quites banderas), la cara y el temporizador
	// Cuando le das a una mina, te deja hacer click a las casillas (cosa que hay que evitar)
	// Cuando te pasas el juego, no te lleva a VentanaClasificacion
	// Ahora tiene un contador de espacios sin minas y un contador de banderas (variables)
	// El juego acaba cuando espaciosSinMinas llega a 0
	// Aunque a un espacio le pongas una bandera, puedes hacerle click izquierdo (cosa que no queremos)
	// Hay una funcion para cambiar el numero de banderas que se muestra por pantalla QUE ESTA SIN ACABAR.
	
	public VentanaJuego(Dificultad dificultad, Usuario usuario) {
		
		banderasRestantes = dificultad.getminas();
		espaciosSinMinas = dificultad.getColumnas() * dificultad.getFilas() - banderasRestantes;
		
		tablero = new Tablero(dificultad);
		setTitle("Juego Buscaminas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(698, 301);
		setLocationRelativeTo(null);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0};
		gbl_contentPane.columnWeights = new double[]{1.0};
		contentPane = new JPanel(gbl_contentPane);
		setContentPane(contentPane);
		
		JPanel panelDatos = new JPanel();
		GridBagConstraints gbc_panelDatos = new GridBagConstraints();
		gbc_panelDatos.insets = new Insets(0, 0, 5, 0);
		gbc_panelDatos.fill = GridBagConstraints.BOTH;
		gbc_panelDatos.gridx = 0;
		gbc_panelDatos.gridy = 0;
		contentPane.add(panelDatos, gbc_panelDatos);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[]{119, 0};
		gbl_panelDatos.rowHeights = new int[]{10, 0, 0, 0};
		gbl_panelDatos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelDatos.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		panelDatos.setLayout(gbl_panelDatos);
		
		JPanel panelMinas = new JPanel();
		GridBagConstraints gbc_panelMinas = new GridBagConstraints();
		gbc_panelMinas.insets = new Insets(0, 0, 5, 0);
		gbc_panelMinas.fill = GridBagConstraints.BOTH;
		gbc_panelMinas.gridx = 0;
		gbc_panelMinas.gridy = 0;
		panelDatos.add(panelMinas, gbc_panelMinas);
		GridBagLayout gbl_panelMinas = new GridBagLayout();
		gbl_panelMinas.columnWidths = new int[]{0, 0};
		gbl_panelMinas.rowHeights = new int[]{0, 0, 0};
		gbl_panelMinas.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelMinas.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelMinas.setLayout(gbl_panelMinas);
		
		JPanel panelDigito1 = new JPanel();
		GridBagConstraints gbc_panelDigito1 = new GridBagConstraints();
		gbc_panelDigito1.insets = new Insets(0, 0, 5, 0);
		gbc_panelDigito1.fill = GridBagConstraints.BOTH;
		gbc_panelDigito1.gridx = 0;
		gbc_panelDigito1.gridy = 0;
		panelMinas.add(panelDigito1, gbc_panelDigito1);
		
		JPanel panelDigito2 = new JPanel();
		GridBagConstraints gbc_panelDigito2 = new GridBagConstraints();
		gbc_panelDigito2.fill = GridBagConstraints.BOTH;
		gbc_panelDigito2.gridx = 0;
		gbc_panelDigito2.gridy = 1;
		panelMinas.add(panelDigito2, gbc_panelDigito2);
		
		JPanel panelCara = new JPanel();
		GridBagConstraints gbc_panelCara = new GridBagConstraints();
		gbc_panelCara.insets = new Insets(0, 0, 5, 0);
		gbc_panelCara.fill = GridBagConstraints.BOTH;
		gbc_panelCara.gridx = 0;
		gbc_panelCara.gridy = 1;
		panelDatos.add(panelCara, gbc_panelCara);
		
		JPanel panelTiempo = new JPanel();
		GridBagConstraints gbc_panelTiempo = new GridBagConstraints();
		gbc_panelTiempo.fill = GridBagConstraints.BOTH;
		gbc_panelTiempo.gridx = 0;
		gbc_panelTiempo.gridy = 2;
		panelDatos.add(panelTiempo, gbc_panelTiempo);
				
		JPanel panelTablero = new JPanel();
		GridBagConstraints gbc_panelTablero = new GridBagConstraints();
		gbc_panelTablero.fill = GridBagConstraints.BOTH;
		gbc_panelTablero.gridx = 0;
		gbc_panelTablero.gridy = 1;
		contentPane.add(panelTablero, gbc_panelTablero);
		GridBagLayout gbl_panelTablero = new GridBagLayout();
		gbl_panelTablero.columnWidths = new int[]{0};
		gbl_panelTablero.rowHeights = new int[]{0};
		gbl_panelTablero.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panelTablero.rowWeights = new double[]{Double.MIN_VALUE};
		panelTablero.setLayout(gbl_panelTablero);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		tablero.generarTablero();
		tablero.colocarMinas(tablero.getTablero());
		int filas = tablero.getTablero().length;
		int columnas = tablero.getTablero()[0].length;
		tableroInterfaz = new JButton[filas][columnas];

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				tableroInicial(i, j);

				final int x = i;
				final int y = j;

				tableroInterfaz[i][j].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isLeftMouseButton(e)) {
							int numero = tablero.conseguirNumeroCasilla(tablero.getTablero(), x, y);
							destaparCelda(x, y, numero);
							System.out.println("Espacios sin minas: " + espaciosSinMinas);
						} else if (SwingUtilities.isRightMouseButton(e)) {
							comprobarBandera(x, y);
						}
					}
				});

				gbc.gridx = j;
				gbc.gridy = i + 1;

				panelTablero.add(tableroInterfaz[i][j], gbc);
			}
		}
		
		
		
		setVisible(true);
		pack();
	}

	// ----------------------------------------------------------------------------------------------------
	
	public void tableroInicial(int i, int j) {
		tableroInterfaz[i][j] = new JButton();
		if (i == 0) {
			tableroInterfaz[i][j].setBorder(new MatteBorder(1, 0, 1, 1, new Color(0, 0, 0)));
		} else {
			tableroInterfaz[i][j].setBorder(new MatteBorder(0, 0, 1, 1, new Color(0, 0, 0)));
		}
		if (j == 0) {
			tableroInterfaz[i][j].setBorder(new MatteBorder(0, 1, 1, 1, new Color(0, 0, 0)));
		} else {
			tableroInterfaz[i][j].setBorder(new MatteBorder(0, 0, 1, 1, new Color(0, 0, 0)));
		}
		
		ImageIcon icono = new ImageIcon("src/imagenes/celda0.gif");
		Image imagen = icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		icono = new ImageIcon(imagen);
		icono.setDescription("celda0.gif");
		tableroInterfaz[i][j].setIcon(icono);

	}

	public void actualizarCelda(String fotoSrc, int i, int j) {
		ImageIcon iconLogo = new ImageIcon("src/imagenes/" + fotoSrc);
		Image image = iconLogo.getImage();
		Image scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		iconLogo = new ImageIcon(scaledImage);
		tableroInterfaz[i][j].setIcon(iconLogo);
		iconLogo.setDescription(fotoSrc);
	}

	public void destaparCelda(int x, int y, int numero) {
		if (tableroInterfaz[x][y].isEnabled()) {
			String fotoSrc;
			if (numero > 0 && numero <= 8) {
				fotoSrc = numero + "Mina.gif";
				actualizarCelda(fotoSrc, x, y);
				espaciosSinMinas -= 1;
			} else if (numero == -1) {
				fotoSrc = "explotada.gif";
				destaparMinas(x, y);
				return;
			} else {
				espaciosSinMinas -= 1;
				actualizarCelda("sinMina.gif", x, y);
			}
			
			tableroInterfaz[x][y].setDisabledIcon(tableroInterfaz[x][y].getIcon());
		    tableroInterfaz[x][y].setEnabled(false);
			

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
						}
					}
				}
			}
		}
	}
	
	public boolean condicionDestaparAgua (int nuevoX, int nuevoY) {
		return nuevoX >= 0 && nuevoX < tablero.getTablero().length && nuevoY >= 0
				&& nuevoY < tablero.getTablero()[0].length && tableroInterfaz[nuevoX][nuevoY].isEnabled();
	}

	public void destaparMinas(int x, int y) {
		actualizarCelda("explotada.gif", x, y);
		tableroInterfaz[x][y].setDisabledIcon(tableroInterfaz[x][y].getIcon());
		tableroInterfaz[x][y].setEnabled(false);
		
		for (int i = 0; i < tablero.getTablero().length; i++) {
			for (int j = 0; j < tablero.getTablero()[i].length; j++) {
				if (tablero.getTablero()[i][j].isMina() && (i != x && j != y)) {
					actualizarCelda("mina.gif", i, j);
				}
				tableroInterfaz[i][j].setDisabledIcon(tableroInterfaz[i][j].getIcon());
				tableroInterfaz[i][j].setEnabled(false); 
			}
		}
	}

	public void comprobarBandera(int x, int y) {
		Icon icono = tableroInterfaz[x][y].getIcon();
		ImageIcon iconoImg = (ImageIcon) icono;
		String ruta = iconoImg.getDescription();

		if (ruta.equals("celda0.gif") && banderasRestantes > 0) {
			actualizarCelda("bandera.gif", x, y);
			banderasRestantes -= 1;
			System.out.println("Banderas restantes: " + banderasRestantes);
		} else if (ruta.equals("bandera.gif")) {
			actualizarCelda("interrogante.gif", x, y);
			banderasRestantes +=1;
			System.out.println("Banderas restantes: " + banderasRestantes);
		} else {
			actualizarCelda("celda0.gif", x, y);
		}
	}
	
	public void cambiarBanderas(int banderasRestantes) { // No consigo crear una funcion para cambiar el numero de banderas que se muestra en el juego		
		
		String banderasRestantesTexto = banderasRestantes + ""; // Recogera los 2 digitos del numero de banderas
		String digito1 = banderasRestantesTexto.substring(0, 1); // Sera el digito uno: x[] <- (el que tiene []).
		String digito2 = banderasRestantesTexto.substring(1, 2); // Sera el digito dos: []x <- (el que tiene []).
		ImageIcon digitoImagen1 = new ImageIcon("src/imagenes/time" + digito1 + ".gif");
		Image imagen1 = digitoImagen1.getImage().getScaledInstance(50, 25, Image.SCALE_SMOOTH);
		ImageIcon digitoImagen2 = new ImageIcon("src/imagenes/time" + digito2 + ".gif");
		Image imagen2 = digitoImagen2.getImage().getScaledInstance(50, 25, Image.SCALE_SMOOTH);
		digitoImagen1 = new ImageIcon(imagen1);
		digitoImagen2 = new ImageIcon(imagen2);
		digitoImagen1.setDescription("src/imagenes/time" + digito1 + ".gif");
		digitoImagen2.setDescription("src/imagenes/time" + digito2 + ".gif");

		// panelDigito1.setIcon(icono); Sin acabar
		
	}
	
}