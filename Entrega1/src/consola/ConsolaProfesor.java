package consola;

import modelo.CentralLogica;
import modelo.LearningPath;
import modelo.actividad.Actividad;
import modelo.actividad.RecursoEducativo;
import modelo.actividad.Tarea;
import modelo.actividad.examen.Encuesta;
import modelo.actividad.examen.Parcial;
import modelo.actividad.examen.Quiz;
import modelo.usuario.Profesor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsolaProfesor extends ConsolaBasica {
	
	private Profesor profesor;
	private CentralLogica centralLogica;
	
	public ConsolaProfesor (Profesor profesor, CentralLogica centralLogica) {
		this.profesor = profesor;
		this.centralLogica = centralLogica;
	}
	
	/**
     * Método para ejecutar el menú principal del profesor.
     */
    public void correrConsola() {
        String[] opcionesMenu = {"Crear Learning Path", 
        		"Editar Learning Path", "Eliminar Learning Path", 
        		"Crear actividad", "Gestionar actividad",
        		"Eliminar Actividad", "Consultar Learning Paths creados", "Salir"};
        int opcion = mostrarMenu("Menú Profesor", opcionesMenu);
        
        try {
            switch (opcion) {
                case 1 -> crearLearningPath();
                case 2 -> editarLearningPath();
                case 3 -> eliminarLearningPath();
                case 4 -> crearActividad();
                case 5 -> editarActividad();
                case 6 -> eliminarActividad();
                case 7 -> consultarLearningPaths();
                case 8 -> {
                    System.out.println("Saliendo...");
                    break;
                }
                default -> System.out.println("Opción no válida.");
            }
        } catch (IOException e) {
            System.out.println("Error al procesar la opción. Intente nuevamente.");
        }

        if (opcion != 8) correrConsola();
    }
   
    
    /**
     * Método para crear un nuevo Learning Path.
     */
    private void crearLearningPath() throws IOException {
    	String titulo = pedirCadenaAlUSuario("Ingrese el titulo del Learning Path");
    	String descripcion = pedirCadenaAlUSuario("Ingrese la descripcion del Learning Path");
    	String objetivo = pedirCadenaAlUSuario("Ingrese el objetivo del Learning Path");
    	List<Actividad> actividades = seleccionarActividades();

    	
    	//LearningPath nuevoPath = centralLogica.crearLearningPath(titulo, descripcion, objetivo, actividades);
    	//System.out.println("Learning Path creado con exito: " + nuevoPath.getID());
    	
    	correrConsola();
    	
    }
    

    /**
     * Permite al profesor editar un Learning Path existente.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private void editarLearningPath() throws IOException {
        LearningPath camino = seleccionarLearningPath();

        if (camino != null) {
            String[] opciones = {
                "Cambiar título",
                "Cambiar descripción",
                "Cambiar objetivo",
                "Agregar actividad",
                "Eliminar actividad"
            };

            int opcion = this.mostrarMenu("Editar Learning Path", opciones);

            switch (opcion) {
                case 1 -> {
                    String nuevoTitulo = pedirCadenaAlUSuario("Ingrese el nuevo titulo");
                    centralLogica.cambiarTituloLearningPath(camino.getID(), nuevoTitulo);
                    System.out.println("Título actualizado.");
                }
                case 2 -> {
                    String nuevaDescripcion = pedirCadenaAlUSuario("Ingrese la nueva descripcion");
                    centralLogica.cambiarDescrpcionLearningPath(camino.getID(), nuevaDescripcion);
                    System.out.println("Descripcion actualizada.");
                }
                case 3 -> {
                    String nuevoObjetivo = pedirCadenaAlUSuario("Ingrese el nuevo objetivo");
                    centralLogica.cambiarObjetivoLearningPath(camino.getID(), nuevoObjetivo);
                    System.out.println("Objetivo actualizado.");
                }
                case 4 -> {
                    Actividad actividad = (Actividad) seleccionarActividades();
                    if (actividad != null) {
                        centralLogica.agregarActividadLearningPath(camino.getID(), actividad.getID());
                        System.out.println("Actividad agregada.");
                    }
                }
                case 5 -> {
                    Actividad actividad = seleccionarActividadDelCamino(camino);
                    if (actividad != null) {
                        centralLogica.eliminarsActividadLearningPath(camino.getID(), actividad.getID());
                        System.out.println("Actividad eliminada.");
                    }
                }
                default -> System.out.println("Opción no valida.");
            }
        } else {
            System.out.println("No se seleccionó un Learning Path valido.");
        }
    }
    
    /**
     * Permite al profesor eliminar un Learning Path existente.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private void eliminarLearningPath() throws IOException {
        LearningPath camino = seleccionarLearningPath();

        if (camino != null) {
            centralLogica.eliminarLearningPathCreado(camino.getID());
            System.out.println("Learning Path eliminado exitosamente.");
        } else {
            System.out.println("No se seleccionó un Learning Path valido.");
        }
    }
    
    /**
     * Consulta y muestra los Learning Paths creados por el profesor.
     */
    private void consultarLearningPaths() {
        List<LearningPath> caminos = centralLogica.obtenerLearningPathsProfesor(profesor);

        if (caminos.isEmpty()) {
            System.out.println("No tiene Learning Paths creados.");
        } else {
            System.out.println("Learning Paths creados:");
            for (LearningPath camino : caminos) {
                System.out.println(camino.getID() + " - " + camino.getTitulo());
            }
        }
    }
    
    
    	
    


	/**
     * Método para crear una nueva actividad.
     */
    private void crearActividad() throws IOException {
    	String descripcion = pedirCadenaAlUSuario("Ingrese la descirpcion de la actividad");
    	String objetivo = pedirCadenaAlUSuario("Ingrese el objetivo de la actividad");
    	int nivelDificultad = pedirEnteroAlUsuario("Ingrese el nivel de dificultad de la actividad");
    	Double duracion = pedirNumeroAlUsuario("Ingrese la duracion de la actividad");
    	List <Integer> pre = new ArrayList<Integer>();
    	boolean hayMas = true;
    	while (hayMas) {
    		int p = pedirEnteroAlUsuario("Ingrese el id del prerequisito");
    		pre.add(p);
    		
    		int opcion = (int) pedirNumeroAlUsuario("Ingrese 1 si hay mas prerequisitos, ingrese 2 si no hay mas prerequisitos");
    		
    		if (opcion == 2) {
    			hayMas = false;
    		} else {
    			hayMas = true;
    		}
    		
    	}
    	String[] opciones = {
                 "Recurso educativo: RE",
                 "Tarea: T",
                 "Quiz: Q",
                 "Parcial: P",
                 "Encuesta: E"
             };
    	
    	 int opcion = this.mostrarMenu("Eliga que tipo de actividad es", opciones);
    	 Actividad nuevaActividad = null;
    	 
    	 switch (opcion) {
         case 1 -> {
        	 String tipoRecurso = pedirCadenaAlUSuario("Ingerese el tipo de recurso educativo");
        	 String recurso = pedirCadenaAlUSuario("Ingrese el recurso ecucativo");
        	 nuevaActividad = centralLogica.crearActividad(descripcion, objetivo, "RE", nivelDificultad, duracion, pre, recurso, tipoRecurso, null, null, null, 0);
        	 
         }
         case 2 -> {
        	 List<String> ejercicios = new ArrayList<String>();
        	 int cant = pedirEnteroAlUsuario("Ingrese la cantidad de ejercicios");
        	 for (int i = 0; i < cant; i++) {
        		 ejercicios.add(pedirCadenaAlUSuario("Ingrese el ejercicio numero" + String.valueOf(i)));
        	 }
        	 nuevaActividad = centralLogica.crearActividad(descripcion, objetivo, "T", nivelDificultad, duracion, pre, null, null, ejercicios, null, null, 0);
         }
         case 3 -> {
        	 List<String> preguntas = new ArrayList<String>();
        	 List<String> respuestas = new ArrayList<String>();
        	 int cant = pedirEnteroAlUsuario("Ingrese la cantidad de preguntas");
        	 for (int i = 0; i < cant; i++) {
        		 preguntas.add(pedirCadenaAlUSuario("Ingrese la pregunta numero" + String.valueOf(i)));
        		 respuestas.add(pedirCadenaAlUSuario("Ingrese la respuestas numero" + String.valueOf(i)));
        	 }
        	 int calMin = pedirEnteroAlUsuario("Ingrese la calificacion minima");
        	 nuevaActividad = centralLogica.crearActividad(descripcion, objetivo, "Q", nivelDificultad, duracion, pre, null, null, null, preguntas, respuestas, calMin);
         }
         case 4 -> {
        	 List<String> preguntas = new ArrayList<String>();
        	 int cant = pedirEnteroAlUsuario("Ingrese la cantidad de preguntas");
        	 for (int i = 0; i < cant; i++) {
        		 preguntas.add(pedirCadenaAlUSuario("Ingrese la pregunta numero" + String.valueOf(i)));
        	 }
        	 nuevaActividad = centralLogica.crearActividad(descripcion, objetivo, "P", nivelDificultad, duracion, pre, null, null, null, preguntas, null, 0);
         }
         case 5 -> {
        	 List<String> preguntas = new ArrayList<String>();
        	 int cant = pedirEnteroAlUsuario("Ingrese la cantidad de preguntas");
        	 for (int i = 0; i < cant; i++) {
        		 preguntas.add(pedirCadenaAlUSuario("Ingrese la pregunta numero" + String.valueOf(i)));
        	 }
        	 nuevaActividad = centralLogica.crearActividad(descripcion, objetivo, "E", nivelDificultad, duracion, pre, null, null, null, preguntas, null, 0);
         }
         default -> System.out.println("Opción no valida.");
         
    }
    	 
    	System.out.println("Actividad creada con exito con ID: " + nuevaActividad.getID());
    	
    	correrConsola();
    }
    
    /**
     * Permite al profesor editar una actividad existente.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private void editarActividad() throws IOException {
        Actividad actividad = seleccionarActividadIndependiente();

        if (actividad != null) {
            String[] opciones = {
                "Cambiar descripción",
                "Cambiar objetivo",
                "Cambiar duración esperada",
                "Cambiar nivel de dificultad"
            };

            int opcion = this.mostrarMenu("Editar Actividad", opciones);

            switch (opcion) {
                case 1 -> {
                    String nuevaDescripcion = pedirCadenaAlUSuario("Ingrese la nueva descripción");
                    centralLogica.cambiarDescrpcionActividad(actividad.getID(), nuevaDescripcion);
                    System.out.println("Descripción actualizada.");
                }
                case 2 -> {
                    String nuevoObjetivo = pedirCadenaAlUSuario("Ingrese el nuevo objetivo");
                    centralLogica.cambiarObjetivoActividad(actividad.getID(), nuevoObjetivo);
                    System.out.println("Objetivo actualizado.");
                }
                case 3 -> {
                    Double nuevaDuracion = pedirNumeroAlUsuario("Ingrese la nueva duración esperada (en minutos)");
                    centralLogica.cambiarDuracionEsperadaActividad(actividad.getID(), nuevaDuracion);
                    System.out.println("Duración actualizada.");
                }
                case 4 -> {
                    int nuevaDificultad = pedirEnteroAlUsuario("Ingrese el nuevo nivel de dificultad (1-5)");
                    centralLogica.cambiarNivelDificultadActividad(actividad.getID(), nuevaDificultad);
                    System.out.println("Nivel de dificultad actualizado.");
                }
                default -> System.out.println("Opción no válida.");
            }
        } else {
            System.out.println("No se seleccionó una actividad válida.");
        }
    }
    
    /**
     * Permite al profesor eliminar una actividad independiente.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private void eliminarActividad() throws IOException {
        Actividad actividad = seleccionarActividadIndependiente();

        if (actividad != null) {
            centralLogica.eliminarActividad(actividad.getID());
            System.out.println("Actividad eliminada exitosamente.");
        } else {
            System.out.println("No se seleccionó una actividad válida.");
        }
    }
       
    /**
     * Permite al profesor seleccionar un Learning Path creado.
     * @return El Learning Path seleccionado o null si no se selecciona ninguno.
     * @throws IOException Si hay errores en la entrada del usuario.
     */
    private LearningPath seleccionarLearningPath() throws IOException {
        List<LearningPath> caminos = centralLogica.obtenerLearningPathsProfesor(profesor);

        if (caminos.isEmpty()) {
            System.out.println("No tiene Learning Paths creados.");
            return null;
        }

        System.out.println("Seleccione un Learning Path:");
        for (int i = 0; i < caminos.size(); i++) {
            LearningPath camino = caminos.get(i);
            System.out.println((i + 1) + ". " + camino.getID() + " - " + camino.getTitulo());
        }

        int opcion = pedirEnteroAlUsuario("Ingrese el número del Learning Path");
        if (opcion > 0 && opcion <= caminos.size()) {
            return caminos.get(opcion - 1);
        } else {
            System.out.println("Opción no válida.");
            return null;
        }
    }
    
    /**
     * Permite al profesor seleccionar una lista de actividades independientes para un Learning Path.
     * @return Una lista de actividades seleccionadas.
     */
    private List<Actividad> seleccionarActividades() {
        List<Actividad> actividadesDisponibles = centralLogica.obtenerActividadesIndependientes();
        List<Actividad> actividadesSeleccionadas = new ArrayList<>();

        if (actividadesDisponibles.isEmpty()) {
            System.out.println("No hay actividades disponibles.");
            return actividadesSeleccionadas;
        }
        
        System.out.println("Seleccione actividades (escriba los numeros de las actividades separados por comas, ej: 1,2,3)");
        for (int i = 0; i < actividadesDisponibles.size(); i++) {
            Actividad actividad = actividadesDisponibles.get(i);
            System.out.println((i + 1) + ". " + actividad.getDescripcion() + " (" + actividad.getID() + ")");
        }
        
        
        String input = pedirCadenaAlUSuario("Ingrese sus opciones o presione enter para omitir");
        if (!input.isEmpty()) {
            String[] opciones = input.split(",");
            for (String opcion : opciones) {
            	try {
                    int index = Integer.parseInt(opcion.trim()) - 1;
                    if (index >= 0 && index < actividadesDisponibles.size()) {
                        actividadesSeleccionadas.add(actividadesDisponibles.get(index));
                    } else {
                        System.out.println("Opción " + opcion + " no valida. Ignorada.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opción " + opcion + " no valida. Ignorada.");
                }
            }
        }
            
            
		return actividadesSeleccionadas;
    
    }
    
    /**
     * Permite al profesor seleccionar una actividad independiente para editar o eliminar.
     * @return La actividad seleccionada o null si no se selecciona ninguna.
     */
    private Actividad seleccionarActividadIndependiente() {
    	List<Actividad> actividades = centralLogica.obtenerActividadesIndependientes();
    	
    	if (actividades.isEmpty()) {
    		System.out.println("No hay actividades independientes disponibles");
    		return null;
    	
    	
    	}
    	
    	System.out.println("Seleccione una actividad: ");
    	
    	 for (int i = 0; i < actividades.size(); i++) {
    	        Actividad actividad = actividades.get(i);
    	        System.out.println((i + 1) + ". " + actividad.getDescripcion() + " (" + actividad.getID() + ")");
    	    }
    	 
    	 int opcion = pedirEnteroAlUsuario("Ingrese el numero de la actividad: ");
    	 
    	 if (opcion > 0 && opcion <= actividades.size()) {
    	        return actividades.get(opcion - 1);
    	    } else {
    	        System.out.println("Opción no válida.");
    	        return null;
    	    }
    
    }
    
    /**
     * Permite al profesor seleccionar una actividad de un Learning Path.
     * @param camino El Learning Path del que se seleccionará la actividad.
     * @return La actividad seleccionada o null si no se selecciona ninguna.
     */
    private Actividad seleccionarActividadDelCamino(LearningPath camino) {
    	List<Actividad> actividades = camino.getActivdades();
    	
    	if (actividades.isEmpty()) {
    		System.out.println("El Learning Path no tiene actividades");
    		return null;
    	}
    	
    	System.out.println("Seleccione una actividad del Learning Paths");
    	for (int i = 0; i < actividades.size(); i++) {
            Actividad actividad = actividades.get(i);
            System.out.println((i + 1) + ". " + actividad.getDescripcion() + " (" + actividad.getID() + ")");
        }
    	
    	int opcion = pedirEnteroAlUsuario("Ingrese el número de la actividad");
        if (opcion > 0 && opcion <= actividades.size()) {
            return actividades.get(opcion - 1);
        } else {
            System.out.println("Opción no válida.");
            return null;
	
        }
    }
}
    
