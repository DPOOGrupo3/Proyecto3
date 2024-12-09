package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import modelo.CentralLogica;
import modelo.LearningPath;
import modelo.actividad.Actividad;
import modelo.usuario.Estudiante;
import modelo.Progreso;


public class InterfazEstudiante extends JPanel{
	
	private Estudiante estudiante;
    private CentralLogica centralLogica;
    
    private JPanel panelBotones;
    private JTextArea areaInformacion;
    private Progreso progreso;
   
    
    
    
    /**
     * Constructor de la InterfazEstudiante.
     * @param estudiante El estudiante que está usando la interfaz.
     * @param centralLogica La lógica central del sistema.
     */
    public InterfazEstudiante(Estudiante estudiante, CentralLogica centralLogica) {
        this.estudiante = estudiante;
        this.centralLogica = centralLogica;
        
        setLayout(new BorderLayout());

        
        inicializar();
    }
    
    /**
     * Método para inicializar el panel estudiante
     */
    private void inicializar() {
        
        areaInformacion = new JTextArea();
        areaInformacion.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaInformacion);

        
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(7, 1, 10, 10));

        
        agregarBoton("Inscribir en un Learning Path", e -> inscribirEnLearningPath());
        agregarBoton("Ver Actividades Pendientes", e -> verActividadesPendientes());
        agregarBoton("Ver Progreso en un Learning Path", e -> verProgresoLearningPath());
        agregarBoton("Interactuar con Actividad", e -> interactuarConActividad());
        agregarBoton("Entregar Actividad", e -> entregarActividad());
        agregarBoton("Visualizar Cantidad de Actividades", e -> visualizarActividades());
        agregarBoton("Salir", e -> salir());
        
        


        add(panelBotones, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);
    }


    
    /**
     * Método para inscribir al estudiante en un Learning Path.
     */
    private void inscribirEnLearningPath()   {
    	List<LearningPath> caminosDisponibles = centralLogica.obtenerLearningPathsDisponibles(estudiante);
    	if (caminosDisponibles.isEmpty()) {
    		mostrarInformacion("No hay Learning Paths disponobles para la inscripcion");
    		
    	}
    	 String[] opciones = caminosDisponibles.stream()
                .map(c -> c.getID() + " - " + c.getTitulo())
                .toArray(String[]::new);

        String seleccion = (String) JOptionPane.showInputDialog(
                this, "Seleccione un Learning Path:",
                "Inscribir Learning Path", JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);
        
        if (seleccion != null) {
        	int idSeleccionado = Integer.parseInt(seleccion.split("-")[0]);
        	LearningPath caminoSeleccionado = caminosDisponibles.stream()
                    .filter(c -> c.getID() == idSeleccionado)
                    .findFirst().orElse(null);
        	if (caminoSeleccionado != null) {
        		estudiante.inscribirCamino(caminoSeleccionado);
        		mostrarInformacion("Inscripcion exitosa en: " + caminoSeleccionado.getID() + "- " + caminoSeleccionado.getTitulo());
        	} else {
        		mostrarInformacion("Ya estas inscrito en este Learning Path");
        	}
        
        }
    	
    }
    
    
    /**
     * Método para ver las actividades pendientes.
     */
    
    private void verActividadesPendientes() {
    	LearningPath camino = seleccionarLearningPath();
    	if (camino== null) {
    		return;
    	}
    	List<Actividad> Apendientes = estudiante.getActividadesPendientes(camino);
    	StringBuilder sb = new StringBuilder("Actividades pendientes:\n");
    	if (Apendientes.isEmpty()) {
    		sb.append("No tienes actividades pendientes");
    	} else {
    		for (Actividad a: Apendientes) {
    			sb.append(a.getID()).append(("-")).append(a.getDescripcion()).append("\n");
    		}
    	}
    	mostrarInformacion(sb.toString());
    }
    
    
    /**
     * Método para ver el progreso de un Learning Path.
     */
    private void verProgresoLearningPath() {
    	LearningPath camino = seleccionarLearningPath();
    	
    	if (camino != null) {
    		double porcentaje = estudiante.getPorcentajeProgresoLearningPath(camino) * 100;
    		
    		mostrarInformacion("Progreso en " + camino.getTitulo() + ("= ") + String.format("%.2f", porcentaje) + "%");
    	} else {
    		return;
    	}
    }
    
    
    /**
     * Método para interactuar con una actividad.
     */
    private void interactuarConActividad() {
    	LearningPath camino = seleccionarLearningPath();
    	if (camino == null) {
    		return;
    	}
    	
    	Actividad actividad = seleccionarActividad(camino);
    	if (actividad != null) {
    		String rta = estudiante.interactuarActividad(camino, actividad);
    		mostrarInformacion("interaccion con actividad: " + rta);
    	}
    }
    
    /**
     * Método para entregar una actividad.
     */
    
    private void entregarActividad() {
    	LearningPath camino = seleccionarLearningPath();
    	if (camino == null) {
    		return;
    	}
    	
    	Actividad actividad = seleccionarActividad(camino);
    	if (actividad != null) {
    		estudiante.entregarActividad(camino, actividad);
    		mostrarInformacion("Actividad entregada exitosamente");
    	}
    		
    }
    
    /**
     * Método para ver una grafica de las actividades realizadas y no realizadas.
     */
    
    private void visualizarActividades() {
    	
    	LearningPath camino = seleccionarLearningPath();
    	
    	
    	
        List<Actividad> actividadesPendientes = estudiante.getActividadesPendientes(camino);
        List<Actividad> actividadesRealizadas = estudiante.getActividadesTerminadas(camino);
        
        
        JPanel panelActividades = new JPanel();
        panelActividades.setLayout(new GridLayout(0, 5, 10, 10)); 

        
        for (Actividad actividad : actividadesRealizadas) {
            JPanel panelActividad = new JPanel();
            panelActividad.setBackground(Color.GREEN);
            panelActividad.add(new JLabel("ID: " + actividad.getID()));
            panelActividades.add(panelActividad);
        }

        
        for (Actividad actividad : actividadesPendientes) {
            JPanel panelActividad = new JPanel();
            panelActividad.setBackground(Color.RED);
            panelActividad.add(new JLabel("ID: " + actividad.getID()));
            panelActividades.add(panelActividad);
        }

        
        JFrame actividadesFrame = new JFrame("Actividades Realizadas vs Pendientes");
        actividadesFrame.setSize(600, 400);  
        actividadesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        actividadesFrame.add(new JScrollPane(panelActividades));
        actividadesFrame.setVisible(true);
    }

      
    
    /**
     * Método para salir.
     */
    private void salir() {
        JOptionPane.showMessageDialog(this, "Saliendo de la sesion");
        System.exit(0);
    }
    
    /**
     * Método para agregar botones al panel.
     * @param texto El texto del botón.
     * @param action La acción que se ejecutará al presionar el botón.
     */
    private void agregarBoton(String texto, ActionListener action) {
        JButton boton = new JButton(texto);
        boton.addActionListener(action);
        panelBotones.add(boton);
    }
    
    /**
     * Método para mostrar información en el área de texto.
     * @param mensaje El mensaje a mostrar.
     */
    private void mostrarInformacion(String mensaje) {
        areaInformacion.setText(mensaje);
    }
    
    /**
     * Método para seleccionar un Learning Path.
     * @return El Learning Path seleccionado.
     */
    private LearningPath seleccionarLearningPath() {
        List<LearningPath> caminos = estudiante.getCaminosInscritos();
        if (caminos.isEmpty()) {
            mostrarInformacion("No estás inscrito en ningún Learning Path.");
            return null;
        }

        String[] opciones = caminos.stream()
                .map(c -> c.getID() + " - " + c.getTitulo())
                .toArray(String[]::new);

        String seleccion = (String) JOptionPane.showInputDialog(
                this, "Seleccione un Learning Path:",
                "Seleccionar Learning Path", JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);

        if (seleccion != null) {
            int indice = Integer.parseInt(seleccion.split(" - ")[0]);
            if (indice >= 0 && indice < caminos.size()) {
                return caminos.get(indice);
            }
        }
        mostrarInformacion("Selección invalida");
        return null;
    }
    
    /**
     * Método para seleccionar una actividad.
     * @return La actividad seleccionada.
     */
    
    
    private Actividad seleccionarActividad(LearningPath camino) {
        List<Actividad> actividades = estudiante.getActividadesPendientes(camino);
        if (actividades.isEmpty()) {
            mostrarInformacion("No hay actividades pendientes");
            return null;
        }

        String[] opciones = actividades.stream()
                .map(a -> a.getID() + " - " + a.getDescripcion())
                .toArray(String[]::new);

        String seleccion = (String) JOptionPane.showInputDialog(
                this, "Seleccione una Actividad:",
                "Seleccionar Actividad", JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);


        if (seleccion != null) {
            int indice = Integer.parseInt(seleccion.split(" - ")[0]);
            if (indice >= 0 && indice < actividades.size()) {
                return actividades.get(indice);
            }
        }
        mostrarInformacion("Seleccion invalida");
        return null;
    }
    
    
    
    


}
