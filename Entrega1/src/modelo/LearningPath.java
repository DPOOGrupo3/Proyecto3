package modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import modelo.actividad.Actividad;
import modelo.usuario.Estudiante;

public class LearningPath {
	private static int cantLP;
	private int ID;
	private String titulo;
	private String descripcion;
	private String objetivo;
	private double duracionEsperada;
	private int nivelDificultad;
	private double rating;
	private int raters;
	private List<Actividad> actividades;
	private List<Resenha> resenhas;
	private List<Estudiante> inscripciones;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private int version;
	
	public LearningPath(String titulo, String descripcion, String objetivo, List<Actividad> actividades) {
		this.ID = cantLP++;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivo = objetivo;
		this.duracionEsperada = 0;
		this.nivelDificultad = 0;
		this.rating = 0;
		this.raters = 0;
		this.actividades = new ArrayList<Actividad>();
		for (Actividad actividad: actividades) {
			this.actividades.add(actividad);
		}
		resenhas = new ArrayList<Resenha>();
		inscripciones = new ArrayList<Estudiante>();
		fechaCreacion = new Date();
		fechaModificacion = fechaCreacion;
		version = 1;
	}
	
	public int getID() {
		return ID;
	}

	public String getTitulo() {
		return titulo;
	}

	public void cambiarTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void cambiarDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void cambiarObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public double getDuracionEsperada() {
		return duracionEsperada;
	}

	public void cambiarDuracionEsperada() {
		if (actividades.size() > 0) {
			duracionEsperada = 0;
			for (Actividad actividad: actividades) {
				duracionEsperada += actividad.getDuracionEsperada();
			}
		}
	}

	public int getNivelDificultad() {
		return nivelDificultad;
	}

	public void cambiarNivelDificultad() {
		if (actividades.size() > 0) {
			nivelDificultad = 0;
			for (Actividad actividad: actividades) {
				nivelDificultad += actividad.getDuracionEsperada();
			}
			nivelDificultad = nivelDificultad / actividades.size();
		}
	}

	public double getRating() {
		return rating;
	}
	
	public void ratePath(double rate) {
		rating = ((rating * raters) + rate)/(++raters);
	}
	
	public double getRaters() {
		return rating;
	}

	public List<Actividad> getActivdades() {
		return actividades;
	}
	
	public int getCantidadActivdades() {
		return actividades.size();
	}

	public void agregarActivdad(Actividad activdad) {
		actividades.add(activdad);
	}
	
	public void eliminarActivdad(Actividad activdad) {
		try {
			actividades.remove(activdad);
		} catch (Exception e){
			System.out.println("La actividad que se desea eliminar no ha sido encontrada en este Learning Path");
		}
	}
	
	public void agregarResenha(Resenha resenha) {
		resenhas.add(resenha);
		ratePath(resenha.getCalificaicon());
	}
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	public double getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public void cambiarVersion() {
		version += 1;
		setFechaModificacion(new Date());
	}
	
	public LearningPath inscribirCamino(Estudiante estudiante) {
		inscripciones.add(estudiante);
		return this;
	}
	
	private String generarIDActividad() {
		return ID + "." +String.valueOf(actividades.size() + 1);
	}
	
	@Override
	public String toString() {
		String cadenaActividades = "";
		if (actividades.size() > 0) {
			for (Actividad actividad: actividades) {
				cadenaActividades += actividad.getID() + "%";
			}
		}else {
			cadenaActividades = "NA";
		}
		return ID + "/" +titulo + "/" + descripcion + "/" + objetivo + "/" + duracionEsperada + "/" + nivelDificultad + "/" + rating + "/" + raters + "/" + cadenaActividades + "/" + fechaCreacion + "/" + fechaModificacion + "/" + version;
	}
}