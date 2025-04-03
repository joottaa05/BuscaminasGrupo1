package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Dificultad;
import modelo.Tablero;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel numeroMinas;
	private JLabel dificultad;
	private JLabel temporizador;
	private JButton[][] tableroInterfaz;

	public VentanaJuego(Tablero tablero) {
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

		int filas = tablero.getTablero().length;
		int columnas = tablero.getTablero()[0].length;
		tableroInterfaz = new JButton[filas][columnas];

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				tableroInterfaz[i][j] = new JButton();
				tableroInterfaz[i][j].setFocusPainted(false);
				tableroInterfaz[i][j].setBorder(BorderFactory.createEmptyBorder());
				tableroInterfaz[i][j].setContentAreaFilled(false);
				ImageIcon icono = tableroInicial("src/imagenes/blank.gif");
				tableroInterfaz[i][j].setIcon(icono);

				final int x = i;
				final int y = j;
				int numero = tablero.conseguirNumeroCasilla(tablero.getTablero(), x, y);
				tableroInterfaz[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						actualizarCeldas(x, y, numero);
					}
				});

				gbc.gridx = j;
				gbc.gridy = i + 1;

				contentPane.add(tableroInterfaz[i][j], gbc);
			}
		}

		pack();
		setVisible(true);

	}

	public ImageIcon tableroInicial(String ruta) {
		ImageIcon icono = new ImageIcon(ruta);
		Image imagen = icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		return new ImageIcon(imagen);
	}

	public void actualizarCeldas(int i, int j, int numero) {
		String fotoSrc;
		if (numero >= 0) {
			fotoSrc = "open" + numero + ".gif";
		} else {
			fotoSrc = "bombdeath.gif";
		}
		ImageIcon iconLogo = new ImageIcon("src/imagenes/" + fotoSrc);
		Image image = iconLogo.getImage();
		Image scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		iconLogo = new ImageIcon(scaledImage);
		tableroInterfaz[i][j].setIcon(iconLogo);
	}
}