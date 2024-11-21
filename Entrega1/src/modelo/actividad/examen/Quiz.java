package modelo.actividad.examen;

import java.util.List;

import modelo.actividad.Actividad;

public class Quiz extends Examen {
	private List<String> respuestas;
	private double calificacionMin;
	
	public Quiz(String descripcion, String objetivo, String tipo, int nivelDificultad, Double duracionEsperada, List<Actividad> preRequisitos, List<String> preguntas, List<String> respuestas, Double calificacionMin) {
		super(descripcion, objetivo, tipo, nivelDificultad, duracionEsperada, preRequisitos, preguntas);
		this.respuestas = respuestas;
		this.calificacionMin = calificacionMin;
	}

	public List<String> getRespuestas() {
		return respuestas;
	}

	public void editarRespuestas(List<String> respuestas) {
		this.respuestas = respuestas;
	}

	public double getCalificacionMin() {
		return calificacionMin;
	}
	
	public void cambiarCalificacionMinima(Double calificacionMin) {
		this.calificacionMin = calificacionMin;
	}
	
	@Override
	public Actividad copy() {
		Quiz copia = new Quiz(getDescripcion(), getObjetivo(), getTipo(), getNivelDificultad(), getDuracionEsperada(), getPreRequisitos(), getPreguntas(), respuestas, calificacionMin);
		copia.cambiarResultado(this.getResultado());
		return copia;
	}
	
	@Override
	public String toString() {
		String cadenaRespuestas = "";
		if (respuestas.size() > 0) {
			for (String respuesta: respuestas) {
				cadenaRespuestas += respuesta + "//";
			}
			cadenaRespuestas = cadenaRespuestas.substring(0, cadenaRespuestas.length()-2);
		}else {
			cadenaRespuestas = "NA";
		}
		return super.toString() + ":/:" + cadenaRespuestas + ":/:"  + calificacionMin;
	}
}