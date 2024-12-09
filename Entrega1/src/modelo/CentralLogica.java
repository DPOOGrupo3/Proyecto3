package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.actividad.Actividad;
import modelo.usuario.*;
import persistencia.CentralPersistencia;

public class CentralLogica {
	public List<Profesor> profesores;
	private List<Estudiante> estudiantes;
	private List<LearningPath> caminos;
	private List<Actividad> actividades;
	private CentralPersistencia centralP = new CentralPersistencia();
	private Usuario user;
	
	public CentralLogica() {
		profesores = new ArrayList<Profesor>();
		estudiantes = new ArrayList<Estudiante>();
		caminos = new ArrayList<LearningPath>();
		actividades = new ArrayList<Actividad>();
	}
	
	public void cargarDatos() {
		centralP.cargarDatos(profesores, estudiantes, caminos, actividades);
	}
	
	public void guardarDatos() {
		centralP.guardarDatos(profesores, estudiantes, caminos, actividades);
	}
	
	public Usuario iniciarSesion(String email, String contrasena) {
		for (Profesor profesor: profesores) {
			if (profesor.iniciarSesion(email, contrasena)) {
				user = profesor;
				break;
			}
		}
		for (Estudiante estudiante: estudiantes) {
			if (estudiante.iniciarSesion(email, contrasena)) {
				user = estudiante;
				break;
			}
		}
		return user;
	}
	
	
	
	public LearningPath crearLearningPath(String titulo, String descripcion, String objetivo, List<Integer> IDActivdades) {
		List<Actividad> activdades = new ArrayList<Actividad>();
		for (int ID: IDActivdades) {
			activdades.add(encontrarActividadPorID(ID));
		}
		LearningPath camino = ((Profesor) user).crearLearningPath(titulo, descripcion, objetivo, activdades);
		caminos.add(camino);
		return camino;
	}
	
	public Actividad crearActividad(String descripcion, String objetivo, String tipo, int nivelDificultad, Double duracionEsperada, List<Integer> preRequisitos, String recurso, String tipoRecurso, List<String> ejercicios, List<String> preguntas, List<String> respuestas, double calificacionMin) {
		List<Actividad> listaPreRequisitos = new ArrayList<Actividad>();
		for (int pre: preRequisitos) {
			listaPreRequisitos.add(encontrarActividadPorID(pre));
		}
		Actividad actividad = ((Profesor) user).crearActividad(descripcion, objetivo, tipo, nivelDificultad, duracionEsperada, listaPreRequisitos, recurso, tipoRecurso, ejercicios, preguntas, respuestas, calificacionMin);
		actividades.add(actividad);
		return actividad;
	}
	
	private LearningPath encontrarLearningPathPorID(int IDCamino) {
		for (LearningPath camino: caminos) {
			if (camino.getID()==IDCamino) {
				return camino;
			}
		}
		return new LearningPath("", "", "", new ArrayList<Actividad>());
	}
	
	private Actividad encontrarActividadPorID(int IDActividad) {
		for (Actividad actividad: actividades) {
			if (actividad.getID() == IDActividad) {
				return actividad;
			}
		}
		return null;
	}
	
	private LearningPath encontrarLearningPathPorIDActividad(int IDActividad) {
		for (LearningPath camino: caminos) {
			for (Actividad actividad: camino.getActivdades()) {
				if (actividad.getID() == IDActividad) {
					return camino;
				}
			}
		}
		return null;
	}
	
	public void copiarActividad(int IDActividad) {
	    Actividad actividadOriginal = encontrarActividadPorID(IDActividad);
	    if (actividadOriginal != null) {
	        Actividad actividadCopia = actividadOriginal.copy();
	        actividades.add(actividadCopia);
	    }
	}
	
	public void eliminarLearningPathCreado(int IDCamino) {
		LearningPath camino = encontrarLearningPathPorID(IDCamino);
		if (!camino.equals(null)) {
			((Profesor) user).eliminarLearningPathCreado(camino);
			caminos.remove(camino);
		}
	}
	
	public void cambiarTituloLearningPath(int IDCamino, String titulo) {
		LearningPath camino = encontrarLearningPathPorID(IDCamino);
		if (!camino.equals(null)) {
			((Profesor) user).cambiarTituloLearningPath(camino, titulo);
		}
	}
	
	public void cambiarDescrpcionLearningPath(int IDCamino, String descripcion) {
		LearningPath camino = encontrarLearningPathPorID(IDCamino);
		if (!camino.equals(null)) {
			((Profesor) user).cambiarDescripcionLearningPath(camino, descripcion);
		}
	}
	
	public void cambiarObjetivoLearningPath(int IDCamino, String objetivo) {
		LearningPath camino = encontrarLearningPathPorID(IDCamino);
		if (!camino.equals(null)) {
			((Profesor) user).cambiarObjetivoLearningPath(camino, objetivo);
		}
	}
	
	public void agregarActividadLearningPath(int IDCamino, int IDActividad) {
		LearningPath camino = encontrarLearningPathPorID(IDCamino);
		Actividad actividadAgregar = encontrarActividadPorID(IDActividad);
		if (!camino.equals(null) && !actividadAgregar.equals(null)) {
			((Profesor) user).agregarActividadLearningPath(camino, actividadAgregar);
		}
	}
	
	public void eliminarsActividadLearningPath(int IDCamino, int IDActividad) {
		LearningPath camino = encontrarLearningPathPorID(IDCamino);
		Actividad actividadEliminar = encontrarActividadPorID(IDActividad);
		if (!camino.equals(null) && !actividadEliminar.equals(null)) {
			((Profesor) user).eliminarActividadLearningPath(camino, actividadEliminar);
			actividades.remove(actividadEliminar);
		}
	}
	
