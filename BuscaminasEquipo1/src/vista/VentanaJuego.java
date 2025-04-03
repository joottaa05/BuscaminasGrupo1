package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import modelo.Dificultad;
import modelo.Tablero;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel numeroMinas;
	private JLabel temporizador;
	private JButton[][] tableroInterfaz;
	private Tablero tablero = new Tablero(Dificultad.Dificil);
	private boolean esBandera = false;
	private boolean esInterrogante = false;

	public VentanaJuego() {
		setTitle("Juego Buscaminas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);

		contentPane = new JPanel(new GridBagLayout());
		setContentPane(contentPane);

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
							esBandera = false;
						} else if (SwingUtilities.isRightMouseButton(e) && !esBandera && esInterrogante == false) {
							colocarBandera(x, y);
							esBandera = true;
						} else if (SwingUtilities.isRightMouseButton(e) && esBandera) {
							actualizarCelda("flagremoved.gif", x, y);
							esBandera = false;
							esInterrogante = true;
						} else {
							actualizarCelda("blank.gif", x, y);
							esInterrogante = false;
						}
					}

				});

				gbc.gridx = j;
				gbc.gridy = i + 1;

				contentPane.add(tableroInterfaz[i][j], gbc);
			}
		}

		setVisible(true);
		pack();
	}

	public void tableroInicial(int i, int j) {
		tableroInterfaz[i][j] = new JButton();
		tableroInterfaz[i][j].setFocusPainted(false);
		tableroInterfaz[i][j].setBorder(BorderFactory.createEmptyBorder());
		tableroInterfaz[i][j].setContentAreaFilled(false);
		ImageIcon icono = tableroInicial("src/imagenes/blank.gif");
		tableroInterfaz[i][j].setIcon(icono);
	}

	public ImageIcon tableroInicial(String ruta) {
		ImageIcon icono = new ImageIcon(ruta);
		Image imagen = icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		return new ImageIcon(imagen);
	}

	public void actualizarCelda(String fotoSrc, int i, int j) {
		ImageIcon iconLogo = new ImageIcon("src/imagenes/" + fotoSrc);
		Image image = iconLogo.getImage();
		Image scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		iconLogo = new ImageIcon(scaledImage);
		tableroInterfaz[i][j].setIcon(iconLogo);
	}

	public void destaparCelda(int x, int y, int numero) {
		String fotoSrc;
		if (numero >= 0 && numero <= 8) {
			fotoSrc = "open" + numero + ".gif";
		} else {
			fotoSrc = "bombdeath.gif";
			destaparMinas(x, y);
		}
		actualizarCelda(fotoSrc, x, y);
	}

	public void destaparMinas(int x, int y) {
		for (int i = 0; i < tablero.getTablero().length; i++) {
			for (int j = 0; j < tablero.getTablero()[i].length; j++) {
				if (tablero.getTablero()[i][j].isMina()) {
					actualizarCelda("bombrevealed.gif", i, j);
				}
				actualizarCelda("bombdeath.gif", x, y);
				tableroInterfaz[i][j].setDisabledIcon(tableroInterfaz[i][j].getIcon());
				tableroInterfaz[i][j].setEnabled(false);
			}
		}

	}

	public void colocarBandera(int x, int y) {
		actualizarCelda("bombflagged.gif", x, y);
	}
}