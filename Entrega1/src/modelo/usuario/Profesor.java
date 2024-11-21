package modelo.usuario;

import java.util.ArrayList;
import java.util.List;

import modelo.LearningPath;
import modelo.actividad.Actividad;
import modelo.actividad.RecursoEducativo;
import modelo.actividad.Tarea;
import modelo.actividad.examen.Encuesta;
import modelo.actividad.examen.Parcial;
import modelo.actividad.examen.Quiz;

public class Profesor extends Usuario{
	private List<LearningPath> caminosCreados;
	
	public Profesor(String nombre, String email, String contraseña) {
		super(nombre, email, contraseña);
		caminosCreados = new ArrayList<LearningPath>();
	}
	
	public void cargarLearninPathsCreados(List<Integer> caminosACargar, List<LearningPath> caminosCompletos){
		if (caminosACargar.size() > 0) {
			for (int IDCamino: caminosACargar) {
				for (LearningPath camino: caminosCompletos) {
					if (camino.getID()==IDCamino) {
						caminosCreados.add(camino);
					}
				}
			}
		}
	}
	
	public Actividad crearActividad(String descripcion, String objetivo, String tipo, int nivelDificultad, Double duracionEsperada, List<Actividad> preRequisitos, String recurso, String tipoRecurso, List<String> ejercicios, List<String> preguntas, List<String> respuestas, Double calificacionMin) {
		Actividad actividad = null;
		if (tipo.equals("RE")) {
			actividad = new RecursoEducativo(descripcion, objetivo, tipo, nivelDificultad, duracionEsperada, preRequisitos, recurso, tipoRecurso);
		}else if (tipo.equals("T")) {
			actividad = new Tarea(descripcion, objetivo, tipo, nivelDificultad, duracionEsperada, preRequisitos, ejercicios);
		}else if (tipo.equals("Q")) {
			actividad = new Quiz(descripcion, objetivo, tipo, nivelDificultad, duracionEsperada, preRequisitos, preguntas, respuestas, calificacionMin);
		}else if (tipo.equals("P")) {
			actividad = new Parcial(descripcion, objetivo, tipoRecurso, nivelDificultad, duracionEsperada, preRequisitos, preguntas);
		}else if (tipo.equals("E")) {
			actividad = new Encuesta(descripcion, objetivo, tipoRecurso, nivelDificultad, duracionEsperada, preRequisitos, preguntas);
		}
		return actividad;
	}
	
	public LearningPath crearLearningPath(String titulo, String descripcion, String objetivo, List<Actividad> activdades) {
		LearningPath camino = new LearningPath(titulo, descripcion, objetivo, activdades);
		caminosCreados.add(camino);
		return camino;
	}
	
	public void eliminarLearningPathCreado(LearningPath camino) {
		caminosCreados.remove(camino);
	}
	
	public void cambiarTituloLearningPath(LearningPath camino, String titulo) {
	    camino.cambiarTitulo(titulo);
	}
	
	public void cambiarDescripcionLearningPath(LearningPath camino, String descripcion) {
	    camino.cambiarDescripcion(descripcion);
	}

	public void cambiarObjetivoLearningPath(LearningPath camino, String objetivo) {
	    camino.cambiarObjetivo(objetivo);
	}
	
	public void agregarActividadLearningPath(LearningPath camino, Actividad actividadAgregar) {
		camino.agregarActivdad(actividadAgregar);
	}
	
	public void eliminarActividadLearningPath(LearningPath camino, Actividad actividadEliminar) {
		camino.eliminarActivdad(actividadEliminar);
	}
	
	public void cambiarDescripcionActividad(Actividad actividad, String descripcion) {
	    actividad.cambiarDescripcion(descripcion);
	}

	public void cambiarObjetivoActividad(Actividad actividad, String objetivo) {
	    actividad.cambiarObjetivo(objetivo);
	}
	
	public void cambiarDuracionEsperadaActividad(Actividad actividad, Double duracion, LearningPath camino) {
		actividad.cambiarDuracionEsperada(duracion);
		camino.cambiarDuracionEsperada();
	}
	
	public void cambiarNivelDificultadActividad(Actividad actividad, int dificultad, LearningPath camino) {
		actividad.cambiarNivelDificultad(dificultad);
		camino.cambiarNivelDificultad();
	}
	
	public void agregarPreRequisitosActividad(Actividad actividad, Actividad actividadAgregar) {
		actividad.agregarPreRequisito(actividadAgregar);
	}
	
	public void eliminarPreRequisitosActividad(Actividad actividad, Actividad actividadEliminar) {
		actividad.eliminarPreRequisito(actividadEliminar);
	}
	
	private String cadenaCaminos(List<LearningPath> caminos) {
		String cadenaCaminos = "";
		if (caminos.size() > 0) {
			for (LearningPath camino: caminos) {
				cadenaCaminos += camino.getID() + "%";
			}
			cadenaCaminos = cadenaCaminos.substring(0, cadenaCaminos.length()-1);
		}else {
			cadenaCaminos = "NA";
		}
		return cadenaCaminos;
	}
	
	@Override
	public String toString() {
		return super.toString() + "/" + cadenaCaminos(caminosCreados);
	}

	public List<LearningPath> getCaminosCreados() {
		return caminosCreados;
	}
}