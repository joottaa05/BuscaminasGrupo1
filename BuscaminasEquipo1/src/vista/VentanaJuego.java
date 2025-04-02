package vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Tablero;

import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import modelo.Casilla;
import modelo.Dificultad;
import modelo.Tablero;
import java.awt.Color;
public class VentanaJuego extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected static Tablero tablero = new Tablero(Dificultad.Dificil);
	private JPanel gridBagLayoutDatos;
	private JLabel numeroMinas;
	private JLabel dificultad;
	private JLabel temporizador;
	private JPanel gridBagLayoutTablero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJuego frame = new VentanaJuego(tablero);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaJuego(Tablero tablero) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		gridBagLayoutDatos = new JPanel();
		gridBagLayoutDatos.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_gridBagLayoutDatos = new GridBagConstraints();
		gbc_gridBagLayoutDatos.insets = new Insets(0, 0, 5, 5);
		gbc_gridBagLayoutDatos.fill = GridBagConstraints.BOTH;
		gbc_gridBagLayoutDatos.gridx = 1;
		gbc_gridBagLayoutDatos.gridy = 1;
		contentPane.add(gridBagLayoutDatos, gbc_gridBagLayoutDatos);
		GridBagLayout gbl_gridBagLayoutDatos = new GridBagLayout();
		gbl_gridBagLayoutDatos.columnWidths = new int[]{0, 0, 0, 0};
		gbl_gridBagLayoutDatos.rowHeights = new int[]{0, 0};
		gbl_gridBagLayoutDatos.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_gridBagLayoutDatos.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayoutDatos.setLayout(gbl_gridBagLayoutDatos);
		
		numeroMinas = new JLabel("New label");
		GridBagConstraints gbc_numeroMinas = new GridBagConstraints();
		gbc_numeroMinas.insets = new Insets(0, 0, 0, 5);
		gbc_numeroMinas.gridx = 0;
		gbc_numeroMinas.gridy = 0;
		gridBagLayoutDatos.add(numeroMinas, gbc_numeroMinas);
		
		dificultad = new JLabel("New label");
		GridBagConstraints gbc_dificultad = new GridBagConstraints();
		gbc_dificultad.insets = new Insets(0, 0, 0, 5);
		gbc_dificultad.gridx = 1;
		gbc_dificultad.gridy = 0;
		gridBagLayoutDatos.add(dificultad, gbc_dificultad);
		
		temporizador = new JLabel("New label");
		GridBagConstraints gbc_temporizador = new GridBagConstraints();
		gbc_temporizador.gridx = 2;
		gbc_temporizador.gridy = 0;
		gridBagLayoutDatos.add(temporizador, gbc_temporizador);
		
		gridBagLayoutTablero = new JPanel();
		GridBagConstraints gbc_gridBagLayoutTablero = new GridBagConstraints();
		gbc_gridBagLayoutTablero.insets = new Insets(0, 0, 5, 5);
		gbc_gridBagLayoutTablero.fill = GridBagConstraints.BOTH;
		gbc_gridBagLayoutTablero.gridx = 1;
		gbc_gridBagLayoutTablero.gridy = 2;
		contentPane.add(gridBagLayoutTablero, gbc_gridBagLayoutTablero);
		GridBagLayout gbl_gridBagLayoutTablero = new GridBagLayout();
		gbl_gridBagLayoutTablero.columnWidths = new int[]{0};
		gbl_gridBagLayoutTablero.rowHeights = new int[]{0};
		gbl_gridBagLayoutTablero.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_gridBagLayoutTablero.rowWeights = new double[]{Double.MIN_VALUE};
		gridBagLayoutTablero.setLayout(gbl_gridBagLayoutTablero);
	}
	
	
	
	public void actualizarMinas(String fotoSrc) {
		ImageIcon iconLogo = new ImageIcon("src/"+fotoSrc);
		Image image = iconLogo.getImage();
		Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		iconLogo = new ImageIcon(scaledImage);
		numeroMinas.setIcon(iconLogo);
	}


}
