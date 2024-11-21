package modelo.actividad.examen;

import java.util.List;

import modelo.actividad.Actividad;

public abstract class Examen extends Actividad {
	private List<String> preguntas; //lista de preguntas
	
	public Examen(String descripcion, String objetivo, String tipo, int nivelDificultad, Double duracionEsperada, List<Actividad> preRequisitos, List<String> preguntas) {
		super(descripcion, objetivo, tipo, nivelDificultad, duracionEsperada, preRequisitos);
		this.preguntas = preguntas;
	}
	
	public List<String> getPreguntas() {
		return preguntas;
	}
	
	@Override
	public void editarContenido(Object preguntas) {
		if (preguntas.getClass() == List.class) {
			this.preguntas = (List<String>) preguntas;
		}
	}
	
	@Override
	public String interactuar() {
		String cadPreguntas  = "";
		if (preguntas.size() > 0) {
			for (String pregunta: preguntas) {
				cadPreguntas += pregunta + "/n";
			}
			cadPreguntas = cadPreguntas.substring(0, cadPreguntas.length()-1);
		}else {
			cadPreguntas = "NA";
		}
		return cadPreguntas;
	}
	
	@Override
	public String toString() {
		String cadena = "";
		if (preguntas.size() > 0) {
			for (String pregunta: preguntas) {
				cadena += pregunta + "//";
			}
			cadena = cadena.substring(0, cadena.length()-2);
		}else {
			cadena = "NA";
		}
		return super.toString() + cadena;
	}
}