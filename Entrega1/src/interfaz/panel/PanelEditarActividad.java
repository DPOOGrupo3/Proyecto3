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
	
	public PanelEditarActividad(String tipo) {
		if (tipo.equals("RE")) {
			setLayout(new GridLayout(6, 2, 10, 10));
			
			btnEditarDescripcion = new JButton("Editar Descripcion");
			btnEditarObjetivo = new JButton("Editar Objetivo");
			btnEditarNivel = new JButton("Editar Nivel");
			btnEditarDuracion = new JButton("Editar Duracion");
			btnEditarTipoRescurso = new JButton("Editar Tipo del Recurso");
			btnEditarRescurso = new JButton("Editar Recurso");
			add(btnEditarDescripcion);
			add(btnEditarObjetivo);
			add(btnEditarNivel);
			add(btnEditarDuracion);
			add(btnEditarTipoRescurso);
			add(btnEditarRescurso);
		}
		if (tipo.equals("T")) {
			setLayout(new GridLayout(5, 1, 10, 10));
			
			btnEditarDescripcion = new JButton("Editar Descripcion");
			btnEditarObjetivo = new JButton("Editar Objetivo");
			btnEditarNivel = new JButton("Editar Nivel");
			btnEditarDuracion = new JButton("Editar Duracion");
			btnEditarEjercicios = new JButton("Editar Ejercicios");
			add(btnEditarDescripcion);
			add(btnEditarObjetivo);
			add(btnEditarNivel);
			add(btnEditarDuracion);
			add(btnEditarEjercicios);
		}
		if (tipo.equals("Q")) {
			setLayout(new GridLayout(7, 1, 10, 10));
			
			btnEditarDescripcion = new JButton("Editar Descripcion");
			btnEditarObjetivo = new JButton("Editar Objetivo");
			btnEditarNivel = new JButton("Editar Nivel");
			btnEditarDuracion = new JButton("Editar Duracion");
			btnEditarPreguntas = new JButton("Editar Preguntas");
			btnEditarRespuestas = new JButton("Editar Respuestas");
			btnEditarCalificacionMin = new JButton("Editar Calificaicón Minima");
			add(btnEditarDescripcion);
			add(btnEditarObjetivo);
			add(btnEditarNivel);
			add(btnEditarDuracion);
			add(btnEditarPreguntas);
			add(btnEditarRespuestas);
			add(btnEditarCalificacionMin);
		}
		if (tipo.equals("P")) {
			setLayout(new GridLayout(5, 1, 10, 10));
			
			btnEditarDescripcion = new JButton("Editar Descripcion");
			btnEditarObjetivo = new JButton("Editar Objetivo");
			btnEditarNivel = new JButton("Editar Nivel");
			btnEditarDuracion = new JButton("Editar Duracion");
			btnEditarPreguntas = new JButton("Editar Preguntas");
			add(btnEditarDescripcion);
			add(btnEditarObjetivo);
			add(btnEditarNivel);
			add(btnEditarDuracion);
			add(btnEditarPreguntas);
		}
		if (tipo.equals("E")) {
			setLayout(new GridLayout(5, 1, 10, 10));
			
			btnEditarDescripcion = new JButton("Editar Descripcion");
			btnEditarObjetivo = new JButton("Editar Objetivo");
			btnEditarNivel = new JButton("Editar Nivel");
			btnEditarDuracion = new JButton("Editar Duracion");
			btnEditarPreguntas = new JButton("Editar Preguntas");
			add(btnEditarDescripcion);
			add(btnEditarObjetivo);
			add(btnEditarNivel);
			add(btnEditarDuracion);
			add(btnEditarPreguntas);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}