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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interfaz.InterfazCentral;

public class PanelRegistrar extends JPanel implements ActionListener, KeyListener {
	private JLabel lblVacio = new JLabel(" ");
	private JLabel lblNombre;
	private JLabel lblCorreo;
	private JLabel lblPassword;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JPasswordField txtPassword;
	private JButton btnRegistrarse;
	private JButton btnAtras;
	private InterfazCentral interfaz;
	
	public PanelRegistrar (InterfazCentral interfaz) {
		this.interfaz = interfaz;
		
		setLayout(new GridLayout(7,1));
		
		lblNombre = new JLabel("Nombre:");
		add(lblNombre);
		
		txtNombre = new JTextField("Ingrese su nombre");
		txtNombre.setForeground(Color.GRAY);
		txtNombre.addActionListener(this);
		txtNombre.addKeyListener(this);
		add(txtNombre);
		
		lblCorreo = new JLabel("Correo:");
		add(lblCorreo);
		
		txtCorreo = new JTextField("Ingrese su correo");
		txtCorreo.setForeground(Color.GRAY);
		txtCorreo.addActionListener(this);
		txtCorreo.addKeyListener(this);
		add(txtCorreo);
		
		lblPassword = new JLabel("Contraseña:");
		add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.addActionListener(this);
		txtPassword.addKeyListener(this);
		add(txtPassword);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(this);
		add(btnRegistrarse);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(this);
		add(btnAtras);
	}
	
	private void verificarTextoVacio() {
		if (txtNombre.getText().isBlank() || txtNombre.getText().equals("Ingrese su nombre") || txtCorreo.getText().isBlank() || txtCorreo.getText().equals("Ingrese su correo") || txtPassword.getText().isBlank() || txtPassword.getText().equals("Ingrese su contraseña")) {
			JOptionPane.showMessageDialog(this, "No ha rellenado uno o más campos", "Error campo vacio", JOptionPane.WARNING_MESSAGE );
		} else {
			verificarCorreo();
		}
	}
	
	private void verificarCorreo() {
		String[] correos = {"@gmail.com", "@hotmail.com", "@outlook.com", "@yahoo.com", "@uniandes.edu.co"};
		boolean correcto = false;
		for (String correo: correos) {
			if (txtCorreo.getText().contains(correo)) {
				correcto = true;
			}
		}
		if (!correcto) {
			JOptionPane.showMessageDialog(this, "El formato del correo es incorrecto", "Error correo", JOptionPane.WARNING_MESSAGE );
		} else {
			registrarUsuario();
		}
	}
	
	private void registrarUsuario() {
		interfaz.registrarUsuario(txtNombre.getText(), txtCorreo.getText(), txtPassword.getText(), "Profesor");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == txtNombre || e.getSource() == txtCorreo || e.getSource() == txtPassword) {
			verificarTextoVacio();
		}
		if (e.getSource() == btnRegistrarse) {
			verificarTextoVacio();
		} else if (e.getSource() == btnAtras) {
			interfaz.cambiarPanel("inicial");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == txtNombre) {
			if (txtNombre.getText().equals("Ingrese su nombre")) {
				txtNombre.setText("");
				txtNombre.setForeground(Color.BLACK);
			}
		} else if (e.getSource() == txtCorreo) {
			if (txtCorreo.getText().equals("Ingrese su correo")) {
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