package modelo;

public class Tablero {
	private static int filas;
	private static int columnas;
	private static int numeroMinas;
	
	
	public Tablero(int filas, int columnas, int numeroMinas) {
		this.filas = filas;
		this.columnas = columnas;
		this.numeroMinas = numeroMinas;
	}


	public static int getFilas() {
		return filas;
	}


	public void setFilas(int filas) {
		this.filas = filas;
	}


	public static int getColumnas() {
		return columnas;
	}


	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
	
	
}
