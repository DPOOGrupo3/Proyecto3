package persistencia;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import modelo.LearningPath;
import modelo.usuario.Estudiante;
import modelo.usuario.Profesor;
import modelo.usuario.Usuario;

public class PersistenciaUsuarios {
	private String[] titulos = {"nombre", "correo", "contrasena", "caminosCreados", "caminosInscritos"};
	
	
	/**
	 * Método para cargar usuarios desde un archivo JSON.
	 * @param ruta Ruta del archivo JSON que contiene los usuarios.
	 * @param profesores Lista donde se almacenarán los profesores cargados.
	 * @param estudiantes Lista donde se almacenarán los estudiantes cargados.
	 * @param learningPaths Lista de caminos de aprendizaje asociados a los usuarios.
	 * @throws IOException Si ocurre un error al leer el archivo.
	 * @throws JSONException Si hay un error al interpretar el archivo JSON.
	 */
	public void cargarArchivo(String ruta, List<Profesor> profesores, List<Estudiante> estudiantes, List<LearningPath> caminosCompletos) throws IOException {
		JSONObject usuarios = new JSONObject(new String(Files.readAllBytes(new File(ruta).toPath())));
		
		cargarProfesores(profesores, caminosCompletos, usuarios.getJSONArray("profesores"));
		cargarEstudiantes(estudiantes, caminosCompletos, usuarios.getJSONArray("estudiantes"));
	}
	
	/**
	 * Método para obtener un objeto Usuario desde un JSONObject.
	 * @param usuarioJSON JSONObject que representa el usuario.
	 * @param caminos Lista de caminos de aprendizaje disponibles.
	 * @return Objeto Usuario creado a partir del JSONObject.
	 */
	
	private void cargarProfesores(List<Profesor> profesores, List<LearningPath> caminosCompletos, JSONArray jProfesores) {
		for (int i = 0; i < jProfesores.length(); i++) {
			JSONObject jProfesor = jProfesores.getJSONObject(i);
			Profesor profesor = new Profesor(jProfesor.getString(titulos[0]), jProfesor.getString(titulos[1]), jProfesor.getString(titulos[2]));
			JSONArray jCaminos = jProfesor.getJSONArray(titulos[3]);
			List<Integer> caminos = new ArrayList<Integer>();
			for (int j = 0; j < jCaminos.length(); j++) {
				caminos.add(jCaminos.getInt(j));
			}
			profesor.cargarLearninPathsCreados(caminos, caminosCompletos);
			profesores.add(profesor);
		}
	}
	
	/**
	 * Método para convertir una lista de caminos de aprendizaje a una lista de sus identificadores.
	 * @param caminos Lista de caminos de aprendizaje.
	 * @return Lista de identificadores de los caminos proporcionados.
	 */
	private void cargarEstudiantes(List<Estudiante> estudiantes, List<LearningPath> caminosCompletos, JSONArray jEstudiantes) {
		for (int i = 0; i < jEstudiantes.length(); i++) {
			JSONObject estudiante = jEstudiantes.getJSONObject(i);
			Estudiante student = new Estudiante(estudiante.getString(titulos[0]), estudiante.getString(titulos[1]), estudiante.getString(titulos[2]));
			JSONArray jCaminos = estudiante.getJSONArray(titulos[4]);
			List<Integer> caminos = new ArrayList<Integer>();
			List<Integer> actividades = new ArrayList<Integer>();
			for (int j = 0; j < jCaminos.length(); j++) {
				JSONObject camino = jCaminos.getJSONObject(j);
				caminos.add(camino.getInt("ID"));
				JSONArray jActividadesterminadas = camino.getJSONArray("actividadesTerminadas");
				for (int k = 0; k < jActividadesterminadas.length(); k++) {
					actividades.add(jActividadesterminadas.getInt(k));
				}
			}
			student.cargarDatos(caminos, caminosCompletos, actividades);
			estudiantes.add(student);
		}
	}
	
	/**
	 * Método para guardar una lista de usuarios (profesores y estudiantes) en un archivo JSON.
	 * @param ruta Ruta del archivo donde se guardarán los usuarios.
	 * @param profesores Lista de profesores a guardar.
	 * @param estudiantes Lista de estudiantes a guardar.
	 * @throws IOException Si ocurre un error al escribir el archivo.
	 */
	
	public void guardarArchivo(String ruta, List<Profesor> profesores, List<Estudiante> estudiantes) {
		JSONObject usuarios = new JSONObject();
		JSONArray jProfesores = new JSONArray();
		JSONArray jEstudiantes = new JSONArray();
		
		for (Profesor profesor: profesores) {
			JSONObject profe = new JSONObject();
			String[] atributosProfesor = profesor.toString().split("/");
			
			for (int i = 0; i < 3; i++) {
				profe.put(titulos[i], atributosProfesor[i]);
			}
			
			JSONArray jCaminosCreados = new JSONArray();
			
			if (!atributosProfesor[3].equals("NA")) {
				String[] caminos = atributosProfesor[3].split("%");
				for (String camino: caminos) {
					jCaminosCreados.put(camino);
				}
			}
			profe.put(titulos[3], jCaminosCreados);
			jProfesores.put(profe);
		}
		
		for (Usuario estudiante: estudiantes) {
			JSONObject student = new JSONObject();
			String[] atributosEstudiante = estudiante.toString().split("/");
			
			for (int i = 0; i < 3; i++) {
				student.put(titulos[i], atributosEstudiante[i]);
			}
			
			JSONArray caminosInscritos = new JSONArray();
			
			if (!atributosEstudiante[3].equals("NA")) {
				String[] caminoTitulos = {"ID", "porcentaje", "actividadesTerminadas", "actividadesPendientes"};
				String[] caminos = atributosEstudiante[3].split("%");
				for (String camino: caminos) {
					JSONObject jCamino = new JSONObject();
					String[] atributosCamino = camino.split("-");
					for (int i = 0; i < atributosCamino.length; i++) {
						if (i < 2) {
							jCamino.put(caminoTitulos[i], atributosCamino[i]);
						} else {
							JSONArray jActividades = new JSONArray();
							if (!atributosCamino[i].equals("NA")) {
								String[] actividades = atributosCamino[i].split("::");
								for (String actividad: actividades) {
									jActividades.put(actividad);
								}
							}
							jCamino.put(caminoTitulos[i], jActividades);
						}
					}
					caminosInscritos.put(jCamino);
				}
			}
			student.put(titulos[4], caminosInscritos);
			jEstudiantes.put(student);
		}
		
		usuarios.put("profesores", jProfesores);
		usuarios.put("estudiantes", jEstudiantes);
		
		try {
			PrintWriter pw = new PrintWriter(ruta);
			usuarios.write(pw, 2, 0);
	        pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método privado para comparar dos ID de LearningPaths.
	 * 
	 * @param first Primer ID.
	 * @param second Segundo ID.
	 * @return -1 si el primer ID es menor, 1 si el segundo ID es menor, 0 si son iguales.
	 */
	
	private int comparatorID(int first, int second) {
		if (first < second) {
			return -1;
		} else if (first > second) {
			return 1;
		}
		return 0;
	}
}