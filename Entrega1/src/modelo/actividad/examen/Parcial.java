package modelo.actividad.examen;

import java.util.List;

import modelo.actividad.Actividad;

public class Parcial extends Examen {
	public Parcial(String descripcion, String objetivo, String tipo, int nivelDificultad, Double duracionEsperada, List<Actividad> preRequisitos, List<String> preguntas) {
		super(descripcion, objetivo, tipo, nivelDificultad, duracionEsperada, preRequisitos, preguntas);
	}
	
	@Override
	public Actividad copy() {
		Parcial copia = new Parcial(getDescripcion(), getObjetivo(), getTipo(), getNivelDificultad(), getDuracionEsperada(), getPreRequisitos(), getPreguntas());
		copia.cambiarResultado(this.getResultado());
		return copia;
	}
}