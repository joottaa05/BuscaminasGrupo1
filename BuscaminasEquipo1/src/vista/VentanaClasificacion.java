package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Usuario;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class VentanaClasificacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<String> clasificacion = new ArrayList<>();

	// Esto aun no se abre.
	
	public VentanaClasificacion(Usuario user) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("src/clasificacion.txt"));
	        
	        String linea;
	        while ((linea = br.readLine()) != null) {
	        	clasificacion.add(linea);
	        }
	        

	        Collections.sort(clasificacion);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		for(String s: clasificacion) {
			System.out.println(s);
		}
		
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
		
		JLabel jLabelClasificacion = new JLabel("Clasificacion:");
		jLabelClasificacion.setFont(new Font("Tahoma", Font.BOLD, 22));
		GridBagConstraints gbc_jLabelClasificacion = new GridBagConstraints();
		gbc_jLabelClasificacion.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelClasificacion.gridx = 1;
		gbc_jLabelClasificacion.gridy = 1;
		contentPane.add(jLabelClasificacion, gbc_jLabelClasificacion);
		
		JPanel tabla = new JPanel();
		tabla.setBackground(new Color(192, 192, 192));
		GridBagConstraints gbc_tabla = new GridBagConstraints();
		gbc_tabla.insets = new Insets(0, 0, 5, 5);
		gbc_tabla.fill = GridBagConstraints.BOTH;
		gbc_tabla.gridx = 1;
		gbc_tabla.gridy = 2;
		contentPane.add(tabla, gbc_tabla);
		GridBagLayout gbl_tabla = new GridBagLayout();
		gbl_tabla.columnWidths = new int[]{0, 0, 0, 0};
		gbl_tabla.rowHeights = new int[]{0, 0};
		gbl_tabla.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_tabla.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		tabla.setLayout(gbl_tabla);
		
		JPanel columnaPuesto = new JPanel();
		columnaPuesto.setBackground(new Color(128, 128, 128));
		GridBagConstraints gbc_columnaPuesto = new GridBagConstraints();
		gbc_columnaPuesto.insets = new Insets(0, 0, 0, 5);
		gbc_columnaPuesto.fill = GridBagConstraints.BOTH;
		gbc_columnaPuesto.gridx = 0;
		gbc_columnaPuesto.gridy = 0;
		tabla.add(columnaPuesto, gbc_columnaPuesto);
		
		JLabel jLabelPuesto = new JLabel("Puesto");
		jLabelPuesto.setFont(new Font("Tahoma", Font.BOLD, 10));
		jLabelPuesto.setForeground(new Color(255, 255, 255));
		columnaPuesto.add(jLabelPuesto);
		
		JPanel columnaUsuario = new JPanel();
		columnaUsuario.setBackground(new Color(128, 128, 128));
		GridBagConstraints gbc_columnaUsuario = new GridBagConstraints();
		gbc_columnaUsuario.insets = new Insets(0, 0, 0, 5);
		gbc_columnaUsuario.fill = GridBagConstraints.BOTH;
		gbc_columnaUsuario.gridx = 1;
		gbc_columnaUsuario.gridy = 0;
		tabla.add(columnaUsuario, gbc_columnaUsuario);
		
		JLabel jLabelUsuario = new JLabel("Usuario");
		jLabelUsuario.setFont(new Font("Tahoma", Font.BOLD, 10));
		jLabelUsuario.setForeground(new Color(255, 255, 255));
		columnaUsuario.add(jLabelUsuario);
		
		JPanel columnaPuntuacion = new JPanel();
		columnaPuntuacion.setBackground(new Color(128, 128, 128));
		GridBagConstraints gbc_columnaPuntuacion = new GridBagConstraints();
		gbc_columnaPuntuacion.fill = GridBagConstraints.BOTH;
		gbc_columnaPuntuacion.gridx = 2;
		gbc_columnaPuntuacion.gridy = 0;
		tabla.add(columnaPuntuacion, gbc_columnaPuntuacion);
		
		JLabel jLabelPuntuacion = new JLabel("Puntuacion");
		jLabelPuntuacion.setForeground(new Color(255, 255, 255));
		jLabelPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 10));
		columnaPuntuacion.add(jLabelPuntuacion);
	}

}
