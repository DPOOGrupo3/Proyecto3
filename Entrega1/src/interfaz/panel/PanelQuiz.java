package interfaz.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class PanelQuiz extends PanelActividad implements KeyListener {
	private List<String> preguntas =  new ArrayList<String>();
	private List<String> respuestas =  new ArrayList<String>();
	private int rows;
	
	public PanelQuiz(int cantPreguntas) {
		rows = cantPreguntas;
		setLayout(new GridLayout(4 + (rows*2), 2));
		
		add(lblDescripcion);
		add(txtDescripcion);
		add(lblObjetivo);
		add(txtObjetivo);
		add(lblNivel);
		add(txtNivel);
		add(lblDuracion);
		add(txtDuracion);
		for (int i = 0; i < rows; i++) {
			add(new JLabel("Ingrese la pregunta #" + String.valueOf(i+1) + ":"));
			JTextField txtPregunta = new JTextField("Pregunta #" + String.valueOf(i+1));
			txtPregunta.addKeyListener(this);
			add(txtPregunta);
			add(new JLabel("Ingrese la respuesta #" + String.valueOf(i+1) + ":"));
			JTextField txtRespuesta = new JTextField("Respuesta #" + String.valueOf(i+1));
			txtRespuesta.addKeyListener(this);
			add(txtRespuesta);
		}
	}
	
	public List<String> getPreguntas() {
		for (Component comp: this.getComponents()) {
			if (comp instanceof JTextField) {
				if (((JTextField) comp).getText().contains("Pregunta")) {
					preguntas.add(((JTextField) comp).getText());
				}
			}
		}
		return preguntas;
	}
	
	public List<String> getRespuestas() {
		for (Component comp: this.getComponents()) {
			if (comp instanceof JTextField) {
				if (((JTextField) comp).getText().contains("Respuesta")) {
					respuestas.add(((JTextField) comp).getText());
				}
			}
		}
		return respuestas;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (((JTextField) e.getSource()).getText().contains("Pregunta #") || ((JTextField) e.getSource()).getText().contains("Respuesta #")) {
			((JTextField) e.getSource()).setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}