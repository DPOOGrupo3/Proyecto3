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
	private PanelPrincipalProfesor panelProfesor;
	
	public PanelEditarLearningPath(PanelPrincipalProfesor panelProfesor) {
		this.panelProfesor = panelProfesor;
		
		setLayout(new GridLayout(5, 1, 10, 10));
		
		btnEditarTitulo = new JButton("Editar Título");
		btnEditarTitulo.addActionListener(this);
		btnEditarTitulo.setActionCommand(btnEditarTitulo.getText());
		add(btnEditarTitulo);
		btnEditarDescripcion = new JButton("Editar Descripción");
		btnEditarDescripcion.addActionListener(this);
		btnEditarDescripcion.setActionCommand(btnEditarDescripcion.getText());
		add(btnEditarDescripcion);
		btnEditarObjetivo = new JButton("Editar Objetivo");
		btnEditarObjetivo.addActionListener(this);
		btnEditarObjetivo.setActionCommand(btnEditarObjetivo.getText());
		add(btnEditarObjetivo);
		btnAnadirActividad = new JButton("Añadir Actividad");
		btnAnadirActividad.addActionListener(this);
		btnAnadirActividad.setActionCommand(btnAnadirActividad.getText());
		add(btnAnadirActividad);
		btnEliminarActividad = new JButton("Eliminar Actividad");
		btnEliminarActividad.addActionListener(this);
		btnEliminarActividad.setActionCommand(btnEliminarActividad.getText());
		add(btnEliminarActividad);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panelProfesor.editarLP(e.getActionCommand());
	}
}