package modelo;

public class Casilla {

	private boolean mina;

	public Casilla(boolean mina) {
		this.mina = mina;
	}

	public boolean isMina() {
		return mina;
	}

	public void setMina(boolean mina) {
		this.mina = mina;
	}
	
}
