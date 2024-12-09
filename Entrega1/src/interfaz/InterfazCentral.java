package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import interfaz.panel.PanelInicial;
import interfaz.panel.PanelIniciar;
import interfaz.panel.PanelPrincipalProfesor;
import interfaz.panel.PanelRegistrar;
import modelo.CentralLogica;
import modelo.usuario.Estudiante;
import modelo.usuario.Profesor;
import modelo.usuario.Usuario;

import javax.swing.JLabel;

public class InterfazCentral extends InterfazBasica {
	private final String bienvinidaGenerica = "Bienvenido(a) a Learning Path Recomendation System";
	private JPanel panelNorte;
	private JLabel lblBienvenido = new JLabel("");
	private JPanel panelSur;
    private JPanel panelEste;
    
    private PanelInicial pInicial;
    private PanelRegistrar pRegistrar = null;
    private PanelIniciar pIniciar = null;
    private PanelPrincipalProfesor pInProfesor = null;
    private InterfazEstudiante iEstudiante = null;
    
    private CentralLogica logica;
    private Usuario user = null;
	
	public InterfazCentral() {
		int tamX = 665;
    	int tamY = 585;
    	
    	setSize(tamX, tamY);
    	setResizable(false);
        setTitle("LPRS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        panelNorte = new JPanel();
        lblBienvenido.setText(bienvinidaGenerica);
        lblBienvenido.setForeground(Color.WHITE);
        panelNorte.add(lblBienvenido);
        panelNorte.setBackground(new Color(0, 119, 255));
        add(panelNorte, BorderLayout.NORTH);
        
        panelSur = new JPanel();
        add(panelSur, BorderLayout.SOUTH);
        
        pInicial = new PanelInicial(this);
        add(pInicial, BorderLayout.CENTER);
        
        panelEste = new JPanel();
        add(panelEste, BorderLayout.EAST);
        
        logica = new CentralLogica();
        logica.cargarDatos();
	}
	
	public void cambiarPanel(String panelNombre) {
		if (panelNombre == "inicial") {
			if (pRegistrar != null) {
				remove(pRegistrar);
			}
			if (pIniciar != null) {
				remove(pIniciar);
			}
			if (pInProfesor != null) {
				remove(pInProfesor);
			}
			if (iEstudiante != null) {
				remove(iEstudiante);
			}
			lblBienvenido.setText(bienvinidaGenerica);
			add(pInicial, BorderLayout.CENTER);
		}
		if (panelNombre == "registrar") {
			remove(pInicial);
			pRegistrar = new PanelRegistrar(this);
			add(pRegistrar, BorderLayout.CENTER);
		}
		if (panelNombre == "iniciar") {
			remove(pInicial);
			pIniciar = new PanelIniciar(this);
			add(pIniciar, BorderLayout.CENTER);
		}
		if (panelNombre == "profesorIni") {
			lblBienvenido.setText("Bienvenido(a) " + user.getNombre());
			remove(pIniciar);
			add(pInProfesor, BorderLayout.CENTER);
		}
		if (panelNombre == "estudianteIni") {
			lblBienvenido.setText("Bienvenido(a) " + user.getNombre());
			remove(pIniciar);
			add(iEstudiante, BorderLayout.CENTER);
		}
		repaint();
		validate();
	}
	
	public void salir() {
		dispose();
	}
	
	public void registrarUsuario(String nombre, String correo, String password, String tipoUsuario) {
		logica.registrarUsuario(nombre, correo, password, tipoUsuario);
	}
	
	public void iniciarSesion(String mail, String contra) {
		user = logica.iniciarSesion(mail, contra);
		if (user instanceof Profesor) {
			pInProfesor = new PanelPrincipalProfesor((Profesor) user, this, logica);
			cambiarPanel("profesorIni");
		} else if (user instanceof Estudiante) {
			iEstudiante = new InterfazEstudiante((Estudiante) user, logica, this);
			cambiarPanel("estudianteIni");
		}
	}
	
	public static void main(String[] args) {
		InterfazCentral interfaz = new InterfazCentral();
    	interfaz.setLocationRelativeTo(null);
    	interfaz.setVisible(true);
    }
}