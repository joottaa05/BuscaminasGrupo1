package modelo;

public class Usuario {
	
	// En esta clase se guardara el nombre de usuario y la puntuacion.
	// En caso de ganar, lo que contenga esta clase se guardara en el fichero clasificacion.
	
	private String nombre;
	
	public Usuario (String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
}
