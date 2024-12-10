package interfaz.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelEditarLP extends JPanel implements ActionListener {
	private JLabel lblvacio = new JLabel(" ");
	private JLabel lblCambio;
	private JTextField txtCambio;
	private JButton btnAtras;
	private PanelPrincipalProfesor panelProfesor;
	
	public PanelEditarLP(String cambio, PanelPrincipalProfesor panelProfesor) {
		this.panelProfesor = panelProfesor;
		
		setLayout(new GridLayout(5, 1, 10, 10));
		
		lblCambio =  new JLabel("Ingrese el nuevo " + cambio.replace("Editar ", "").toLowerCase() + ":");
		if (cambio.contains("Descripción")) {
			lblCambio.setText(lblCambio.getText().replace("el", "la"));
		}
		add(lblCambio);
		add(lblvacio);
		txtCambio = new JTextField();
		add(txtCambio);
		add(lblvacio);
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(this);
		btnAtras.setActionCommand("atrasLP");
		add(btnAtras);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAtras) {
			panelProfesor.actionPerformed(e);
		}
	}
	
	public String obtenerCambio () {
		return txtCambio.getText();
	}
}