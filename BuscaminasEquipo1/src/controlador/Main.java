package controlador;

import modelo.Casilla;
import modelo.Dificultad;
import modelo.Tablero;
import modelo.Usuario;
import vista.VentanaClasificacion;
import vista.VentanaInicioSesion;
import vista.VentanaJuego;

public class Main {
	
	// Version: 2.11.0
	
	// La ventanaJuego ahora tiene 2 jPanels para tener los datos y para tener las minas (el juego sigue siendo funcional).
	// Ahora hay una clase Usuario para almacenar el nombre y la puntuacion.
	// La ventanaJuego tiene un contador de paneles sin minas y de banderas restantes.
	// Hay que limitar el numero de minas que el jugador pueda poner (puede poner las que le apetezca saliendose del limite).	
	// VentanaJuego tiene una manera de abrir la clasificacion que esta sin acabar (por un error que no consigo solucionar).
	
	private static VentanaInicioSesion vis;
	private static VentanaJuego vj;
	private static VentanaClasificacion vc;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		vis = new VentanaInicioSesion();
		vis.setVisible(true);
		
		
	}
	
	public static void abrirJuego(Usuario usuario, Dificultad dificultad) { // Esta funcion sirve para abrir el juego
		
		if(usuario == null || usuario.getNombre().equals("")) {
			System.out.println("No se ha introducido ningun nombre de usuario.");
			System.out.println("Por favor, introduzca un nombre de usuario.");
			
		}else {
			vj = new VentanaJuego(usuario, dificultad);
			vis.setVisible(false);
			vj.setVisible(true);
		}
		
	}
	
	public static void abrirClasificacion(Usuario user) { // Esta funcion sirve para abrir la clasificacion
		
		vc = new VentanaClasificacion(user);
		vj.dispose(); // Cierra el juego
		vc.setVisible(true); // Abre la clasificacion
		
	}
	
	public static void cerrarJuego() { // Esto hay que hacerlo funcional: que cierre VentanaInicioSesion
		vis.setVisible(false);
	}
		
}