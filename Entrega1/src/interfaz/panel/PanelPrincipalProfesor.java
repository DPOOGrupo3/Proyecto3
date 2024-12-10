package interfaz.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import interfaz.InterfazCentral;
import modelo.CentralLogica;
import modelo.LearningPath;
import modelo.actividad.Actividad;
import modelo.usuario.Profesor;

public class PanelPrincipalProfesor extends JPanel implements ActionListener, ListSelectionListener {
	private Profesor profesor;
	private InterfazCentral interfaz;
	private CentralLogica central;
	private JLabel lblVacio = new JLabel(" ");
	private JButton btnCrearLearningPath; 
	private JButton btnEditarLearningPath;
	private JButton btnEliminarLearningPath;
	private JButton btnCrearActividad;
	private JButton btnGestionarActividad;
	private JButton btnEliminarActividad;
	private JButton btnConsultarLearningPathsCreados;
	private JButton btnCerrarSesion;
	private JPanel pBotones = new JPanel();
	private JPanel pCentral = new JPanel();
	private String tipoAct = "";
	private List<LearningPath> caminos = new ArrayList<LearningPath>();
	private List<Actividad> actividades = new ArrayList<Actividad>();
	private PanelLearningPath pLP;
	private PanelEditarLearningPath pEditarLP;
	private PanelEditarLP pCentralEditarLP;
	private PanelEditarLPAct pCentralEditarLPAct;
	private PanelEliminarLP pEliminarLP;
	private LearningPath lP;
	private PanelRecursoEducativo pRecurso = new PanelRecursoEducativo();
	private PanelTarea pTarea;
	private PanelQuiz pQuiz;
	private PanelParcial pParcial;
	private PanelEncuesta pEncuesta;
	private PanelEditarActividad pEditarAct;
	//private PanelEd pCentralEditarAct;
	private PanelEliminarActividad pEliminarActividad;
	private Actividad Act;
	int ID = -1;
	DefaultListModel<String> modeloDatos;
	JList<String> listaLP;
	private String cambio;
	private static String cancelar = "CANCELAR";
	private static String confirmarCLP = "CONFRIMARCLP";
	private static String confirmarELP = "CONFRIMARELP";
	private static String confirmarRLP = "CONFRIMARRLP";
	private static String confirmarCA = "CONFRIMARCA";
	private static String confirmarGA = "CONFRIMARGA";
	private static String confirmarEA = "CONFRIMAREA";

	public PanelPrincipalProfesor(Profesor profesor, InterfazCentral interfaz, CentralLogica central) {
		setLayout(new BorderLayout());
		
		this.profesor = profesor;
		this.interfaz = interfaz;
		this.central = central;
		caminos.addAll(profesor.getCaminosCreados());
		profesor.getCaminosCreados().forEach(camino -> actividades.addAll(camino.getActivdades()));
		
		pBotones.setLayout(new GridLayout(8, 1, 10, 10));
		btnCrearLearningPath =  new JButton("Crear Learning Path");
		btnCrearLearningPath.addActionListener(this);
		pBotones.add(btnCrearLearningPath);
		btnEditarLearningPath =  new JButton("Editar Learning Path");
		btnEditarLearningPath.addActionListener(this);
		pBotones.add(btnEditarLearningPath);
		btnEliminarLearningPath =  new JButton("Eliminar Learning Path");
		btnEliminarLearningPath.addActionListener(this);
		pBotones.add(btnEliminarLearningPath);
		btnCrearActividad =  new JButton("Crear Actividad");
		btnCrearActividad.addActionListener(this);
		pBotones.add(btnCrearActividad);
		btnGestionarActividad =  new JButton("Editar Actividad");
		btnGestionarActividad.addActionListener(this);
		pBotones.add(btnGestionarActividad);
		btnEliminarActividad =  new JButton("Eliminar Actividad");
		btnEliminarActividad.addActionListener(this);
		pBotones.add(btnEliminarActividad);
		btnConsultarLearningPathsCreados =  new JButton("Consultar Learning Paths Creados");
		btnConsultarLearningPathsCreados.addActionListener(this);
		pBotones.add(btnConsultarLearningPathsCreados);
		btnCerrarSesion =  new JButton("Cerrar Sesión");
		btnCerrarSesion.addActionListener(this);
		pBotones.add(btnCerrarSesion);
		
		add(pCentral, BorderLayout.CENTER);
		add(pBotones, BorderLayout.WEST);
	}
	
