package modelo.usuario;

public abstract class Usuario {
	private String nombre;
	private String email;
	private String contraseña;
	
	public Usuario(String nombre, String email, String contraseña) {
		this.nombre = nombre;
		this.email = email;
		this.contraseña = contraseña;
	}
	
	
	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getContraseña() {
		return contraseña;
	}



	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public boolean iniciarSesion(String login, String contraseña) {
		return (login.equals(this.email.substring(0, this.email.indexOf("@"))) && contraseña.equals(this.contraseña));
	}
	
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public String toString() {
		return nombre + "/" + email + "/" + contraseña;
	}
	
	public boolean autenticar(String email, String contraseña) {
		return this.email.equals(email) && this.contraseña.equals(contraseña);
	}
}