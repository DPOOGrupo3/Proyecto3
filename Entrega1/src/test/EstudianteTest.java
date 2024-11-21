package test;

import static org.junit.jupiter.api.Assertions.*;

import modelo.LearningPath;
import modelo.Progreso;
import modelo.actividad.Actividad;
import modelo.actividad.RecursoEducativo;
import modelo.usuario.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class EstudianteTest {
    private Estudiante estudiante;
    private LearningPath learningPath1;
    private LearningPath learningPath2;
    private Actividad actividad1;
    private Actividad actividad2;
    private Actividad actividad3;

    @BeforeEach
    void setUp() {
        
        estudiante = new Estudiante("Jeronimo Vasquez", "j.vasquezp@gmail.com", "Jero123");

       
        actividad1 = new RecursoEducativo("Descripción 1", "Objetivo 1", "RE", 1, 1.5, new ArrayList<>(), "Recurso 1", "Video");
        actividad2 = new RecursoEducativo("Descripción 2", "Objetivo 2", "RE", 2, 2.0, new ArrayList<>(), "Recurso 2", "PDF");
        actividad3 = new RecursoEducativo("Descripción 3", "Objetivo 3", "RE", 3, 3.0, new ArrayList<>(), "Recurso 3", "Sitio Web");

        
        learningPath1 = new LearningPath("DPOO", "Curso de Programación Orientada a Objetos", "Aprender POO", List.of(actividad1, actividad2));
        learningPath2 = new LearningPath("Infratec", "Curso de Infraestructura Tecnológica", "Aprender Infraestructura", List.of(actividad2, actividad3));

        
        estudiante.inscribirCamino(learningPath1);
        estudiante.inscribirCamino(learningPath2);
    }

    @Test
    void testInscribirCamino() {
        LearningPath nuevoCamino = new LearningPath("LP3", "Machine Learning", "Aprender ML", List.of());
        estudiante.inscribirCamino(nuevoCamino);

        assertTrue(estudiante.getCaminosInscritos().contains(nuevoCamino), "El estudiante debería estar inscrito en el nuevo camino.");
    }

    @Test
    void testObtenerCaminosInscritos() {
        List<LearningPath> caminosInscritos = estudiante.getCaminosInscritos();
        assertEquals(2, caminosInscritos.size(), "El estudiante debería tener 2 Learning Paths inscritos.");
        assertTrue(caminosInscritos.contains(learningPath1), "El estudiante debería estar inscrito en el Learning Path 1.");
        assertTrue(caminosInscritos.contains(learningPath2), "El estudiante debería estar inscrito en el Learning Path 2.");
    }

    @Test
    void testObtenerActividadesPendientes() {
        List<Actividad> actividadesPendientes = estudiante.getActividadesPendientes(learningPath1);
        assertEquals(2, actividadesPendientes.size(), "El Learning Path 1 debería tener 2 actividades pendientes.");
        assertTrue(actividadesPendientes.contains(actividad1), "La actividad 1 debería estar pendiente.");
        assertTrue(actividadesPendientes.contains(actividad2), "La actividad 2 debería estar pendiente.");
    }

    @Test
    void testObtenerProgresoLearningPath() {
        Progreso progreso = estudiante.getProgresoLearningPath(learningPath1);
        assertNotNull(progreso, "El progreso no debería ser nulo.");
        assertEquals(0.0, progreso.getPorcentaje(), "El progreso inicial debería ser 0%.");
    }

    @Test
    void testObtenerPorcentajeProgreso() {
        estudiante.entregarActividad(learningPath1, actividad1);
        estudiante.entregarActividad(learningPath1, actividad2);

        Double porcentaje = estudiante.getPorcentajeProgresoLearningPath(learningPath1);
        assertEquals(100.0, porcentaje * 100, "El porcentaje debería ser del 100% después de completar todas las actividades.");
    }

    @Test
    void testInteractuarActividad() {
        String resultadoInteraccion = estudiante.interactuarActividad(learningPath1, actividad1);
        assertEquals("Aquí tienes la forma de acceder al video: \nRecurso 1", resultadoInteraccion, "El resultado de interactuar debería coincidir.");
    }

    @Test
    void testEntregarActividad() {
        
        Progreso progresoInicial = estudiante.getProgresoLearningPath(learningPath1);
        assertEquals(0, progresoInicial.getActividadesTerminadas().size(), "No debería haber actividades terminadas inicialmente.");
        assertEquals(2, progresoInicial.getActividadesPendinetes().size(), "Debería haber 2 actividades pendientes inicialmente.");
        assertEquals(0.0, progresoInicial.getPorcentaje(), "El progreso inicial debería ser 0%.");
        estudiante.entregarActividad(learningPath1, actividad1);

        
        Progreso progresoActual = estudiante.getProgresoLearningPath(learningPath1);
        assertEquals(1, progresoActual.getActividadesTerminadas().size(), "Debería haber 1 actividad terminada.");
        assertTrue(progresoActual.getActividadesTerminadas().contains(actividad1), "La actividad 1 debería estar en terminadas.");

       
        assertEquals(1, progresoActual.getActividadesPendinetes().size(), "Debería haber 1 actividad pendiente.");
        assertFalse(progresoActual.getActividadesPendinetes().contains(actividad1), "La actividad 1 no debería estar en pendientes.");
        assertEquals(50.0, progresoActual.getPorcentaje() * 100, "El progreso debería ser del 50%.");
    }



    @Test
    void testEntregarActividadConActividadNoExistente() {
        
        Actividad actividadNoExistente = new RecursoEducativo("Descripción", "Objetivo", "RE", 1, 1.0, new ArrayList<>(), "Recurso", "PDF");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> estudiante.entregarActividad(learningPath1, actividadNoExistente));
        assertEquals("La actividad no pertenece al Learning Path proporcionado.", exception.getMessage());
    }


    @Test
    void testCasosExtremosLearningPathsVacios() {
        Estudiante estudianteNuevo = new Estudiante("Nuevo Estudiante", "nuevo@mail.com", "password123");
        assertTrue(estudianteNuevo.getCaminosInscritos().isEmpty(), "El estudiante debería no tener Learning Paths al inicio.");
    }

    @Test
    void testAgregarResenhaYRating() {
        learningPath1.ratePath(5.0);
        learningPath1.ratePath(4.0);
        assertEquals(4.5, learningPath1.getRating(), "El rating promedio debería ser 4.5.");
    }
}
