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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class VentanaClasificacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private HashMap<String, Integer> clasificacion = new HashMap<>();
	
	public VentanaClasificacion(Usuario user) {
		BufferedReader br;
		ArrayList<String> keys = null;
        ArrayList<Integer> valores = null;
        LinkedHashMap<String, Integer> mapOrdenado = null;
        
		try {
			br = new BufferedReader(new FileReader("src/clasificacion.txt"));
	        
	        String linea;
	        String nombre = "";
	        Integer puntuacion = 0;
	        
	        while ((linea = br.readLine()) != null) {
	        	nombre = linea.split(",")[0];
	        	puntuacion = Integer.parseInt(linea.split(",")[1]);
	        	clasificacion.put(nombre, puntuacion);
	        }
	        
	        keys = new ArrayList<>(clasificacion.keySet());
	        valores = new ArrayList<>(clasificacion.values());

	        Collections.sort(valores, Collections.reverseOrder()); 
	        
	        mapOrdenado = new LinkedHashMap<>();
	        
	        for (Integer valor : valores) {
	            for (String clave : keys) {
	                if (clasificacion.get(clave).equals(valor) && !mapOrdenado.containsKey(clave)) {
	                	
	                    mapOrdenado.put(clave, valor);
	                    break;
	                }
	            }
	        }

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		tabla.setBackground(new Color(180, 180, 180));
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
		GridBagConstraints gbc_columnaPuesto = new GridBagConstraints();
		columnaPuesto.setBackground(new Color(180, 180, 180));
		gbc_columnaPuesto.insets = new Insets(0, 0, 0, 5);
		gbc_columnaPuesto.fill = GridBagConstraints.BOTH;
		gbc_columnaPuesto.gridx = 0;
		gbc_columnaPuesto.gridy = 0;
		tabla.add(columnaPuesto, gbc_columnaPuesto);
		
		JPanel columnaUsuario = new JPanel();
		GridBagConstraints gbc_columnaUsuario = new GridBagConstraints();
		columnaUsuario.setBackground(new Color(180, 180, 180));
		gbc_columnaUsuario.insets = new Insets(0, 0, 0, 5);
		gbc_columnaUsuario.fill = GridBagConstraints.BOTH;
		gbc_columnaUsuario.gridx = 1;
		gbc_columnaUsuario.gridy = 0;
		tabla.add(columnaUsuario, gbc_columnaUsuario);
		
		
		JPanel columnaPuntuacion = new JPanel();
		GridBagConstraints gbc_columnaPuntuacion = new GridBagConstraints();
		columnaPuntuacion.setBackground(new Color(180, 180, 180));
		gbc_columnaPuntuacion.fill = GridBagConstraints.BOTH;
		gbc_columnaPuntuacion.gridx = 2;
		gbc_columnaPuntuacion.gridy = 0;
		tabla.add(columnaPuntuacion, gbc_columnaPuntuacion);
		

		columnaPuesto.setLayout(new GridBagLayout());
		columnaUsuario.setLayout(new GridBagLayout());
		columnaPuntuacion.setLayout(new GridBagLayout());
		
		
		JLabel labelPuesto = new JLabel("TOP");
		GridBagConstraints gbcPuesto = new GridBagConstraints();
	    gbcPuesto = new GridBagConstraints();
	    gbcPuesto.gridx = 0;
	    gbcPuesto.gridy = 0;
	    gbcPuesto.anchor = GridBagConstraints.WEST;
	    columnaPuesto.add(labelPuesto, gbcPuesto);
		
		JLabel labelNombre = new JLabel("JUGADOR");
		GridBagConstraints gbcNombre = new GridBagConstraints();
	    gbcPuesto = new GridBagConstraints();
	    gbcPuesto.gridx = 0;
	    gbcPuesto.gridy = 0;
	    gbcPuesto.anchor = GridBagConstraints.WEST;
	    columnaUsuario.add(labelNombre, gbcNombre);
	    
		JLabel labelPuntuacion = new JLabel("SCORE");
	    GridBagConstraints gbcPuntuacion = new GridBagConstraints();
	    gbcPuesto = new GridBagConstraints();
	    gbcPuesto.gridx = 0;
	    gbcPuesto.gridy = 0;
	    gbcPuesto.anchor = GridBagConstraints.WEST;
	    columnaPuntuacion.add(labelPuntuacion, gbcPuntuacion);
	    
		Iterator<String> it2 = mapOrdenado.keySet().iterator();
				
		int fila = 1;

		while (it2.hasNext()) {
		    String nombreJugador = it2.next();
		    Integer puntuacionJugador = mapOrdenado.get(nombreJugador);

		    labelPuesto = new JLabel((fila) + ".");
		    gbcPuesto = new GridBagConstraints();
		    gbcPuesto.gridx = 0;
		    gbcPuesto.gridy = fila;
		    gbcPuesto.anchor = GridBagConstraints.WEST;
		    columnaPuesto.add(labelPuesto, gbcPuesto);

		    labelNombre = new JLabel(nombreJugador);
		    gbcNombre = new GridBagConstraints();
		    gbcNombre.gridx = 0;
		    gbcNombre.gridy = fila;
		    gbcNombre.anchor = GridBagConstraints.WEST;
		    columnaUsuario.add(labelNombre, gbcNombre);

		    labelPuntuacion = new JLabel(puntuacionJugador.toString());
		    gbcPuntuacion = new GridBagConstraints();
		    gbcPuntuacion.gridx = 0;
		    gbcPuntuacion.gridy = fila;
		    gbcPuntuacion.anchor = GridBagConstraints.WEST;
		    columnaPuntuacion.add(labelPuntuacion, gbcPuntuacion);

		    fila++;
		}
	}

}
