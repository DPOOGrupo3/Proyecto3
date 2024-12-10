package persistencia;

import java.io.IOException;
import java.util.List;

import modelo.LearningPath;
import modelo.actividad.Actividad;
import modelo.usuario.*;

public class CentralPersistencia {
	private static String RutaUsuarios = "datos/usuarios.json";
	private static String RutaCaminos = "datos/caminos.json";
	private static String RutaActividades = "datos/actividades.json";
	private PersistenciaUsuarios persistenciaU = new PersistenciaUsuarios();
	private PersistenciaLearningPaths persistenciaC = new PersistenciaLearningPaths();
	private PersistenciaActividades persistenciaA = new PersistenciaActividades();
	
	/**
	 * Método para cargar los datos de usuarios, caminos de aprendizaje y actividades desde archivos JSON.
	 * @param profesores Lista de profesores a cargar.
	 * @param estudiantes Lista de estudiantes a cargar.
	 * @param caminos Lista de LearningPaths a cargar.
	 * @param actividades Lista de actividades a cargar.
	 */
	public void cargarDatos(List<Profesor> profesores, List<Estudiante> estudiantes, List<LearningPath> caminos, List<Actividad> actividades) {
		try {
			cargarActividades(actividades);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			cargarLearningPaths(caminos, actividades);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			cargarUsusarios(profesores, estudiantes, caminos);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Método para cargar las actividades desde el archivo correspondiente.
	 * @param actividades Lista donde se almacenarán las actividades cargadas.
	 * @throws IOException Si ocurre un error al leer el archivo.
	 */
	
	private void cargarActividades(List<Actividad> actividades) throws IOException {
		persistenciaA.cargarArchivo(RutaActividades, actividades);
	}
	
	/**
	 * Método para cargar los caminos de aprendizaje desde el archivo correspondiente, incluyendo sus actividades relacionadas.
	 * @param caminos Lista donde se almacenarán los caminos cargados.
	 * @param actividades Lista de actividades necesarias para los caminos.
	 * @throws IOException Si ocurre un error al leer el archivo.
	 */
	
	private void cargarLearningPaths(List<LearningPath> caminos, List<Actividad> actividades) throws IOException {
		persistenciaC.cargarArchivo(RutaCaminos, caminos, actividades);
	}
	
	/**
	 * Método para cargar usuarios (profesores y estudiantes) desde el archivo correspondiente.
	 * @param profesores Lista donde se almacenarán los profesores cargados.
	 * @param estudiantes Lista donde se almacenarán los estudiantes cargados.
	 * @param caminos Lista de caminos asociados a los usuarios.
	 * @throws IOException Si ocurre un error al leer el archivo.
	 */
	
	private void cargarUsusarios(List<Profesor> profesores, List<Estudiante> estudiantes, List<LearningPath> caminos) throws IOException {
		persistenciaU.cargarArchivo(RutaUsuarios, profesores, estudiantes, caminos);
	}
	
	/**
	 * Método para guardar los datos de usuarios, caminos de aprendizaje y actividades en sus respectivos archivos JSON.
	 * @param profesores Lista de profesores a guardar.
	 * @param estudiantes Lista de estudiantes a guardar.
	 * @param caminos Lista de LearningPaths a guardar.
	 * @param actividades Lista de actividades a guardar.
	 */
	
	public void guardarDatos(List<Profesor> profesores, List<Estudiante> estudiantes, List<LearningPath> caminos, List<Actividad> actividades) {
		try {
			guardarUsusarios(profesores, estudiantes);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			guardarLearningPaths(caminos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			guardarActividades(actividades);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Método para guardar los datos de usuarios (profesores y estudiantes) en el archivo correspondiente.
	 * @param profesores Lista de profesores a guardar.
	 * @param estudiantes Lista de estudiantes a guardar.
	 * @throws IOException Si ocurre un error al escribir el archivo.
	 */
	
	private void guardarUsusarios(List<Profesor> profesores, List<Estudiante> estudiantes) throws IOException {
		persistenciaU.guardarArchivo(RutaUsuarios, profesores, estudiantes);
	}
	
	/**
	 * Método para guardar los caminos de aprendizaje en el archivo correspondiente.
	 * @param caminos Lista de LearningPaths a guardar.
	 * @throws IOException Si ocurre un error al escribir el archivo.
	 */
	
	private void guardarLearningPaths(List<LearningPath> caminos) throws IOException {
		persistenciaC.guardarArchivo(RutaCaminos, caminos);
	}
	
	/**
	 * Método para guardar las actividades en el archivo correspondiente.
	 * @param actividades Lista de actividades a guardar.
	 * @throws IOException Si ocurre un error al escribir el archivo.
	 */
	
	private void guardarActividades(List<Actividad> actividades) throws IOException {
		persistenciaA.guardarArchivo(RutaActividades, actividades);
	}
}