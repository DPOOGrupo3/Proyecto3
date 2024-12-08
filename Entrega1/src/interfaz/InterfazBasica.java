package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class InterfazBasica extends JFrame {
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelCentro;
    private JPanel panelEste;
    
    public InterfazBasica() {
    	int tamX = 665;
    	int tamY = 585;
    	
    	setSize(tamX, tamY);
    	setResizable(false);
        setTitle("LPRS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        panelNorte = new JPanel();
        add(panelNorte, BorderLayout.NORTH);
        
        panelSur = new JPanel();
        add(panelSur, BorderLayout.SOUTH);
        
        panelCentro = new JPanel();
        add(panelCentro, BorderLayout.CENTER);
        
        panelEste = new JPanel();
        add(panelEste, BorderLayout.EAST);
	}
}