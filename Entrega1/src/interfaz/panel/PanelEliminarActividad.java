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

public class PanelEliminarActividad extends JPanel implements ListSelectionListener {
	private DefaultListModel<String> modeloDatos;
	private JList<String> listaActividades;
	
	public PanelEliminarActividad(List<String> actividades) {
		setLayout(new BorderLayout());
		
		modeloDatos = new DefaultListModel<>();
		modeloDatos.addAll(actividades);
        listaActividades = new JList<>(modeloDatos);
        listaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaActividades.addListSelectionListener(this);
        
        JScrollPane listaScroll = new JScrollPane(listaActividades);
        listaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(listaScroll, BorderLayout.CENTER);
	}
	
	public int getActividadSeleccionada() {
		return Integer.parseInt(listaActividades.getSelectedValue().split(". ")[0]);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String actividadSelecionado = listaActividades.getSelectedValue();
	}
}