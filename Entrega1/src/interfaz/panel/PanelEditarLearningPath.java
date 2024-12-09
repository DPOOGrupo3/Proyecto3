package interfaz.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelEditarLearningPath extends JPanel implements ActionListener {
	private JButton btnEditarTitulo;
	private JButton btnEditarDescripcion;
	private JButton btnEditarObjetivo;
	private JButton btnAnadirActividad;
	private JButton btnEliminarActividad;
	
	public PanelEditarLearningPath() {
		setLayout(new GridLayout(5, 1, 10, 10));
		
		btnEditarTitulo = new JButton("Editar Título");
		btnEditarTitulo.addActionListener(this);
		add(btnEditarTitulo);
		btnEditarDescripcion = new JButton("Editar Descripción");
		btnEditarDescripcion.addActionListener(this);
		add(btnEditarDescripcion);
		btnEditarObjetivo = new JButton("Editar Objetivo");
		btnEditarObjetivo.addActionListener(this);
		add(btnEditarObjetivo);
		btnAnadirActividad = new JButton("Añadir Actividad");
		btnAnadirActividad.addActionListener(this);
		add(btnAnadirActividad);
		btnEliminarActividad = new JButton("Eliminar Actividad");
		btnEliminarActividad.addActionListener(this);
		add(btnEliminarActividad);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}