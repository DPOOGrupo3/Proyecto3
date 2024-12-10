package interfaz.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelEditarActividad extends JPanel implements ActionListener {
	private JButton btnEditarDescripcion;
	private JButton btnEditarObjetivo;
	private JButton btnEditarNivel;
	private JButton btnEditarDuracion;
	private JButton btnEditarTipoRescurso;
	private JButton btnEditarRescurso;
	private JButton btnEditarEjercicios;
	private JButton btnEditarPreguntas;
	private JButton btnEditarRespuestas;
	private JButton btnEditarCalificacionMin;
	
	public PanelEditarActividad(String tipo, PanelPrincipalProfesor panelProfesor) {
		if (tipo.equals("RE")) {
			setLayout(new GridLayout(6, 2, 10, 10));
			
			btnEditarDescripcion = new JButton("Editar Descripcion");
			add(btnEditarDescripcion);
			btnEditarObjetivo = new JButton("Editar Objetivo");
			add(btnEditarObjetivo);
			btnEditarNivel = new JButton("Editar Nivel");
			add(btnEditarNivel);
			btnEditarDuracion = new JButton("Editar Duracion");
			add(btnEditarDuracion);
			btnEditarTipoRescurso = new JButton("Editar Tipo del Recurso");
			add(btnEditarTipoRescurso);
			btnEditarRescurso = new JButton("Editar Recurso");
			add(btnEditarRescurso);
		}
		if (tipo.equals("T")) {
			setLayout(new GridLayout(5, 1, 10, 10));
			
			btnEditarDescripcion = new JButton("Editar Descripcion");
			add(btnEditarDescripcion);
			btnEditarObjetivo = new JButton("Editar Objetivo");
			add(btnEditarObjetivo);
			btnEditarNivel = new JButton("Editar Nivel");
			add(btnEditarNivel);
			btnEditarDuracion = new JButton("Editar Duracion");
			add(btnEditarDuracion);
			btnEditarEjercicios = new JButton("Editar Ejercicios");
			add(btnEditarEjercicios);
		}
		if (tipo.equals("Q")) {
			setLayout(new GridLayout(7, 1, 10, 10));
			
			btnEditarDescripcion = new JButton("Editar Descripcion");
			add(btnEditarDescripcion);
			btnEditarObjetivo = new JButton("Editar Objetivo");
			add(btnEditarObjetivo);
			btnEditarNivel = new JButton("Editar Nivel");
			add(btnEditarNivel);
			btnEditarDuracion = new JButton("Editar Duracion");
			add(btnEditarDuracion);
			btnEditarPreguntas = new JButton("Editar Preguntas");
			add(btnEditarPreguntas);
			btnEditarRespuestas = new JButton("Editar Respuestas");
			add(btnEditarRespuestas);
			btnEditarCalificacionMin = new JButton("Editar Calificaicón Minima");
			add(btnEditarCalificacionMin);
		}
		if (tipo.equals("P")) {
			setLayout(new GridLayout(5, 1, 10, 10));
			
			btnEditarDescripcion = new JButton("Editar Descripcion");
			add(btnEditarDescripcion);
			btnEditarObjetivo = new JButton("Editar Objetivo");
			add(btnEditarObjetivo);
			btnEditarNivel = new JButton("Editar Nivel");
			add(btnEditarNivel);
			btnEditarDuracion = new JButton("Editar Duracion");
			add(btnEditarDuracion);
			btnEditarPreguntas = new JButton("Editar Preguntas");
			add(btnEditarPreguntas);
		}
		if (tipo.equals("E")) {
			setLayout(new GridLayout(5, 1, 10, 10));
			
			btnEditarDescripcion = new JButton("Editar Descripcion");
			add(btnEditarDescripcion);
			btnEditarObjetivo = new JButton("Editar Objetivo");
			add(btnEditarObjetivo);
			btnEditarNivel = new JButton("Editar Nivel");
			add(btnEditarNivel);
			btnEditarDuracion = new JButton("Editar Duracion");
			add(btnEditarDuracion);
			btnEditarPreguntas = new JButton("Editar Preguntas");
			add(btnEditarPreguntas);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}