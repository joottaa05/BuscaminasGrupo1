package modelo;

public enum Dificultad {
	Fácil(8, 8, 10), Intermedio(16, 16, 40), Dificil(16, 30, 99);

	private final int filas;
	private final int columnas;
	private final int minas;

	private Dificultad(int filas, int columnas, int minas) {
		this.filas = filas;
		this.columnas = columnas;
		this.minas = minas;
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public int getminas() {
		return minas;
	}
	
}
