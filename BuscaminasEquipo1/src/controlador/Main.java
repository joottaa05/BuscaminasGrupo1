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
		vj.setVisible(true);
		
	}
	
	public static void abrirJuego() {
		
		vj = new VentanaJuego();
		vis.setVisible(false);
		vj.setVisible(true);
		
	}
	
}