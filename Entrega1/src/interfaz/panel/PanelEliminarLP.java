package interfaz.panel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelEliminarLP extends JPanel implements ListSelectionListener {
	private DefaultListModel<String> modeloDatos;
	private JList<String> listaLP;
	
	public PanelEliminarLP(List<String> learningPaths) {
		setLayout(new BorderLayout());
		
		modeloDatos = new DefaultListModel<>();
		modeloDatos.addAll(learningPaths);
        listaLP = new JList<>(modeloDatos);
        listaLP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaLP.addListSelectionListener(this);
        
        JScrollPane listaScroll = new JScrollPane(listaLP);
        listaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(listaScroll, BorderLayout.CENTER);
	}
	
	public int getLearningPathSeleccionado() {
		return Integer.parseInt(listaLP.getSelectedValue().split(". ")[0]);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String LPSelecionado = listaLP.getSelectedValue();
	}
}