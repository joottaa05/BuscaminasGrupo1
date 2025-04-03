package controlador;

import modelo.Casilla;
import modelo.Dificultad;
import modelo.Tablero;
import vista.VentanaInicioSesion;
import vista.VentanaJuego;

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tablero tablero = new Tablero(Dificultad.Fácil);
		tablero.generarTablero();
		tablero.colocarMinas(tablero.getTablero());
		VentanaJuego vj = new VentanaJuego(tablero);
		vj.setVisible(true);
	}
}