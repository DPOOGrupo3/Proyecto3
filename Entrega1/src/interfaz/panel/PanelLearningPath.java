package interfaz.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.actividad.Actividad;

public class PanelLearningPath extends JPanel implements ActionListener {
	private JLabel lblTitle = new JLabel("Ingrese el título del Learning Path:");
	private JLabel lblDescripcion = new JLabel("Ingrese la descripción del Learning Path:");
	private JLabel lblObjetivo = new JLabel("Ingrese el objetivo del Learning Path:");
	private JLabel lblActividades = new JLabel("Sleccione las actividades del Learning Path:");
	private JTextField txtTitle = new JTextField();
	private JTextField txtDescripcion = new JTextField();
	private JTextField txtObjetivo = new JTextField();
	private JTextField txtActividades = new JTextField();
	private JButton btnAnadirActividad = new JButton("Añadir Actividad");
	private List<String> actividadesDispo = new ArrayList<String>();
	private List<Integer> actividades = new ArrayList<Integer>();
	
	public PanelLearningPath(List<Actividad> actividadesDispo) {
		actividadesDispo.forEach(act -> this.actividadesDispo.add(String.valueOf(act.getID())));
		
		setLayout(new GridLayout(5, 2));
		
		add(lblTitle);
		add(txtTitle);
		add(lblDescripcion);
		add(txtDescripcion);
		add(lblObjetivo);
		add(txtObjetivo);
		add(lblActividades);
		if (actividadesDispo.size() > 0) {
			txtActividades.setEnabled(false);
			add(txtActividades);
			add(new JLabel(" "));
			btnAnadirActividad.addActionListener(this);
			add(btnAnadirActividad);
		} else {
			add(new JLabel("No hay actividades disponibles"));
		}
	}
	
	public String getTitle() {
		return txtTitle.getText();
	}
	
	public String getDescripcion() {
		return txtDescripcion.getText();
	}
	
	public String getObjetivo() {
		return txtObjetivo.getText();
	}
	
	public List<Integer> getActividades() {
		return actividades;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAnadirActividad) {
			String[] actividades = actividadesDispo.toArray(String[]::new);
			String IDAct = (String) JOptionPane.showInputDialog(this, "Seleccione una actividad:", "Actividades Disponibles", JOptionPane.QUESTION_MESSAGE, null, actividades, actividades[0]);
			if (txtActividades.getText().equals("")) {
				txtActividades.setText(IDAct);
			} else {
				txtActividades.setText(txtActividades.getText() + ", " + IDAct);
			}
			this.actividades.add(Integer.parseInt(IDAct));
			actividadesDispo.remove(IDAct);
		}
	}
}
