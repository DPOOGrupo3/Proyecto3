package interfaz.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelEditarLPAct extends JPanel implements ListSelectionListener, ActionListener {
	private DefaultListModel<String> modeloDatos;
	private JList<String> listaLP;
	private PanelPrincipalProfesor panelProfesor;
	private JButton btnAtras;
	
	public PanelEditarLPAct(List<String> actividades, PanelPrincipalProfesor panelProfesor) {
		this.panelProfesor = panelProfesor;
		
		setLayout(new BorderLayout());
		
		modeloDatos = new DefaultListModel<>();
		modeloDatos.addAll(actividades);
	    listaLP = new JList<>(modeloDatos);
	    listaLP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    listaLP.addListSelectionListener(this);
	    btnAtras = new JButton("Atras");
		btnAtras.addActionListener(this);
		btnAtras.setActionCommand("atrasLP");
	    
	    JScrollPane listaScroll = new JScrollPane(listaLP);
	    listaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    
	    add(new JLabel("Escoja la actividad deseada"), BorderLayout.NORTH);
	    add(listaScroll, BorderLayout.CENTER);
	    add(btnAtras, BorderLayout.SOUTH);
	}
	
	public int getLearningPathSeleccionado() {
		return Integer.parseInt(listaLP.getSelectedValue().split(". ")[0]);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String LPSelecionado = listaLP.getSelectedValue();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAtras) {
			panelProfesor.actionPerformed(e);
		}
	}
}