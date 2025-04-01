package modelo;

public class Tablero {
	private Dificultad dificultad;

	public Tablero(Dificultad dificultad) {
		this.dificultad = dificultad;
	}
	
	
	public String[][] generarTablero(Dificultad dificultad) {
		int filas = dificultad.getFilas();
		int columnas = dificultad.getFilas();
		int minas = dificultad.getminas();
		
		final int min = 0;
		final int max = columnas * filas;
		
		String[][] tablero = new String[columnas][filas];
		
		
		while(minas > 0) {
			int mina = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
			
			int x = mina % dificultad.getColumnas();
			int y = (int)(Math.floor(mina / dificultad.getColumnas()));
			
			
		}
		return tablero;
	}
	
	
	
}
