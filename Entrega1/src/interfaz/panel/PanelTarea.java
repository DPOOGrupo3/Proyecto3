package interfaz.panel;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class PanelTarea extends PanelActividad implements KeyListener {
	private List<String> ejercicios =  new ArrayList<String>();
	private int rows;
	
	public PanelTarea(int cantEjercicios) {
		rows = cantEjercicios;
		setLayout(new GridLayout(4 + rows, 2));
		
		add(lblDescripcion);
		add(txtDescripcion);
		add(lblObjetivo);
		add(txtObjetivo);
		add(lblNivel);
		add(txtNivel);
		add(lblDuracion);
		add(txtDuracion);
		for (int i = 0; i < rows; i++) {
			add(new JLabel("Ingrese el ejercicio #" + String.valueOf(i+1) + ":"));
			JTextField ejercicio = new JTextField("Ejercicio #" + String.valueOf(i+1));
			add(ejercicio);
		}
	}
	
	public List<String> getEjercicios() {
		for (Component comp: this.getComponents()) {
			if (comp instanceof JTextField) {
				if (((JTextField) comp).getText().contains("Ejercicio")) {
					ejercicios.add(((JTextField) comp).getText());
					System.out.println(((JTextField) comp).getText());
				}
			}
		}
		return ejercicios;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (((JTextField) e.getSource()).getText().contains("Ejercicio #")) {
			((JTextField) e.getSource()).setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}