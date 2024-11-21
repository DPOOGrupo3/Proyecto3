package test;

import modelo.LearningPath;
import modelo.actividad.Actividad;
import modelo.actividad.RecursoEducativo;
import modelo.usuario.Profesor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfesorTest {

    private Profesor profesor;
    private LearningPath caminoDPOO;
    private LearningPath caminoInfratec;
    private Actividad actividadEDA;
    private Actividad actividadCalculoDiferencial;

    @BeforeEach
    void setUp() {
        
        profesor = new Profesor("Jeronimo Vasquez", "j.vasquezp@gmail.com", "Jero123");

        
        actividadEDA = new RecursoEducativo("EDA", "Aprender estructuras de datos y algoritmos", "RE", 3, 50.0, new ArrayList<>(), "EDA_Libro.pdf", "PDF");
        actividadCalculoDiferencial = new RecursoEducativo("Cálculo Diferencial", "Estudiar conceptos básicos de cálculo diferencial", "RE", 4, 60.0, new ArrayList<>(), "Calculo.pdf", "PDF");

        
        List<Actividad> actividadesDPOO = new ArrayList<>();
        actividadesDPOO.add(actividadEDA);

        caminoDPOO = profesor.crearLearningPath("DPOO", "Aprender programación orientada a objetos", "Estudiar conceptos avanzados", actividadesDPOO);

        List<Actividad> actividadesInfratec = new ArrayList<>();
        actividadesInfratec.add(actividadEDA);
        actividadesInfratec.add(actividadCalculoDiferencial);

        caminoInfratec = profesor.crearLearningPath("Infratec", "Aprender sobre infraestructura tecnológica", "Explorar hardware y software", actividadesInfratec);
    }
    
    // -----------------------------------------------------------------------
    // Pruebas relacionadas con Learning Paths
    // -----------------------------------------------------------------------

    @Test
    void testCrearLearningPath() {
        assertEquals(2, profesor.getCaminosCreados().size(), "El profesor debe tener 2 Learning Paths creados.");
        assertTrue(profesor.getCaminosCreados().contains(caminoDPOO), "El Learning Path DPOO debe existir.");
        assertTrue(profesor.getCaminosCreados().contains(caminoInfratec), "El Learning Path Infratec debe existir.");
    }

    @Test
    void testEliminarLearningPath() {
        profesor.eliminarLearningPathCreado(caminoDPOO);
        assertEquals(1, profesor.getCaminosCreados().size(), "Debe quedar 1 Learning Path después de eliminar uno.");
        assertFalse(profesor.getCaminosCreados().contains(caminoDPOO), "El Learning Path DPOO no debe existir.");
    }

    @Test
    void testCambiarTituloLearningPath() {
        profesor.cambiarTituloLearningPath(caminoDPOO, "DPOO Avanzado");
        assertEquals("DPOO Avanzado", caminoDPOO.getTitulo(), "El título del Learning Path debe haberse actualizado.");
    }

    @Test
    void testCambiarDescripcionLearningPath() {
        profesor.cambiarDescripcionLearningPath(caminoInfratec, "Nuevo contenido para infraestructura tecnológica.");
        assertEquals("Nuevo contenido para infraestructura tecnológica.", caminoInfratec.getDescripcion(), "La descripción debe haberse actualizado.");
    }

    @Test
    void testCambiarObjetivoLearningPath() {
        profesor.cambiarObjetivoLearningPath(caminoDPOO, "Aprender POO con ejemplos prácticos.");
        assertEquals("Aprender POO con ejemplos prácticos.", caminoDPOO.getObjetivo(), "El objetivo debe haberse actualizado.");
    }

    @Test
    void testAgregarActividadLearningPath() {
        Actividad nuevaActividad = new RecursoEducativo("Arquitectura de Software", "Estudiar patrones de diseño", "RE", 5, 90.0, new ArrayList<>(), "Arquitectura.pdf", "PDF");
        profesor.agregarActividadLearningPath(caminoDPOO, nuevaActividad);
        assertEquals(2, caminoDPOO.getActivdades().size(), "Debe haber 2 actividades en el Learning Path DPOO.");
        assertTrue(caminoDPOO.getActivdades().contains(nuevaActividad), "La nueva actividad debe estar en el Learning Path.");
    }

    @Test
    void testEliminarActividadLearningPath() {
        profesor.eliminarActividadLearningPath(caminoInfratec, actividadEDA);
        assertEquals(1, caminoInfratec.getActivdades().size(), "Debe haber 1 actividad en el Learning Path Infratec.");
        assertFalse(caminoInfratec.getActivdades().contains(actividadEDA), "La actividad EDA no debe estar en el Learning Path.");
    }
    
    
    // -----------------------------------------------------------------------
    // Pruebas relacionadas con Actividades
    // -----------------------------------------------------------------------

    @Test
    void testCambiarDescripcionActividad() {
        profesor.cambiarDescripcionActividad(actividadEDA, "Estudiar estructuras avanzadas.");
        assertEquals("Estudiar estructuras avanzadas.", actividadEDA.getDescripcion(), "La descripción de la actividad debe haberse actualizado.");
    }

    @Test
    void testCambiarObjetivoActividad() {
        profesor.cambiarObjetivoActividad(actividadCalculoDiferencial, "Dominar el cálculo diferencial.");
        assertEquals("Dominar el cálculo diferencial.", actividadCalculoDiferencial.getObjetivo(), "El objetivo de la actividad debe haberse actualizado.");
    }

    @Test
    void testAgregarPreRequisitosActividad() {
        Actividad nuevaActividad = new RecursoEducativo("Programación Básica", "Introducción a la programación", "RE", 2, 30.0, new ArrayList<>(), "Programacion.pdf", "PDF");
        profesor.agregarPreRequisitosActividad(actividadEDA, nuevaActividad);
        assertTrue(actividadEDA.getPreRequisitos().contains(nuevaActividad), "El nuevo pre-requisito debe estar en la actividad.");
    }

    @Test
    void testEliminarPreRequisitosActividad() {
        Actividad nuevaActividad = new RecursoEducativo("Matemáticas Básicas", "Conceptos fundamentales", "RE", 1, 20.0, new ArrayList<>(), "Matematicas.pdf", "PDF");
        actividadEDA.agregarPreRequisito(nuevaActividad);
        profesor.eliminarPreRequisitosActividad(actividadEDA, nuevaActividad);
        assertFalse(actividadEDA.getPreRequisitos().contains(nuevaActividad), "El pre-requisito debe haber sido eliminado.");
    }


    @Test
    void testCasosExtremos() {
        profesor.cambiarDuracionEsperadaActividad(actividadEDA, Double.MAX_VALUE, caminoDPOO);
        assertEquals(Double.MAX_VALUE, actividadEDA.getDuracionEsperada(), "La duración esperada debe admitir valores extremos.");

        profesor.cambiarNivelDificultadActividad(actividadCalculoDiferencial, Integer.MAX_VALUE, caminoInfratec);
        assertEquals(Integer.MAX_VALUE, actividadCalculoDiferencial.getNivelDificultad(), "El nivel de dificultad debe admitir valores extremos.");
    }
}
