package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import modelo.Dificultad;
import modelo.Tablero;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JPanel panelMinas;
	private JPanel panelReset;
	private JPanel panelTemporizador;
	private JLabel numeroMinas;
	private JLabel temporizador;
	private JLabel decenasMinas;
	private JLabel unidadesMinas;
	private int filas;
	private int columnas;
	private int minasRestantes;
	private GridBagConstraints gbc;
	private Color bordes = new Color(134, 96, 67);
	private JButton[][] tableroInterfaz;
	private Tablero tablero;

	public VentanaJuego(Dificultad dificultad) {
		tablero = new Tablero(dificultad);
		setTitle("Juego Buscaminas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);

		this.minasRestantes = dificultad.getminas();

		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(bordes, 4));
		setContentPane(contentPanel);

		panelSuperior = new JPanel();
		panelSuperior.setPreferredSize(new Dimension(200, 50));
		panelSuperior.setBackground(bordes);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));

		panelMinas = new JPanel();
		panelMinas.setLayout(new GridLayout(1, 2));
		panelSuperior.add(panelMinas);

		panelInferior = new JPanel();
		panelInferior.setLayout(new GridBagLayout());

		contentPanel.add(panelSuperior, BorderLayout.NORTH);
		contentPanel.add(panelInferior, BorderLayout.CENTER);
		

		int unidades = this.minasRestantes % 10;
		int decenas = this.minasRestantes / 10;
		
		decenasMinas = new JLabel();
		actualizarContadorMinas(decenasMinas, decenas);
		panelSuperior.add(decenasMinas);
		
		unidadesMinas = new JLabel();
		actualizarContadorMinas(unidadesMinas, unidades);
		panelSuperior.add(unidadesMinas);

		gbc = generarEstructuraTablero();
		generarTableroMinas();
		setVisible(true);
		pack();
	}

	public void tableroInicial(int i, int j) {
		tableroInterfaz[i][j] = new JButton();
		Color borde = new Color(0, 0, 0);
		if (i == 0 && j == 0) {
			tableroInterfaz[i][j].setBorder(new MatteBorder(1, 1, 1, 1, borde)); // Para la primera celda (0,0)
		} else if (i == 0) {
			tableroInterfaz[i][j].setBorder(new MatteBorder(1, 0, 1, 1, borde)); // Para las celdas en la fila 0
		} else if (j == 0) {
			tableroInterfaz[i][j].setBorder(new MatteBorder(0, 1, 1, 1, borde)); // Para las celdas en la columna 0
		} else {
			tableroInterfaz[i][j].setBorder(new MatteBorder(0, 0, 1, 1, borde)); // Para el resto de las celdas
		}
		actualizarCelda("celda0.gif",i,j);

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

			} else if (numero == -1) {
				fotoSrc = "explotada.gif";
				destaparMinas(x, y);
				return;
			} else {
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

	public boolean condicionDestaparAgua(int nuevoX, int nuevoY) {
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
		
		if (ruta.equals("celda0.gif")) {
			actualizarCelda("bandera.gif", x, y);
			this.minasRestantes--;
		} else if (ruta.equals("bandera.gif")) {
			actualizarCelda("interrogante.gif", x, y);
			this.minasRestantes++;
		} else {
			actualizarCelda("celda0.gif", x, y);
		}
		int unidades = this.minasRestantes % 10;
		int decenas = this.minasRestantes / 10;
		actualizarContadorMinas(decenasMinas, decenas);
		actualizarContadorMinas(unidadesMinas, unidades);
	}

	public GridBagConstraints generarEstructuraTablero() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		gbc.weighty = 1;

		return gbc;
	}

	public void generarTableroMinas() {
		tablero.generarTablero();
		tablero.colocarMinas(tablero.getTablero());
		this.filas = tablero.getTablero().length;
		this.columnas = tablero.getTablero()[0].length;
		tableroInterfaz = new JButton[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				tableroInicial(i, j);

				final int x = i;
				final int y = j;

				tableroInterfaz[i][j].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isLeftMouseButton(e)) {
							Icon icono = tableroInterfaz[x][y].getIcon();
							ImageIcon iconoImg = (ImageIcon) icono;
							String ruta = iconoImg.getDescription();
							
							if(!ruta.equals("bandera.gif")) {
							int numero = tablero.conseguirNumeroCasilla(tablero.getTablero(), x, y);
							destaparCelda(x, y, numero);
							}
						} else if (SwingUtilities.isRightMouseButton(e)) {
							comprobarBandera(x, y);
						}
					}
				});

				this.gbc.gridx = j;
				this.gbc.gridy = i + 1;
				panelInferior.add(tableroInterfaz[i][j], gbc);
			}
		}
	}

	public void actualizarContadorMinas(JLabel cifra, int minas) {
		String ruta = "time" + minas + ".gif";
		
		ImageIcon iconLogo = new ImageIcon("src/imagenes/" + ruta);
		Image image = iconLogo.getImage();
		Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		iconLogo = new ImageIcon(scaledImage);
		cifra.setIcon(iconLogo);
	}
}