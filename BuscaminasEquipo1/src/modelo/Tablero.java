package modelo;

public class Tablero {
	private Dificultad dificultad;
	private Casilla[][] tableroJuego;
	
	public Tablero(Dificultad dificultad) {
		this.dificultad = dificultad;
	}
	
	public void generarTablero() {
		int columnas = dificultad.getColumnas();
		int filas = dificultad.getFilas();
		
		this.tableroJuego = new Casilla[filas][columnas];
		for(int i = 0; i<filas;i++) {
			for(int j = 0; j<columnas; j++) {
				tableroJuego[i][j] = new Casilla(false);
			}
		}
	}
	
	public void colocarMinas(Casilla[][] tablero) {
		
		int minas = dificultad.getminas();
		int columnas = dificultad.getColumnas();
		int filas = dificultad.getFilas();
		
		final int min = 0;
		final int max = columnas * filas;
		
		
		
		while(minas > 0) {
			int mina = (int) (Math.random() * max);
			
			int i = (mina / columnas);
			int j = mina % columnas;
			
			if(!tablero[i][j].isMina()) {
				tablero[i][j].setMina(true);
				minas--;
			} else {
				continue;
			}
		}
	}

	public Casilla[][] getTablero() {
		return tableroJuego;
	}
	
	public void mostrarTablero() {
		for(int i = 0; i<tableroJuego.length;i++) {
			for(int j = 0; j<tableroJuego[i].length; j++) {
				System.out.print(tableroJuego[i][j].isMina() + " ");
			}
			System.out.println();
		}
	}
	
	public void comprobarMinas() {
		int minas = 0;
		for(int i = 0; i<tableroJuego.length;i++) {
			for(int j = 0; j<tableroJuego[i].length; j++) {
				if(tableroJuego[i][j].isMina()) {
					minas++;
				}
			}
		}
		System.out.println(minas);
	}
}
