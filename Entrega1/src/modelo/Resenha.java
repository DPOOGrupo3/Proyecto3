package modelo;

public class Resenha {
	private String comentario;
	private double calificaicon;
	
	public Resenha(String comentario, double calificaicon) {
		this.comentario = comentario;
		this.calificaicon = calificaicon;
	}

	public String getComentario() {
		return comentario;
	}

	public double getCalificaicon() {
		return calificaicon;
	}
}