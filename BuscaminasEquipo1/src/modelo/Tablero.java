package modelo;

public class Tablero {
	private Dificultad dificultad;
	private Casilla[][] tableroJuego;
	
	public Tablero(Dificultad dificultad) {
		this.dificultad = dificultad;
	}
	
	public void generarTablero() {
		//Consigo filas y columnas del enumerado 'Dificultad'
		int columnas = dificultad.getColumnas();
		int filas = dificultad.getFilas();
		
		//Inicializo el tablero sin minas
		this.tableroJuego = new Casilla[filas][columnas];
		for(int i = 0; i<filas;i++) {
			for(int j = 0; j<columnas; j++) {
				tableroJuego[i][j] = new Casilla(false);
			}
		}
	}
	
	public void colocarMinas(Casilla[][] tablero) {
		int minas = dificultad.getminas(); //Cuantas minas se deben colocar
		int columnas = dificultad.getColumnas();
		int filas = dificultad.getFilas();
		
		//Calculo de las casillas maximas para el random
		final int min = 0;
		final int max = columnas * filas;
		
		//Bucle para colocar minas hasta que 'minas' sea = 0
		while(minas > 0) {
			//Genero la posición de la mina
			int mina = (int) (Math.random() * max);
			
			//Calculo el eje x e y a partir de la posición generada
			int i = (mina / columnas);
			int j = (mina % columnas);
			
			//Compruba que la casilla del tablero no tenga ya una mina
			if(!tablero[i][j].isMina()) {
				tablero[i][j].setMina(true); //Si no hay mina, la coloca y queda una mina menos que colocar
				minas--;
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
