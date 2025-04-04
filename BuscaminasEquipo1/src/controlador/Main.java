package controlador;

import modelo.Casilla;
import modelo.Dificultad;
import modelo.Tablero;
import vista.VentanaInicioSesion;
import vista.VentanaJuego;

public class Main {
	
	private static VentanaInicioSesion vis;
	private static VentanaJuego vj;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		vis = new VentanaInicioSesion();
		vis.setVisible(true);
		
	}
	
	public static void abrirJuego(String usuario, Dificultad dificultad) { // Hay que hacer que la dificultad se mande a la 'VentanaInicioSesion', ya que esta puesto para que siempre sea INTERMEDIO.
		
		if(usuario == null || usuario.equals("")) {
			System.out.println("No se ha introducido ningun nombre de usuario.");
			System.out.println("Por favor, introduzca un nombre de usuario.");
			
		}else {
			vj = new VentanaJuego(dificultad);
			vis.setVisible(false);
			vj.setVisible(true);
		}
		
	}
	
}