	public void cambiarDescrpcionActividad(int IDActividad, String descripcion) {
		Actividad actividad = encontrarActividadPorID(IDActividad);
		((Profesor) user).cambiarDescripcionActividad(actividad, descripcion);
	}
	
	public void cambiarObjetivoActividad(int IDActividad, String objetivo) {
		Actividad actividad = encontrarActividadPorID(IDActividad);
		if (!actividad.equals(null)) {
			((Profesor) user).cambiarObjetivoActividad(actividad, objetivo);
		}
	}
	
	public void cambiarDuracionEsperadaActividad(int IDActividad, Double duracion) {
		Actividad actividad = encontrarActividadPorID(IDActividad);
		LearningPath camino = encontrarLearningPathPorIDActividad(IDActividad);
		if (!actividad.equals(null) && !camino.equals(null)) {
			((Profesor) user).cambiarDuracionEsperadaActividad(actividad, duracion, camino);
		}
	}
	
	public void cambiarNivelDificultadActividad(int IDActividad, int dificultad) {
		Actividad actividad = encontrarActividadPorID(IDActividad);
		LearningPath camino = encontrarLearningPathPorIDActividad(IDActividad);
		if (!actividad.equals(null) && !camino.equals(null)) {
			((Profesor) user).cambiarNivelDificultadActividad(actividad, dificultad, camino);
		}
	}
	
	public void agregarPreRequisitosActividad(int IDActividad, int IDActividadAgregar) {
		Actividad actividad = encontrarActividadPorID(IDActividad);
		Actividad actividadAgregar = encontrarActividadPorID(IDActividadAgregar);
		if (!actividad.equals(null) && !actividadAgregar.equals(null)) {
			((Profesor) user).agregarPreRequisitosActividad(actividad, actividadAgregar);
		}
	}
	
	public void eliminarPreRequisitosActividad(int IDActividad, int IDActividadEliminar) {
		Actividad actividad = encontrarActividadPorID(IDActividad);
		Actividad actividadEliminar = encontrarActividadPorID(IDActividadEliminar);
		if (!actividad.equals(null) && !actividadEliminar.equals(null)) {
			((Profesor) user).eliminarPreRequisitosActividad(actividad, actividadEliminar);
		}
	}
	
	public void actualizarVersionLP(int IDCamino) {
		LearningPath camino = encontrarLearningPathPorID(IDCamino);
		camino.cambiarVersion();
	}

	public Usuario buscarUsuario(String email, String password) {
	    
	    for (Profesor profesor : profesores) {
	        if (profesor.iniciarSesion(email, password)) {
	            return profesor; 
	        }
	    }
	    
	    for (Estudiante estudiante : estudiantes) {
	        if (estudiante.iniciarSesion(email, password)) {
	            return estudiante; 
	        }
	    }
	   
	    return null;
	}

	public void registrarUsuario(String nombre, String correo, String password, String tipoUsuario) {
	    if (tipoUsuario.equals("Profesor")) {
	    	Profesor user = new Profesor(nombre, correo, password);
	    	profesores.add(user);
	    } else if (tipoUsuario.equals("Estudiante")) {
	    	Estudiante user = new Estudiante(nombre, correo, password);
	    	estudiantes.add(user);
	    }
	}

	public List<LearningPath> obtenerLearningPathsProfesor(Profesor profesor) {
	    return profesor.getCaminosCreados();
	}

	public List<Actividad> obtenerActividadesIndependientes() {
	    List<Actividad> independientes = new ArrayList<>();
	    for (Actividad actividad : actividades) {
	        if (!esActividadParteDeLearningPath(actividad)) {
	            independientes.add(actividad);
	        }
	    }
	    return independientes;
	}

	private boolean esActividadParteDeLearningPath(Actividad actividad) {
	    for (LearningPath camino : caminos) {
	        if (camino.getActivdades().contains(actividad)) {
	            return true;
	        }
	    }
	    return false;
	}

	/**
	 * Elimina una actividad del sistema.
	 * Si la actividad está asociada a algún Learning Path, también se elimina de ellos.
	 * @param idActividad El ID de la actividad a eliminar.
	 */
	public void eliminarActividad(int idActividad) {
	    Actividad actividadAEliminar = encontrarActividadPorID(idActividad);

	    if (actividadAEliminar == null) {
	        System.out.println("Error: No se encontró la actividad con el ID proporcionado.");
	        return;
	    }

	    for (LearningPath camino : caminos) {
	        if (camino.getActivdades().contains(actividadAEliminar)) {
	            camino.eliminarActivdad(actividadAEliminar);
	            System.out.println("La actividad ha sido eliminada del Learning Path: " + camino.getID());
	        }
	    }

	    actividades.remove(actividadAEliminar);
	    System.out.println("Actividad eliminada exitosamente del sistema. ID: " + idActividad);
	}
	
	public List<LearningPath> obtenerLearningPathsDisponibles(Estudiante estudiante) {
	    List<LearningPath> disponibles = new ArrayList<>();
	    for (LearningPath camino : caminos) {
	        boolean estaInscrito = false;
	        
	            if (estudiante.getCaminosInscritos().contains(camino)) {
	                estaInscrito = true;	            
	        }
	        if (!estaInscrito) {
	            disponibles.add(camino);
	        }
	    }
	    return disponibles;
	}	
}