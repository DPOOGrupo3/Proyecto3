package consola;

import modelo.CentralLogica;
import modelo.LearningPath;
import modelo.Progreso;
import modelo.actividad.Actividad;
import modelo.usuario.Estudiante;
import java.io.IOException;
import java.util.List;

public class ConsolaEstudiante extends ConsolaBasica{
	
	
	private Estudiante estudiante;
	private CentralLogica centralLogica;
	
	public ConsolaEstudiante(Estudiante estudiante, CentralLogica centralLogica) {
        this.estudiante = estudiante;
        this.centralLogica = centralLogica;
    }
	
	/**
     * Método para ejecutar el menú principal del estudiante.
     */
	public void correrConsola() throws IOException {
        String[] opcionesMenu = {"Inscribir en un Learning Path", 
        		"Ver actividades pendientes","Ver progreso en un Learning Path",
                "Interactuar con actividad",
                "Entregar actividad","Ver tus LearningPaths",
                "Salir"};
        int opcion = mostrarMenu("Menú Estudiante", opcionesMenu);

        try {
            switch (opcion) {
                case 1 -> inscribirEnLearningPath();
                case 2 -> verActividadesPendientes();
                case 3 -> verProgresoLearningPath();
                case 4 -> interactuarConActividad();
                case 5 -> entregarActividad();
                case 6 -> verTusLearningPaths();
                case 7 -> {
                    System.out.println("Saliendo...");
                    break;
                }
                default -> System.out.println("Opción no válida.");
            }
        } catch (IOException e) {
            System.out.println("Error al procesar la opción. Intente nuevamente.");
        }

        if (opcion != 7) correrConsola();
    }
	
	
	/**
     * Método para ver los Learning Paths a los cuales el estudiante esta inscrito
     * @throws IOException Si hay errores en la entrada del usuario.
     */
	
	private void verTusLearningPaths() throws IOException {
		
		List<LearningPath> caminos = estudiante.getCaminosInscritos();
		
		if (caminos.isEmpty()) {
			System.out.println("No estas inscrito a ningun camino");
			return;
		}
			System.out.println("Tus Learning Paths disponibles: ");
			for (LearningPath camino : estudiante.getCaminosInscritos()) {
				System.out.println("ID-" + camino.getID() + " "  + camino.getTitulo());
		}	
	}
		

