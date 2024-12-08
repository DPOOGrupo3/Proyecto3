package interfaz.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaz.InterfazCentral;

public class PanelInicial extends JPanel implements ActionListener {
	private JLabel lblVacio = new JLabel(" ");
	private JButton btnRegistrarse;
	private JButton btnIniciarSesion;
	private JButton btnSalir;
	private InterfazCentral interfaz;
	
	public PanelInicial(InterfazCentral interfaz) {
		this.interfaz = interfaz;
		
		setLayout(new GridLayout(7,1));
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(this);
		add(btnRegistrarse);
		
		btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.addActionListener(this);
		add(btnIniciarSesion);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		add(btnSalir);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrarse) {
			interfaz.cambiarPanel("registrar");
		} else if (e.getSource() == btnIniciarSesion) {
			interfaz.cambiarPanel("iniciar");
		} else if (e.getSource() == btnSalir) {
			interfaz.salir();
		}
	}
}