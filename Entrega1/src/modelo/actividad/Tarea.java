package modelo.actividad;

import java.util.ArrayList;
import java.util.List;

public class Tarea extends Actividad {
	List<String> ejercicios;
	boolean enviada;
	
	public Tarea(String descripcion, String objetivo, String tipo, int nivelDificultad, Double duracionEsperada, List<Actividad> preRequisitos, List<String> ejercicios) {
		super(descripcion, objetivo, tipo, nivelDificultad, duracionEsperada, preRequisitos);
		this.ejercicios = ejercicios;
		this.enviada = false;
	}

	@Override
	public void editarContenido(Object ejercicios) {
		if (ejercicios.getClass() == List.class) {
			this.ejercicios = (List<String>) ejercicios;
		}
	}
	
	public void enviarTarea(List<String> respuestas) {
		enviada = true;
	}

	@Override
	public String interactuar() { 
		String cadEjercicios = "";
		if (ejercicios.size() > 0) {
			for (String ejercicio: ejercicios) {
				cadEjercicios += ejercicio + "/n";
			}
			cadEjercicios = cadEjercicios.substring(0, cadEjercicios.length()-1);
		}else {
			cadEjercicios = "NA";
		}
		return cadEjercicios;
	}
	
	@Override
	public Actividad copy() {
		Tarea copia = new Tarea(getDescripcion(), getObjetivo(), getTipo(), getNivelDificultad(), getDuracionEsperada(), getPreRequisitos(), ejercicios);
		copia.cambiarResultado(this.getResultado());
		return copia;
	}
	
	@Override
	public String toString() {
		String cadena = "";
		if (ejercicios.size() > 0) {
			for (String ejercicio: ejercicios) {
				cadena += ejercicio + "//";
			}
			cadena = cadena.substring(0, cadena.length()-2);
		}else {
			cadena = "NA";
		}
		return super.toString() + cadena;
	}
}