	/**
     * Método para inscribir al estudiante en un Learning Path.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private void inscribirEnLearningPath() throws IOException{
    	List<LearningPath> caminosDisponibles = centralLogica.obtenerLearningPathsDisponibles(estudiante);
    	
    	
    	
    	if (caminosDisponibles.isEmpty()) {
    		System.out.println("No hay Learning Paths disponibles para incripcion");
    		return;
    	}
    	
    	System.out.println("Seleccione un Learning Path para inscribirse");
    	
    	for (int i = 0; i < caminosDisponibles.size(); i++) {
    		LearningPath camino = caminosDisponibles.get(i);
    		System.out.println((i + 1) + "-" + camino.getID() + "-" + camino.getTitulo());
    	}
    	
    	int opcion = pedirEnteroAlUsuario("Ingerese el numero del Learning Path");
    	if (opcion > 0 && opcion <= caminosDisponibles.size()) {
    		LearningPath caminoSeleccionado = caminosDisponibles.get(opcion - 1);
    		estudiante.inscribirCamino(caminoSeleccionado);
    		System.out.println("Te has inscrito exitosamente a un Learning Path: " + caminoSeleccionado.getID() + "-" + caminoSeleccionado.getTitulo());
    	} else {
    		System.out.println("Opcion no valida");
    	}
    	
    }
    
    /**
     * Método para mostrar las actividades pendientes de un Learning Path.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private void verActividadesPendientes() throws IOException {
    	LearningPath camino = seleccionarLearningPathEstudiante();
    	
    	if (camino == null) {
    		return;
    	}
    	
    	List<Actividad> actividadesPendientes = estudiante.getActividadesPendientes(camino);
    	
    	if (actividadesPendientes.isEmpty()) {
    		System.out.println("No tienes actividades pendientes en este Learning Path");
    		
    	} else {
    		System.out.println("Actividades pendientes: ");
    		for (Actividad actividad: actividadesPendientes) {
    			System.out.println("- " + actividad.getID() + " Descripcion: " + actividad.getDescripcion() + "De tipo: " + actividad.getTipo());
    		}
    	}
    }
    
    /**
     * Método para mostrar el progreso y el porcentaje en un Learning Path.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private void verProgresoLearningPath() throws IOException {
    	LearningPath camino = seleccionarLearningPathEstudiante();
    	if (camino == null) {
    		return;
    	}
    	
    	Progreso progreso = estudiante.getProgresoLearningPath(camino);
    	Double porcentaje = estudiante.getPorcentajeProgresoLearningPath(camino);
    	
    	System.out.println("Progreso en el Learning Path: " + camino.getTitulo());
    	System.out.println("Progreso: " + progreso);
    	System.out.println("Porcentaje progreso: " + (porcentaje * 100) + "%");
    }
    
    /**
     * Método para interactuar con una actividad en un Learning Path.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private void interactuarConActividad() throws IOException {
    	LearningPath camino = seleccionarLearningPathEstudiante();
    	if (camino == null) {
    		return;
    	}
    	
    	Actividad actividad = seleccionarActividadDelCamino(camino);
    	
    	if (actividad == null) {
    		return;
    	}
    	
    	String rta = estudiante.interactuarActividad(camino, actividad);
    	System.out.println("Resultado: " + rta);
    	
    }
    
    /**
     * Método para entregar una actividad en un Learning Path.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private void entregarActividad() throws IOException {
    	LearningPath camino = seleccionarLearningPathEstudiante();
    	if (camino == null) {
    		return;
    	}
    	
    	Actividad actividad = seleccionarActividadDelCamino(camino);
    	
    	if (actividad == null) {
    		return;
    	}
    	
    	estudiante.entregarActividad(camino, actividad);
    	System.out.println("Actividad entregada exitosamente");
    }
    
    /**
     * Permite al estudiante seleccionar un Learning Path en el que está inscrito.
     * @return El Learning Path seleccionado o null si no se selecciona ninguno.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private LearningPath seleccionarLearningPathEstudiante() throws IOException {
        List<LearningPath> caminosInscritos = estudiante.getCaminosInscritos();

        if (caminosInscritos.isEmpty()) {
            System.out.println("No estás inscrito en ningún Learning Path");
            return null;
        }

        System.out.println("Seleccione un Learning Path:");
        for (int i = 0; i < caminosInscritos.size(); i++) {
            LearningPath camino = caminosInscritos.get(i);
            System.out.println((i + 1) + ". " + camino.getID() + " - " + camino.getTitulo());
        }

        int opcion = pedirEnteroAlUsuario("Ingrese el numero del Learning Path");
        if (opcion > 0 && opcion <= caminosInscritos.size()) {
            return caminosInscritos.get(opcion - 1);
        } else {
            System.out.println("Opción no valida.");
            return null;
        }
    }
    
    /**
     * Permite al estudiante seleccionar una actividad de un Learning Path inscrito.
     * @param camino El Learning Path del que se seleccionará la actividad.
     * @return La actividad seleccionada o null si no se selecciona ninguna.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private Actividad seleccionarActividadDelCamino(LearningPath camino) throws IOException {
        List<Actividad> actividades = estudiante.getActividadesPendientes(camino);

        if (actividades.isEmpty()) {
            System.out.println("No hay actividades disponibles en este Learning Path.");
            return null;
        }

        System.out.println("Seleccione una actividad:");
        for (int i = 0; i < actividades.size(); i++) {
            Actividad actividad = actividades.get(i);
            System.out.println((i + 1) + ". " + actividad.getDescripcion() + " ID: " + actividad.getID());
        }

        int opcion = pedirEnteroAlUsuario("Ingrese el numero de la actividad");
        if (opcion > 0 && opcion <= actividades.size()) {
            return actividades.get(opcion - 1);
        } else {
            System.out.println("Opción no valida.");
            return null;
        }
    }
}
