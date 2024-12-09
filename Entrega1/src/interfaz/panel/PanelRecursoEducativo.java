package interfaz.panel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class PanelRecursoEducativo extends PanelActividad {
	private JLabel lblTipoRecurso = new JLabel("Ingrese el tipo de recurso educativo:");
	private JLabel lblRecurso = new JLabel("Ingrese el recurso educativo:");
	private JTextField txtTipoRecurso = new JTextField();
	private JTextField txtRecurso = new JTextField();
	
	public PanelRecursoEducativo() {
		setLayout(new GridLayout(6, 2, 15, 20));
		add(lblDescripcion);
		add(txtDescripcion);
		add(lblObjetivo);
		add(txtObjetivo);
		add(lblNivel);
		add(txtNivel);
		add(lblDuracion);
		add(txtDuracion);
		add(lblTipoRecurso);
		add(txtTipoRecurso);
		add(lblRecurso);
		add(txtRecurso);
	}
	
	public String getTipoRecurso() {
		return txtTipoRecurso.getText();
	}
	
	public String getRecurso() {
		return txtRecurso.getText();
	}
}