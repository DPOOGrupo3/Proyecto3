package modelo;

import modelo.CentralLogica;
import modelo.LearningPath;
import modelo.actividad.*;
import modelo.actividad.examen.*;
import modelo.usuario.*;
import persistencia.CentralPersistencia;

import java.util.ArrayList;
import java.util.List;

public class DatosIniciales {

	public static void main(String[] args) {
	    List<Profesor> profesores = new ArrayList<>();
	    List<Estudiante> estudiantes = new ArrayList<>();
	    List<LearningPath> caminos = new ArrayList<>();
	    List<Actividad> actividades = new ArrayList<>();

	    CentralPersistencia persistencia = new CentralPersistencia();

	    
	    inicializarProfesores(profesores);
	    inicializarEstudiantes(estudiantes);

	    for (String titulo : new String[]{"Basket", "Arquitectura empresarial", "Infratec", "Materiales", "DPOO",
	                                       "Álgebra", "Diseño sistemas satelitales", "Laboratorio Materiales",
	                                       "EDA", "Cálculo diferencial"}) {
	        List<Actividad> actividadesLP = crearActividadesParaLearningPath(titulo, actividades);
	        LearningPath camino = new LearningPath(titulo, "Descripción de " + titulo, "Objetivo de " + titulo, actividadesLP);
	        caminos.add(camino);
	        actividades.addAll(actividadesLP); 
	    }

	    
	    asignarLearningPathsAProfesores(profesores, caminos);
	    asignarLearningPathsAEstudiantes(estudiantes, caminos);
	    
	    
	    persistencia.guardarDatos(profesores, estudiantes, caminos, actividades);
	    System.out.println("Datos iniciales guardados correctamente.");
	}
	
	private static void inicializarProfesores(List<Profesor> profesores) {
	    profesores.add(new Profesor("Jeronimo Vasquez", "j.vasquez@gmail.com", "Jero123"));
	    profesores.add(new Profesor("Jacobo Zarruk", "j.zarruk@gmail.com", "Jacobo123"));
	    profesores.add(new Profesor("Jairo Fierro", "j.fierro@gmail.com", "Jairo123"));
	    profesores.add(new Profesor("Juan Pablo Arenas", "j.arenas@gmail.com", "Juan123"));
	    profesores.add(new Profesor("Daniel Quintero", "d.quintero@gmail.com", "Daniel123"));
	}
	
	private static void inicializarEstudiantes(List<Estudiante> estudiantes) {
	    estudiantes.add(new Estudiante("Sofia Zarruk", "sofia.z@gmail.com", "Sofia123"));
	    estudiantes.add(new Estudiante("Juliana Colmenares", "juliana.c@gmail.com", "Juliana123"));
	    estudiantes.add(new Estudiante("David Sanabria", "david.s@gmail.com", "David123"));
	    estudiantes.add(new Estudiante("Laura Sofia", "laura.s@gmail.com", "Laura123"));
	    estudiantes.add(new Estudiante("Valeria Suarez", "valeria.s@gmail.com", "Valeria123"));
	    estudiantes.add(new Estudiante("Angel Farfan", "angel.f@gmail.com", "Angel123"));
	    estudiantes.add(new Estudiante("Sebastina Gomez", "sebastian.g@gmail.com", "Sebastian123"));
	    estudiantes.add(new Estudiante("Sebastian Rodriguez", "sebastian.r@gmail.com", "SebastianR123"));
	    estudiantes.add(new Estudiante("La hermana de jairo", "AuraHermosa@gmail.com", "AuritaLinda"));
	    estudiantes.add(new Estudiante("Jimmy Mujica", "jimmy.m@gmail.com", "Jimmy123"));
	}
	
	private static void asignarLearningPathsAProfesores(List<Profesor> profesores, List<LearningPath> caminos) {
	    
	    profesores.get(0).cargarLearninPathsCreados(
	        List.of(caminos.get(0).getID(), caminos.get(1).getID()), caminos);
	    profesores.get(1).cargarLearninPathsCreados(
	        List.of(caminos.get(2).getID(), caminos.get(3).getID()), caminos);
	    profesores.get(2).cargarLearninPathsCreados(
	        List.of(caminos.get(4).getID(), caminos.get(5).getID()), caminos);
	    profesores.get(3).cargarLearninPathsCreados(
	        List.of(caminos.get(6).getID(), caminos.get(7).getID()), caminos);
	    profesores.get(4).cargarLearninPathsCreados(
	        List.of(caminos.get(8).getID(), caminos.get(9).getID()), caminos);
	}
	
	private static void asignarLearningPathsAEstudiantes(List<Estudiante> estudiantes, List<LearningPath> caminos) {
	    estudiantes.get(0).inscribirCamino(caminos.get(0));
	    estudiantes.get(0).inscribirCamino(caminos.get(1));
	    
	    estudiantes.get(1).inscribirCamino(caminos.get(2));
	    estudiantes.get(1).inscribirCamino(caminos.get(3));
	    
	    estudiantes.get(2).inscribirCamino(caminos.get(4));
	    estudiantes.get(2).inscribirCamino(caminos.get(5));
	    
	    estudiantes.get(3).inscribirCamino(caminos.get(6));
	    estudiantes.get(3).inscribirCamino(caminos.get(7));
	    
	    estudiantes.get(4).inscribirCamino(caminos.get(8));
	    estudiantes.get(4).inscribirCamino(caminos.get(9));
	    
	    estudiantes.get(5).inscribirCamino(caminos.get(0));
	    estudiantes.get(5).inscribirCamino(caminos.get(2));
	    
	    estudiantes.get(6).inscribirCamino(caminos.get(3));
	    estudiantes.get(6).inscribirCamino(caminos.get(5));
	    
	    estudiantes.get(7).inscribirCamino(caminos.get(6));
	    estudiantes.get(7).inscribirCamino(caminos.get(8));
	}


    private static List<Actividad> crearActividadesParaLearningPath(String titulo, List<Actividad> actividadesGlobales) {
        List<Actividad> actividades = new ArrayList<>();
        
        
        RecursoEducativo recurso = new RecursoEducativo("Recurso educativo para " + titulo, "Objetivo del recurso", 
                                                        "RE", 2, 2.0, new ArrayList<>(), 
                                                        "PDF del recurso", "PDF");
        Tarea tarea = new Tarea("Tarea de " + titulo, "Objetivo de la tarea", "T", 3, 3.0, new ArrayList<>(), 
                                List.of("Ejercicio 1", "Ejercicio 2"));
        Quiz quiz = new Quiz("Quiz de " + titulo, "Objetivo del quiz", "Q", 4, 1.5, new ArrayList<>(), 
                             List.of("Pregunta 1", "Pregunta 2"), List.of("Respuesta 1", "Respuesta 2"), 75.0);
        Parcial parcial = new Parcial("Parcial de " + titulo, "Objetivo del parcial", "P", 5, 4.0, new ArrayList<>(), 
                                      List.of("Pregunta abierta 1", "Pregunta abierta 2"));
        Encuesta encuesta = new Encuesta("Encuesta de " + titulo, "Objetivo de la encuesta", "E", 1, 1.0, 
                                         new ArrayList<>(), List.of("Pregunta de opinión 1"));

        actividades.add(recurso);
        actividades.add(tarea);
        actividades.add(quiz);
        actividades.add(parcial);
        actividades.add(encuesta);
        return actividades;
    }
    
    
}
