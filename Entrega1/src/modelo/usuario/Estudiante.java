package modelo.usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.LearningPath;
import modelo.Progreso;
import modelo.actividad.Actividad;

public class Estudiante extends Usuario {
	private Map<LearningPath, Progreso> caminosInscritos;
	
	public Estudiante(String nombre, String email, String contraseña) {
		super(nombre, email, contraseña);
		this.caminosInscritos = new HashMap<LearningPath, Progreso>();
	}
	
	public void cargarDatos(List<Integer> caminosACargar, List<LearningPath> caminosCompletos, List<Integer> actividadesTerminadas) {
		if (caminosACargar.size() > 0) {
			for (int IDCamino: caminosACargar) {
				for (LearningPath camino: caminosCompletos) {
					if (camino.getID()==IDCamino) {
						inscribirCamino(camino);
						if (!actividadesTerminadas.isEmpty()) {
							for (Actividad actividad: camino.getActivdades()) {
								if (actividadesTerminadas.contains(actividad.getID())) {
									entregarActividad(camino, actividad);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void inscribirCamino(LearningPath camino) {
		caminosInscritos.put(camino, new Progreso(camino));
	}
	
	public List<Actividad> getActividadesPendientes(LearningPath camino){
		return getProgresoLearningPath(camino).getActividadesPendinetes();
	}
	
	public Progreso getProgresoLearningPath(LearningPath camino) {
		return caminosInscritos.get(camino);
	}
	
	public Double getPorcentajeProgresoLearningPath(LearningPath camino) {
		return getProgresoLearningPath(camino).getPorcentaje();
	}
	
	public String interactuarActividad(LearningPath camino, Actividad actividad) {
		return actividad.interactuar();
	}
	
	public void entregarActividad(LearningPath camino, Actividad actividad) {
	    if (!camino.getActivdades().contains(actividad)) {
	        throw new IllegalArgumentException("La actividad no pertenece al Learning Path proporcionado.");
	    }
		if (actividad.getTipo().equals("RE") || actividad.getTipo().equals("Q") || actividad.getTipo().equals("E")) {
			actividad.cambiarResultado("Entregado");
		}
		Progreso progreso = caminosInscritos.get(camino);
		progreso.entregarActividad(actividad);
	}
	
	
	public List<LearningPath> getCaminosInscritos() {
		List<LearningPath> caminos = new ArrayList<LearningPath>();
		for (LearningPath camino: caminosInscritos.keySet()) {
			caminos.add(camino);
		}
	    return caminos;
	}
	
	
    @Override
    public String toString() {
    	String cadena = "";
    	if (caminosInscritos.isEmpty()) {
    		cadena = "NA";
    	} else {
	    	for (LearningPath camino: caminosInscritos.keySet()) {
	    		 cadena += String.valueOf(camino.getID()) + "-" + caminosInscritos.get(camino).toString() + "%";
	    	}
	    	cadena = cadena.substring(0, cadena.length()-1);
    	}
        return super.toString()  + "/" + cadena;
    }

	
}