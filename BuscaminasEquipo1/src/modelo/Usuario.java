package modelo;

public class Usuario {
	
	// En esta clase se guardara el nombre de usuario y la puntuacion.
	// En caso de ganar, lo que contenga esta clase se guardara en el fichero clasificacion.
	
	private String nombre;
	private float puntuacion;
	
	public Usuario (String nombre) {
		this.nombre = nombre;
	}

	
	public float getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(float puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getNombre() {
		return nombre;
	}
	
}
