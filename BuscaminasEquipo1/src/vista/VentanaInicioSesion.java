package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Main;
import modelo.Dificultad;
import modelo.Usuario;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class VentanaInicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;

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
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JComboBox dificultad = new JComboBox<>(new String[] { "Fácil", "Intermedio", "Dificil" });
		dificultad.setBackground(Color.WHITE);
		dificultad.setForeground(new Color(49, 63, 79));
		dificultad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_dificultad = new GridBagConstraints();
		gbc_dificultad.insets = new Insets(0, 0, 5, 5);
		gbc_dificultad.fill = GridBagConstraints.HORIZONTAL;
		gbc_dificultad.gridx = 2;
		gbc_dificultad.gridy = 2;
		contentPane.add(dificultad, gbc_dificultad);
		
		JPanel panelOpciones = new JPanel();
		panelOpciones.setBackground(Color.GRAY);
		panelOpciones.setForeground(new Color(128, 128, 128));
		GridBagConstraints gbc_panelOpciones = new GridBagConstraints();
		gbc_panelOpciones.insets = new Insets(0, 0, 0, 5);
		gbc_panelOpciones.fill = GridBagConstraints.BOTH;
		gbc_panelOpciones.gridx = 2;
		gbc_panelOpciones.gridy = 3;
		contentPane.add(panelOpciones, gbc_panelOpciones);
		GridBagLayout gbl_panelOpciones = new GridBagLayout();
		gbl_panelOpciones.columnWidths = new int[]{0, 0, 0};
		gbl_panelOpciones.rowHeights = new int[]{0, 0};
		gbl_panelOpciones.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelOpciones.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelOpciones.setLayout(gbl_panelOpciones);
		
		JButton btnJugar = new JButton("Jugar");
		GridBagConstraints gbc_btnJugar = new GridBagConstraints();
		gbc_btnJugar.insets = new Insets(0, 0, 0, 5);
		gbc_btnJugar.gridx = 0;
		gbc_btnJugar.gridy = 0;
		panelOpciones.add(btnJugar, gbc_btnJugar);
		btnJugar.setForeground(new Color(49, 63, 79));
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedReader in = null;
				try {
					in = new BufferedReader(new FileReader("src/clasificacion.txt"));
					
					String nombre;
						boolean nombreRepetido = false;
						while((nombre = in.readLine()) != null) {
							String nombreUsuario = nombre.split(",")[0];
							if(textFieldUsuario.getText().equals(nombreUsuario)) {
								nombreRepetido = true;
								break;
							}
						}
						
						if(!nombreRepetido && !textFieldUsuario.getText().equals("") && textFieldUsuario != null) {
						Usuario user = new Usuario(textFieldUsuario.getText());
						Main.abrirJuego(user, Dificultad.valueOf(dificultad.getSelectedItem().toString()));
						} else {
				            JOptionPane.showMessageDialog(VentanaInicioSesion.this, "El nombre esta vacio o repetido, por favor, introduzca otro nombre de usuario distinto.");

						}
						
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}  catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						in.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnJugar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(49, 63, 79));
		btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.gridx = 1;
		gbc_btnSalir.gridy = 0;
		panelOpciones.add(btnSalir, gbc_btnSalir);
	}

}
