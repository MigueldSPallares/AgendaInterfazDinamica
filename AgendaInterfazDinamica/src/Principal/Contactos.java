package Principal;

public class Contactos {
	private String nombre;
	private String telefono;
	private boolean sexo;
	
	public Contactos() {
		
	}

	public Contactos(String nombre, String telefono) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.sexo = sexo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		String sexo = "Hombre";
		if(!this.sexo) {
			sexo="Mujer";
		}
		return "Contactos [nombre=" + nombre + ", telefono=" + telefono + ", sexo=" + sexo + "]";
	}
	
	public boolean isSexo() {
		return sexo;
	}

	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}
	
}
