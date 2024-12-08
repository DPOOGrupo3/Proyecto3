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

public class PanelIniciar extends JPanel implements ActionListener, KeyListener {
	private JLabel lblVacio = new JLabel(" ");
	private JLabel lblCorreo;
	private JLabel lblPassword;
	private JTextField txtCorreo;
	private JTextField txtPassword;
	private JButton btnLogIn;
	private JButton btnAtras;
	private InterfazCentral interfaz;
	
	public PanelIniciar (InterfazCentral interfaz) {
		this.interfaz = interfaz;
		
		setLayout(new GridLayout(7,1));
		
		lblCorreo = new JLabel("Login:");
		add(lblCorreo);
		
		txtCorreo = new JTextField("Ingrese su login");
		txtCorreo.setForeground(Color.GRAY);
		txtCorreo.addActionListener(this);
		txtCorreo.addKeyListener(this);
		add(txtCorreo);
		
		lblPassword = new JLabel("Contraseña:");
		add(lblPassword);
		
		txtPassword = new JTextField("Ingrese su contraseña");
		txtPassword.setForeground(Color.GRAY);
		txtPassword.addActionListener(this);
		txtPassword.addKeyListener(this);
		add(txtPassword);
		
		btnLogIn = new JButton("Iniciar Sesión");
		btnLogIn.addActionListener(this);
		add(btnLogIn);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(this);
		add(btnAtras);
	}
	
	private void verificarCorreo() {
		String[] correos = {"@gmail.com", "@hotmail.com", "@outlook.com", "@yahoo.com", "@uniandes.edu.co"};
		boolean correcto = false;
		for (String correo: correos) {
			if (txtCorreo.getText().contains(correo)) {
				correcto = true;
			}
		}
		if (correcto) {
			iniciarSesion(txtCorreo.getText().split("@")[0], txtPassword.getText());
		} else {
			iniciarSesion(txtCorreo.getText(), txtPassword.getText());
		}
	}
	
	private void verificarTextoVacio() {
		if (txtCorreo.getText().isBlank() || txtCorreo.getText().equals("Ingrese su correo") || txtPassword.getText().isBlank() || txtPassword.getText().equals("Ingrese su contraseña")) {
			JOptionPane.showMessageDialog(this, "No ha rellenado uno o más campos", "Error campo vacio", JOptionPane.WARNING_MESSAGE );
		} else {
			verificarCorreo();
		}
	}
	
	private void iniciarSesion(String mail, String contra) {
		interfaz.iniciarSesion(mail, contra);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == txtCorreo || e.getSource() == txtPassword) {
			verificarTextoVacio();
		}
		if (e.getSource() == btnLogIn) {
			verificarTextoVacio();
		} else if (e.getSource() == btnAtras) {
			interfaz.cambiarPanel("inicial");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == txtCorreo) {
			if (txtCorreo.getText().equals("Ingrese su login")) {
				txtCorreo.setText("");
				txtCorreo.setForeground(Color.BLACK);
			}
		} else if (e.getSource() == txtPassword) {
			if (txtPassword.getText().equals("Ingrese su contraseña")) {
				txtPassword.setText("");
				txtPassword.setForeground(Color.BLACK);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}