	private void crearLearningPath() {
		pCentral.setLayout(new BorderLayout());
		
		JPanel pSur = new JPanel();
		pSur.setLayout(new FlowLayout());
		List<Actividad> actividadesDispo = (ArrayList<Actividad>) central.obtenerActividadesIndependientes();
		pLP = new PanelLearningPath(actividadesDispo);
		JPanel pCentro = pLP;
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand(cancelar);
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand(confirmarCLP);
		
		pSur.add(btnCancelar);
		pSur.add(btnConfirmar);
		
		pCentral.add(pCentro, BorderLayout.CENTER);
		pCentral.add(pSur, BorderLayout.SOUTH);
	}
	
	private void editarLearningPath() {
		if (ID < 0) {
			String[] caminos = profesor.getCaminosCreados().stream().map(c -> c.getID() + " - " + c.getTitulo()).toArray(String[]::new);
			String camino = (String) JOptionPane.showInputDialog(this, "Seleccione un tipo de actividad:", "Tipo Actividad", JOptionPane.QUESTION_MESSAGE, null, caminos, caminos[0]);
			ID = Integer.parseInt(camino.split(" - ")[0]);
			lP = encontrarLearningPathPorID(ID);
		}
		
		pCentral.setLayout(new BorderLayout());
		
		JPanel pSur = new JPanel();
		pSur.setLayout(new FlowLayout());
		pEditarLP = new PanelEditarLearningPath(this);
		JPanel pCentro = pEditarLP;
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand(cancelar);
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand(confirmarELP);
		
		pSur.add(btnCancelar);
		pSur.add(btnConfirmar);
		
		pCentral.add(pCentro, BorderLayout.CENTER);
		pCentral.add(pSur, BorderLayout.SOUTH);
	}
	
	private void eliminarLearningPath() {
		pCentral.setLayout(new BorderLayout());
		
		JPanel pSur = new JPanel();
		pSur.setLayout(new FlowLayout());
		List<String> learningPaths = new ArrayList<String>();
		profesor.getCaminosCreados().forEach(lp -> learningPaths.add(lp.getID() + ". " + lp.getTitulo()));
		pEliminarLP = new PanelEliminarLP(learningPaths);
		JPanel pCentro = pEliminarLP;
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand(cancelar);
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand(confirmarRLP);
		
		pSur.add(btnCancelar);
		pSur.add(btnConfirmar);
		
		pCentral.add(pCentro, BorderLayout.CENTER);
		pCentral.add(pSur, BorderLayout.SOUTH);
	}
	
