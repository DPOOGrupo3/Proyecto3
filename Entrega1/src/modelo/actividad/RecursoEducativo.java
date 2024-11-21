package modelo.actividad;

import java.util.List;

public class RecursoEducativo extends Actividad {
	private String recurso;
	private String tipoRecurso;
	
	public RecursoEducativo(String descripcion, String objetivo, String tipo, int nivelDificultad, Double duracionEsperada, List<Actividad> preRequisitos, String recurso, String tipoRecurso) {
		super(descripcion, objetivo, tipo, nivelDificultad, duracionEsperada, preRequisitos);
		this.recurso = recurso;
		this.tipoRecurso = tipoRecurso;
	}

	@Override
	public void editarContenido(Object recurso) {
		this.recurso = (String) recurso;
	}

	@Override
	public String interactuar() {
		String cadenaImprimir = "";
		if (tipoRecurso.equals("Video")) {
			cadenaImprimir = "Aquí tienes la forma de acceder al video: ";
		}else if (tipoRecurso.equals("Sitio Web")) {
			cadenaImprimir = "Aquí tienes la URL para acceder al sitio web: ";
		}else if (tipoRecurso.equals("Libro")) {
			cadenaImprimir = "Aquí tienes la forma de acceder al libro: ";
		}else if (tipoRecurso.equals("Recurso Adjunto")) {
			cadenaImprimir = "Aquí tienes la forma de acceder al recurso adjunto: ";
		}
		return cadenaImprimir + "\n" + recurso;
	}
	
	@Override
	public Actividad copy() {
		RecursoEducativo copia = new RecursoEducativo(getDescripcion(), getObjetivo(), getTipo(), getNivelDificultad(), getDuracionEsperada(), getPreRequisitos(), recurso, tipoRecurso);
		copia.cambiarResultado(this.getResultado());
		return copia;
	}
	
	@Override
	public String toString() {
		return super.toString() + recurso + ":/:" + tipoRecurso;
	}
}