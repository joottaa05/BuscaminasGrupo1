package controlador;

import modelo.Casilla;
import modelo.Dificultad;
import modelo.Tablero;
import modelo.Usuario;
import vista.VentanaInicioSesion;
import vista.VentanaJuego;

public class Main {
	
	// Version 2.5
	
	// La ventanaJuego ahora tiene 2 jPanels para tener los datos y para tener las minas (el juego sigue siendo funcional).
	// Ahora hay una clase Usuario para almacenar el nombre y la puntuacion.
	// La ventanaJuego tiene un contador de espacios en blanco y de banderas restantes.
	
	
	private static VentanaInicioSesion vis;
	private static VentanaJuego vj;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		vis = new VentanaInicioSesion();
		vis.setVisible(true);
		
	}
	
	public static void abrirJuego(String usuario, Dificultad dificultad) { 
		
		if(usuario == null || usuario.equals("")) {
			System.out.println("No se ha introducido ningun nombre de usuario.");
			System.out.println("Por favor, introduzca un nombre de usuario.");
			
		}else {
			vj = new VentanaJuego(dificultad, new Usuario(usuario));
			vis.setVisible(false);
			vj.setVisible(true);
		}
		
	}
	
	public static void cerrarJuego() { // Esto hay que hacerlo funcional: que cierre VentanaInicioSesion
		vis.setVisible(false);
	}
		
}