	private void crearActividad() {
		String[] tipos = {"Recurso Educativo", "Tarea", "Quiz", "Parcial", "Encuesta"};
		tipoAct = (String) JOptionPane.showInputDialog(this, "Seleccione un tipo de actividad:", "Tipo Actividad", JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
		pCentral.setLayout(new BorderLayout());
		
		JPanel pSur = new JPanel();
		pSur.setLayout(new FlowLayout());
		JPanel pCentro = new JPanel ();
		if (tipoAct == "Recurso Educativo") {
			pCentro = pRecurso;
		} else if (tipoAct == "Tarea") {
			int cant = Integer.parseInt(JOptionPane.showInputDialog(this, "¿Cuantos ejercicios desea crear?", "Cantidad de Ejercicios", JOptionPane.QUESTION_MESSAGE));
			pTarea = new PanelTarea(cant);
			pCentro = pTarea;
		} else if (tipoAct == "Quiz") {
			int cant = Integer.parseInt(JOptionPane.showInputDialog(this, "¿Cuantas preguntas desea crear?", "Cantidad de Preguntas", JOptionPane.QUESTION_MESSAGE));
			pQuiz = new PanelQuiz(cant);
			pCentro = pQuiz;
		} else if (tipoAct == "Parcial") {
			int cant = Integer.parseInt(JOptionPane.showInputDialog(this, "¿Cuantas preguntas desea crear?", "Cantidad de Preguntas", JOptionPane.QUESTION_MESSAGE));
			pParcial = new PanelParcial(cant);
			pCentro = pParcial;
		} else if (tipoAct == "Encuesta") {
			int cant = Integer.parseInt(JOptionPane.showInputDialog(this, "¿Cuantas preguntas desea crear?", "Cantidad de Preguntas", JOptionPane.QUESTION_MESSAGE));
			pEncuesta = new PanelEncuesta(cant);
			pCentro = pEncuesta;
		}
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand(cancelar);
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand(confirmarCA);
		
		pSur.add(btnCancelar);
		pSur.add(btnConfirmar);
		
		pCentral.add(pCentro, BorderLayout.CENTER);
		pCentral.add(pSur, BorderLayout.SOUTH);
	}
	
	private void gestionarActividad() {
		if (ID < 0) {
			List<String> actividadesAL = new ArrayList<String>();
			profesor.getCaminosCreados().forEach(camino -> camino.getActivdades().forEach(acti -> actividadesAL.add(String.valueOf(acti.getID()))));
			String[] actividades = actividadesAL.toArray(String[]::new);
			String actividad = (String) JOptionPane.showInputDialog(this, "Seleccione una actividad:", "Actividad a Editar", JOptionPane.QUESTION_MESSAGE, null, actividades, actividades[0]);
			ID = Integer.parseInt(actividad.split(" - ")[0]);
			Act = encontrarActividadPorID(ID);
		}
		
		pCentral.setLayout(new BorderLayout());
		
		JPanel pSur = new JPanel();
		pSur.setLayout(new FlowLayout());
		pEditarAct = new PanelEditarActividad(Act.getTipo(), this);
		JPanel pCentro = pEditarAct;
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand(cancelar);
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand(confirmarGA);
		
		pSur.add(btnCancelar);
		pSur.add(btnConfirmar);
		
		pCentral.add(pCentro, BorderLayout.CENTER);
		pCentral.add(pSur, BorderLayout.SOUTH);
	}
	
	private void eliminarActividad() {
		pCentral.setLayout(new BorderLayout());
		
		JPanel pSur = new JPanel();
		pSur.setLayout(new FlowLayout());
		List<String> listaActividades = new ArrayList<String>();
		profesor.getCaminosCreados().forEach(lp -> lp.getActivdades().forEach(act -> listaActividades.add(act.getID() + ". " + act.getDescripcion())));
		pEliminarActividad = new PanelEliminarActividad(listaActividades);
		JPanel pCentro = pEliminarActividad;
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand(cancelar);
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand(confirmarEA);
		
		pSur.add(btnCancelar);
		pSur.add(btnConfirmar);
		
		pCentral.add(pCentro, BorderLayout.CENTER);
		pCentral.add(pSur, BorderLayout.SOUTH);
	}
	
	private void consultarLearningPaths() {
		pCentral.setLayout(new BorderLayout());
		
		JPanel pSur = new JPanel();
		pSur.setLayout(new FlowLayout());
		JPanel pCentro = new JPanel();
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand(cancelar);
		
		pSur.add(btnCancelar);
		
		List<String> learningPaths = new ArrayList<String>();
		profesor.getCaminosCreados().forEach(lp -> learningPaths.add(lp.getID() + ". " + lp.getTitulo()));
		
		pCentro.setLayout(new BorderLayout());
		
		modeloDatos = new DefaultListModel<>();
		modeloDatos.addAll(learningPaths);
        listaLP = new JList<>(modeloDatos);
        listaLP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaLP.addListSelectionListener(this);
        
        JScrollPane listaScroll = new JScrollPane(listaLP);
        listaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pCentro.add(listaScroll, BorderLayout.CENTER);
		
		pCentral.add(pCentro, BorderLayout.CENTER);
		pCentral.add(pSur, BorderLayout.SOUTH);
	}
	
	private void cerrarSesion() {
		JOptionPane.showMessageDialog(this, "Saliendo de la sesion");
		interfaz.cambiarPanel("inicial");
	}
	
	private void reiniciarPCentral() {
		central.guardarDatos();
		remove(pCentral);
		pCentral = new JPanel();
		add(pCentral, BorderLayout.CENTER);
		repaint();
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrarSesion) {
			cerrarSesion();
		} else {
			if (e.getSource() == btnCrearLearningPath) {
				crearLearningPath();
			}
			if (e.getSource() == btnEditarLearningPath) {
				editarLearningPath();
			}
			if (e.getSource() == btnEliminarLearningPath) {
				eliminarLearningPath();
			}
			if (e.getSource() == btnCrearActividad) {
				crearActividad();
			}
			if (e.getSource() == btnGestionarActividad) {
				gestionarActividad();
			}
			if (e.getSource() == btnEliminarActividad) {
				eliminarActividad();
			}
			if (e.getSource() == btnConsultarLearningPathsCreados) {
				consultarLearningPaths();
			}
			repaint();
			validate();
		}
		if (e.getActionCommand() == cancelar) {
			reiniciarPCentral();
			if (ID >= 0) {
				ID = -1;
			}
		}
		if (e.getActionCommand() == confirmarCLP) {
			central.crearLearningPath(pLP.getTitle(), pLP.getDescripcion(), pLP.getObjetivo(), pLP.getActividades());
			pLP = null;
			reiniciarPCentral();
		}
		if (e.getActionCommand() == confirmarELP) {
			if (cambio.contains("Título")) {
				central.cambiarTituloLearningPath(ID, cambio);
			} else if (cambio.contains("Descripción")) {
				central.cambiarDescrpcionLearningPath(ID, cambio);
			} else if (cambio.contains("Objetivo")) {
				central.cambiarObjetivoLearningPath(ID, cambio);
			} else if (cambio.contains("Añadir")) {
				central.agregarActividadLearningPath(ID, pCentralEditarLPAct.getLearningPathSeleccionado());
			} else if (cambio.contains("Eliminar")) {
				central.eliminarsActividadLearningPath(ID, pCentralEditarLPAct.getLearningPathSeleccionado());
			}
			pCentralEditarLP = null;
			pCentralEditarLPAct = null;
			if (ID >= 0) {
				ID = -1;
			}
			reiniciarPCentral();
		}
		if (e.getActionCommand() == confirmarRLP) {
			central.eliminarLearningPathCreado(pEliminarLP.getLearningPathSeleccionado());
			pEliminarLP = null;
			reiniciarPCentral();
		}
		if (e.getActionCommand() == confirmarCA) {
			if (tipoAct.equals("Recurso Educativo")) {
				central.crearActividad(pRecurso.getDescripcion(), pRecurso.getObjetivo(), "RE", pRecurso.getNivel(), pRecurso.getDuracion(), new ArrayList<Integer>(), pRecurso.getRecurso(), pRecurso.getTipoRecurso(), null, null, null, 0);
				pRecurso = new PanelRecursoEducativo();
			} else if (tipoAct.equals("Tarea")) {
				List<String> ejercicios = pTarea.getEjercicios();
				central.crearActividad(pTarea.getDescripcion(), pTarea.getObjetivo(), "T", pTarea.getNivel(), pTarea.getDuracion(), new ArrayList<Integer>(), null, null, ejercicios, null, null, 0);
				pTarea = null;
			} else if (tipoAct.equals("Quiz")) {
				List<String> preguntas = pQuiz.getPreguntas();
				List<String> respuestas = pQuiz.getRespuestas();
				central.crearActividad(pQuiz.getDescripcion(), pQuiz.getObjetivo(), "Q", pQuiz.getNivel(), pQuiz.getDuracion(), new ArrayList<Integer>(), null, null, null, preguntas, respuestas, 0);
				pQuiz = null;
			} else if (tipoAct.equals("Parcial")) {
				List<String> preguntas = pParcial.getPreguntas();
				central.crearActividad(pParcial.getDescripcion(), pParcial.getObjetivo(), "P", pParcial.getNivel(), pParcial.getDuracion(), new ArrayList<Integer>(), null, null, null, preguntas, null, 0);
				pParcial = null;
			} else if (tipoAct.equals("Encuesta")) {
				List<String> preguntas = pEncuesta.getPreguntas();
				central.crearActividad(pEncuesta.getDescripcion(), pEncuesta.getObjetivo(), "E", pEncuesta.getNivel(), pEncuesta.getDuracion(), new ArrayList<Integer>(), null, null, null, preguntas, null, 0);
				pEncuesta = null;
			}
			reiniciarPCentral();
		}
		if (e.getActionCommand() == confirmarGA) {
			if (cambio.contains("Título")) {
				central.cambiarTituloLearningPath(ID, cambio);
			} else if (cambio.contains("Descripción")) {
				central.cambiarDescrpcionLearningPath(ID, cambio);
			} else if (cambio.contains("Objetivo")) {
				central.cambiarObjetivoLearningPath(ID, cambio);
			} else if (cambio.contains("Añadir")) {
				central.agregarActividadLearningPath(ID, pCentralEditarLPAct.getLearningPathSeleccionado());
			} else if (cambio.contains("Eliminar")) {
				central.eliminarsActividadLearningPath(ID, pCentralEditarLPAct.getLearningPathSeleccionado());
			}
			//pCentralEditarLP = null;
			//pCentralEditarLPAct = null;
			if (ID >= 0) {
				ID = -1;
			}
			reiniciarPCentral();
		}
		if (e.getActionCommand() == confirmarEA) {
			central.eliminarActividad(pEliminarActividad.getActividadSeleccionada());
			pEliminarActividad = null;
			reiniciarPCentral();
		}
		if (e.getActionCommand() == "atrasLP") {
			if (pCentralEditarLP != null) {
				pCentral.remove(pCentralEditarLP);
				pCentralEditarLP = null;
			}
			if (pCentralEditarLPAct != null) {
				pCentral.remove(pCentralEditarLPAct);
				pCentralEditarLPAct = null;
			}
			cambio = "";
			editarLearningPath();
			repaint();
			validate();
		}
		if (e.getActionCommand() == "atrasA") {
			//pCentral.remove();
			cambio = "";
			gestionarActividad();
			repaint();
			validate();
		}
	}
	
