package persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import modelo.LearningPath;
import modelo.actividad.Actividad;

public class PersistenciaLearningPaths {
	private String[] titulos = {"ID", "titulo", "descripcion", "objetivo", "duracion", "dificultad", "rating", "raters", "actividades", "fechaCreacion", "fechaModificacion", "version"};
	
	
	/**
	 * Método para cargar caminos de aprendizaje desde un archivo JSON.
	 * @param ruta Ruta del archivo JSON que contiene los caminos de aprendizaje.
	 * @param learningPaths Lista donde se almacenarán los caminos cargados.
	 * @param actividades Lista completa de actividades necesarias para los caminos.
	 * @throws IOException Si ocurre un error al leer el archivo.
	 * @throws JSONException Si hay un error al interpretar el archivo JSON.
	 */
	public void cargarArchivo(String ruta, List<LearningPath> caminos, List<Actividad> actividadesCompleta) throws JSONException, IOException {
		JSONArray jCaminos = new JSONArray(new String(Files.readAllBytes(new File(ruta).toPath())));
		List<JSONObject> caminosOrdenados = new ArrayList<JSONObject>();
		for (int i = 0; i < jCaminos.length(); i++) {
			JSONObject jCamino = jCaminos.getJSONObject(i);
			caminosOrdenados.add(jCamino);
		}
		if (caminosOrdenados.size() > 0) {
			caminosOrdenados.sort((camino1, camino2) -> comparatorID(camino1.getInt("ID"), camino2.getInt("ID")));
		}
		for (int i = 0; i < 10; i++) {
			JSONObject jCamino = caminosOrdenados.get(i);
			JSONArray jActividades = jCamino.getJSONArray("actividades");
			List<Actividad> actividades = obtenerActividades(jActividades, actividadesCompleta);
			LearningPath camino = new LearningPath(jCamino.getString(titulos[1]), jCamino.getString(titulos[2]), jCamino.getString(titulos[3]), actividades);
			cargarDatos(camino, jCamino.getInt(titulos[7]), jCamino.getDouble(titulos[6]), jCamino.getInt(titulos[11]), jCamino.getString(titulos[9]), jCamino.getString(titulos[10]));
			caminos.add(camino);
		}
	}
	
	/**
	 * Método privado para obtener actividades desde un JSONArray.
	 * 
	 * @param jActividades JSONArray de actividades.
	 * @param actividadesCompleta Lista de actividades completas disponibles.
	 * @return Lista de actividades relacionadas a partir de los IDs en jActividades.
	 */
	
	private List<Actividad> obtenerActividades(JSONArray jActividades, List<Actividad> actividadesCompleta) {
		List<Actividad> actividades = new ArrayList<Actividad>();
		for (int i = 0; i < jActividades.length(); i++) {
			for (int j = 0; j < actividadesCompleta.size(); j++) {
				if (String.valueOf(actividadesCompleta.get(j).getID()).equals(jActividades.getString(i))) {
					actividades.add(actividadesCompleta.get(j));
					break;
				}
			}
		}
		return actividades;
	}
	
	/**
	 * Método privado para convertir una cadena de texto en un objeto Date.
	 * 
	 * @param fecha Fecha en formato String (por ejemplo, "Sep 12 2022 10:30:00").
	 * @return Un objeto Date representando la fecha.
	 */
	
	private void cargarDatos(LearningPath camino, int raters, double rating, int version, String fechaCreacion, String fechaModificacion) {
		camino.cambiarDuracionEsperada();
		camino.cambiarNivelDificultad();
		for (int i = 0; i < raters; i++) {
			camino.ratePath(rating);
		}
		camino.setVersion(version);
		camino.setFechaCreacion(obtenerFecha(fechaCreacion));
		camino.setFechaModificacion(obtenerFecha(fechaModificacion));
	}
	
	/**
	 * Método privado para convertir una cadena de texto en un objeto Date.
	 * 
	 * @param fecha Fecha en formato String (por ejemplo, "Sep 12 2022 10:30:00").
	 * @return Un objeto Date representando la fecha.
	 */
	
	private Date obtenerFecha(String fecha) {
		String[] arrayFecha = fecha.split(" ");
		int mes = 0;
		String[] meses = {"Jan", "Feb", "Mar", "Apr", "May",
	            "Jun", "Jul", "Agu", "Sep", "Oct", "Noc", "Dec"};
		for (int i = 0; i < meses.length; i++) {
			if (arrayFecha[1].equals(meses[i])) {
				mes = i;
			}
		}
		String[] hora = arrayFecha[3].split(":");
		return new Date(Integer.parseInt(arrayFecha[5]) - 1900, mes, Integer.parseInt(arrayFecha[2]), Integer.parseInt(hora[0]), Integer.parseInt(hora[1]), Integer.parseInt(hora[2]));
	}
	
	/**
	 * Método para guardar una lista de LearningPaths en un archivo JSON.
	 * 
	 * @param ruta Ruta del archivo donde se guardarán los caminos de aprendizaje.
	 * @param caminos Lista de LearningPaths a guardar.
	 */
	
	public void guardarArchivo(String ruta, List<LearningPath> caminos) {
		JSONArray jCaminos = new JSONArray();
		
		for (LearningPath camino: caminos) {
			JSONObject jCamino = new JSONObject();
			String[] caminoArray = camino.toString().split("/");
			
			for (int i = 0; i < titulos.length; i++) {
				if (i != 8) {
					jCamino.put(titulos[i], caminoArray[i]);
				}
			}
			
			JSONArray jActividades = new JSONArray();
			
			if (!caminoArray[8].equals("NA")) {
				String[] actividades = caminoArray[8].split("%");
				
				for (String actividad: actividades) {
					jActividades.put(actividad);
				}
			}
			
			jCamino.put(titulos[8], jActividades);
			jCaminos.put(jCamino);
		}
		try {
			PrintWriter pw = new PrintWriter(ruta);
			jCaminos.write(pw, 2, 0);
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