package interfaz.panel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class PanelActividad extends JPanel {
	protected JLabel lblDescripcion = new JLabel("Ingrese la descripción de la actividad:");
	protected JLabel lblObjetivo = new JLabel("Ingrese el objetivo de la actividad:");
	protected JLabel lblNivel = new JLabel("Ingrese el nivel de dificultad de la actividad:");
	protected JLabel lblDuracion = new JLabel("Ingrese la duración de la actividad:");
	protected JTextField txtDescripcion = new JTextField();
	protected JTextField txtObjetivo = new JTextField();
	protected JTextField txtNivel = new JTextField();
	protected JTextField txtDuracion = new JTextField();
	
	public String getDescripcion() {
		return txtDescripcion.getText();
	}
	
	public String getObjetivo() {
		return txtObjetivo.getText();
	}
	
	public int getNivel() {
		return Integer.parseInt(txtNivel.getText());
	}
	
	public Double getDuracion() {
		return Double.parseDouble(txtDuracion.getText());
	}
}