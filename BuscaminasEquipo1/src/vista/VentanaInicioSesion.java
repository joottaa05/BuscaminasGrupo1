package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Main;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class VentanaInicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicioSesion frame = new VentanaInicioSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VentanaInicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 184);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 87, 66, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel tituloBuscaminas = new JLabel("Buscaminas");
		tituloBuscaminas.setForeground(new Color(18, 148, 254));
		tituloBuscaminas.setBackground(SystemColor.inactiveCaptionBorder);
		tituloBuscaminas.setFont(new Font("Segoe UI", Font.BOLD, 18));
		GridBagConstraints gbc_tituloBuscaminas = new GridBagConstraints();
		gbc_tituloBuscaminas.gridwidth = 3;
		gbc_tituloBuscaminas.insets = new Insets(0, 0, 5, 0);
		gbc_tituloBuscaminas.gridx = 1;
		gbc_tituloBuscaminas.gridy = 0;
		contentPane.add(tituloBuscaminas, gbc_tituloBuscaminas);
		
		JLabel tituloUsuario = new JLabel("Usuario:");
		tituloUsuario.setForeground(SystemColor.control);
		tituloUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_tituloUsuario = new GridBagConstraints();
		gbc_tituloUsuario.anchor = GridBagConstraints.EAST;
		gbc_tituloUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_tituloUsuario.gridx = 1;
		gbc_tituloUsuario.gridy = 1;
		contentPane.add(tituloUsuario, gbc_tituloUsuario);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldUsuario.setForeground(new Color(49, 63, 79));
		GridBagConstraints gbc_textFieldUsuario = new GridBagConstraints();
		gbc_textFieldUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsuario.gridx = 2;
		gbc_textFieldUsuario.gridy = 1;
		contentPane.add(textFieldUsuario, gbc_textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel tituloDificultad = new JLabel("Dificultad:");
		tituloDificultad.setForeground(SystemColor.control);
		tituloDificultad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_tituloDificultad = new GridBagConstraints();
		gbc_tituloDificultad.anchor = GridBagConstraints.EAST;
		gbc_tituloDificultad.insets = new Insets(0, 0, 5, 5);
		gbc_tituloDificultad.gridx = 1;
		gbc_tituloDificultad.gridy = 2;
		contentPane.add(tituloDificultad, gbc_tituloDificultad);
		
		JComboBox dificultad = new JComboBox<>(new String[] { "Fácil", "Medio", "Difícil" });
		dificultad.setBackground(Color.WHITE);
		dificultad.setForeground(new Color(49, 63, 79));
		dificultad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_dificultad = new GridBagConstraints();
		gbc_dificultad.insets = new Insets(0, 0, 5, 5);
		gbc_dificultad.fill = GridBagConstraints.HORIZONTAL;
		gbc_dificultad.gridx = 2;
		gbc_dificultad.gridy = 2;
		contentPane.add(dificultad, gbc_dificultad);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.setForeground(new Color(49, 63, 79));
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.abrirJuego();
			}
		});
		btnJugar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnJugar = new GridBagConstraints();
		gbc_btnJugar.gridwidth = 3;
		gbc_btnJugar.gridx = 1;
		gbc_btnJugar.gridy = 3;
		contentPane.add(btnJugar, gbc_btnJugar);
	}

}
