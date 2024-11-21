package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.actividad.Actividad;

public class Progreso {
	private Double porcentaje;
	private LearningPath camino;
	private List<Actividad> actividadesTerminadas;
	private List<Actividad> actividadesPendinetes;
	
	public Progreso(LearningPath camino) {
		porcentaje = 0.0;
		this.camino = camino;
		actividadesTerminadas = new ArrayList<Actividad>();
		actividadesPendinetes = new ArrayList<Actividad>();
		cargarActividadesPendientes();
	}
	
	public Double getPorcentaje() {
		return porcentaje;
	}
	
	private void calcularPorcentaje() {
	    if (camino.getActivdades().size() > 0) {
	        porcentaje = (double) actividadesTerminadas.size() / camino.getActivdades().size();
	    } else {
	        porcentaje = 0.0;
	    }
	}


	public List<Actividad> getActividadesTerminadas() {
		return actividadesTerminadas;
	}

	public List<Actividad> getActividadesPendinetes() {
		return actividadesPendinetes;
	}

	public void entregarActividad(Actividad actividad) {
		
		   if (!actividadesPendinetes.contains(actividad)) {
		        throw new IllegalArgumentException("La actividad no está en la lista de pendientes.");
		    }
		actividadesTerminadas.add(actividad);
		eliminarActividadPendiente(actividad);
		calcularPorcentaje();
	}
	
	private void cargarActividadesPendientes() {
		for (Actividad actividad: camino.getActivdades()) {
			actividadesPendinetes.add(actividad);
		}
	}
	
	private void eliminarActividadPendiente(Actividad actividad) {
		actividadesPendinetes.remove(actividad);
	}
	
	@Override
	public String toString() {
		String cadena = "";
		if (actividadesTerminadas.isEmpty()) {
			cadena += "NA" + "-";
		} else {
			for (Actividad actividad: actividadesTerminadas) {
				cadena += String.valueOf(actividad.getID()) + "::";
			}
			cadena = cadena.substring(0, cadena.length()-2) + "-";
		}
		if (actividadesPendinetes.isEmpty()) {
			cadena += "NA";
		} else {
			for (Actividad actividad: actividadesPendinetes) {
				cadena += String.valueOf(actividad.getID()) + "::";
			}
			cadena = cadena.substring(0, cadena.length()-2);
		}
		return String.valueOf(porcentaje) + "-" + cadena;
	}

	public LearningPath getCamino() {
		return camino;
	}
}