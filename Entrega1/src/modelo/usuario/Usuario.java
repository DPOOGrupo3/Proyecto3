package modelo.usuario;

public abstract class Usuario {
	private String nombre;
	private String email;
	private String contrasena;
	
	public Usuario(String nombre, String email, String contrasena) {
		this.nombre = nombre;
		this.email = email;
		this.contrasena = contrasena;
	}
	
	
	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getContrasena() {
		return contrasena;
	}



	public void setContraseña(String contrasena) {
		this.contrasena = contrasena;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public boolean iniciarSesion(String login, String contraseña) {
		return (login.equals(this.email.substring(0, this.email.indexOf("@"))) && contraseña.equals(this.contrasena));
	}
	
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public String toString() {
		return nombre + "/" + email + "/" + contrasena;
	}
	
	public boolean autenticar(String email, String contraseña) {
		return this.email.equals(email) && this.contrasena.equals(contraseña);
	}
}