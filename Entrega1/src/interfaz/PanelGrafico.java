package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import modelo.LearningPath;
import modelo.actividad.Actividad;
import modelo.usuario.Estudiante;

public class PanelGrafico extends JPanel {
	
	private int aRealizadas;
	private int aPendientes;
	
	
	
	/**
     * Constructor panelGrafico
     */
	 public PanelGrafico(Estudiante estudiante) {
	        calcularActividades(estudiante);
	    }
	 
	 
	 /**
	  * Método para calcular las actovidades realizadas y las actividades no realizadas por un estudiante
	  */	 
	 private void calcularActividades(Estudiante estudiante) {
		 
		 List<LearningPath> caminos = estudiante.getCaminosInscritos();
		 aRealizadas = 0;
		 aPendientes = 0;
		 
		 
		 for (LearningPath camino: caminos) {
			 aRealizadas += estudiante.getProgresoLearningPath(camino).getActividadesTerminadas().size();
			 aPendientes += estudiante.getProgresoLearningPath(camino).getActividadesPendinetes().size();
			 
		 } 
	 }
	 
	 @Override
	    protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 
		 Graphics2D gd = (Graphics2D) g;
		 
		 int total = aRealizadas + aPendientes;
		 
		 if (total == 0) {
			 return;
		 }
		 
		int width = getWidth();
		int height = getHeight();
		
		int realizadasW = (aRealizadas * width) / total;
		int pendientesW = width - realizadasW;
		
		gd.setColor(Color.GREEN);
		gd.fillRect(0, 0, realizadasW, height);
		gd.setColor(Color.RED);
		gd.fillRect(realizadasW, 0, pendientesW, height);
		gd.setColor(Color.BLACK);
		gd.drawString("Realizadas: " + aRealizadas, 20, height / 2 -10);
		gd.drawString("Pendientes: " + aPendientes, realizadasW + 20, height / 2 - 10);
	 }
	
	

}
