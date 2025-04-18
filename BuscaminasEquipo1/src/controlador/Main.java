package controlador;

import modelo.Casilla;
import modelo.Dificultad;
import modelo.Tablero;
import vista.VentanaInicioSesion;
import vista.VentanaJuego;

public class Main {
	
	// Version 2.3
	
	// La ventanaJuego ahora tiene 2 jPanels para tener los datos y para tener las minas (el juego sigue siendo funcional).
	
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
			vj = new VentanaJuego(dificultad);
			vis.setVisible(false);
			vj.setVisible(true);
		}
		
	}
	
	public static void cerrarJuego() { // Esto hay que hacerlo funcional: que cierre VentanaInicioSesion
		vis.setVisible(false);
	}
	
	
}