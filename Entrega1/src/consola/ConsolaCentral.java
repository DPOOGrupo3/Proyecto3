package consola;


import modelo.CentralLogica;
import modelo.LearningPath;
import modelo.usuario.Usuario;
import modelo.usuario.Profesor;
import modelo.usuario.Estudiante;

import java.io.IOException;

import consola.ConsolaEstudiante;
import consola.ConsolaProfesor;

public class ConsolaCentral extends ConsolaBasica {
	
	private static ConsolaEstudiante cEstudiante;
	private static ConsolaProfesor cProfesor;
	private static CentralLogica centralLogica;
	
	public ConsolaCentral() {
        centralLogica = new CentralLogica();
        centralLogica.cargarDatos(); 
    }
	

	
	/**
	 * Metodo para iniciar sesión de un usuario corriente
	 * @throws IOException 
	 */
	private void iniciarSesion() throws IOException {
		
		String iUsername = this.pedirCadenaAlUSuario("Ingrese su usuario (username// correo sin el @)");
		String iPassword = this.pedirCadenaAlUSuario("Ingrese su contraseña");
		
		Usuario user = centralLogica.iniciarSesion(iUsername, iPassword);
		
	       if (user != null) {
	    	   
	            
	    	   System.out.println("Inicio de sesion exitoso, Bienvenido " + user.getNombre());
	    	   
	    	   
	    	   if (user instanceof Profesor) {
	    		   cProfesor = new ConsolaProfesor((Profesor)user, centralLogica);
	    		   cProfesor.correrConsola();
	    	   } else if (user instanceof Estudiante) {
	    		   cEstudiante = new ConsolaEstudiante((Estudiante)user, centralLogica);
	    		   cEstudiante.correrConsola();
	    	   }
	    		   
	       } else {
	    	   System.out.println("Error: No se encuentra el usuario o la contraseña es incorrecta.");

	       }
	       
	       correrAplicacion();
	       

	       

	}
	
    
    /**
	 * Metodo para crear un nuevo usuario corriente
	 * @throws IOException 
	 */
	private void crearNuevoUsuario() throws IOException {
		
		String nombre = this.pedirCadenaAlUSuario("Ingerese su nombre");
		String email = this.pedirCadenaAlUSuario("Ingerese su email");
		String password = this.pedirCadenaAlUSuario("Ingrese su contraseña");
		
		String[] opciones = {"Profesor", "Estudiante"};
		int opcion = this.mostrarMenu("Seleccione el tipo de usuario que desea crear: ", opciones);
		
		
		Usuario nuevoUsuario = null;
		
		if (opcion == 1) {
			nuevoUsuario = new Profesor(nombre, email, password);
			System.out.println("¡Usuario profesor creado exitosamente!");
			
		} else if(opcion == 2) {
			nuevoUsuario = new Estudiante(nombre, email, password);
			System.out.println("¡Usuario estudiante creado exitosamente!");
			
		} else {
			System.out.println("Opcion no valida");
			return;
		}
		
		centralLogica.registrarUsuario(nuevoUsuario);
		centralLogica.guardarDatos();
		System.out.println("Datos guardados exitosamente");
		correrAplicacion();
	}
	
	public void correrAplicacion() {
		
		
		String[] opcionesMenu = {"Iniciar sesión", "Crear nuevo usuario", "Salir"};
        int opcion = this.mostrarMenu("Bienvenido al sistema de Learning Path", opcionesMenu);

        try {
            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    crearNuevoUsuario();
                    break;
                case 3:
                    System.out.println("Saliendo de la aplicación...");
                    centralLogica.guardarDatos(); 
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    correrAplicacion();
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error al procesar la solicitud. Intente nuevamente.");
            correrAplicacion();
        }
		
		
	}
	
	
	public static void main(String[] args) 
	{
		ConsolaCentral ca = new ConsolaCentral();
		ca.correrAplicacion();
	}
	

	
}