	public void editarLP(String pCambio) {
		reiniciarPCentral();
		
		cambio = pCambio;
		pCentral.setLayout(new BorderLayout());
		
		JPanel pSur = new JPanel();
		pSur.setLayout(new FlowLayout());
		JPanel pCentro;
		if (cambio.contains("Editar")) {
			pCentralEditarLP = new PanelEditarLP(cambio, this);
			pCentro = pCentralEditarLP;
		} else {
			List<String> listaActividades = new ArrayList<String>();
			lP.getActivdades().forEach(act -> listaActividades.add(act.getID() + ". " + act.getDescripcion()));
			pCentralEditarLPAct = new PanelEditarLPAct(listaActividades, this);
			pCentro = pCentralEditarLPAct;
		}
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand(cancelar);
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand(confirmarELP);
		
		pSur.add(btnCancelar);
		pSur.add(btnConfirmar);
		
		pCentral.add(pSur, BorderLayout.SOUTH);
		pCentral.add(pCentro, BorderLayout.CENTER);
		
		repaint();
		validate();
	}
	
	public void editarActividad(String pCambio) {
		
	}
	
	private LearningPath encontrarLearningPathPorID(int IDCamino) {
		for (LearningPath camino: caminos) {
			if (camino.getID()==IDCamino) {
				return camino;
			}
		}
		return new LearningPath("", "", "", new ArrayList<Actividad>());
	}
	
	private Actividad encontrarActividadPorID(int IDActividad) {
		for (Actividad actividad: actividades) {
			if (actividad.getID() == IDActividad) {
				return actividad;
			}
		}
		return null;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String LPSelecionado = listaLP.getSelectedValue();
	}
}