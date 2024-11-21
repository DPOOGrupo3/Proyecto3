package consola;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;

public abstract class ConsolaBasica {
	
	/**
     * Pide al usuario que ingrese una cadena de caracteres.
     * @param mensaje El mensaje con el que se solicita la información.
     * @return La cadena introducida por el usuario.
     */
	
	protected String pedirCadenaAlUSuario (String mensaje) {
		
		try 
		{
			System.out.println(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader( System.in ));
			String input = reader.readLine();
	
			return input;
		}
		catch(IOException e)
		{
			System.out.println("ERROR");
		}
		return "Error";
	}
	
	/**
     * Pide confirmación al usuario, indicándole que debe responder 'si' o 'no'.
     * @param mensaje El mensaje con el que se solicita la información.
     * @return Retorna true si el usuario responde 'sí', 'si' o 'SI', independientemente de las minúsculas y las mayúsculas. Retorna false en cualquier otro caso.
     */
	protected boolean pedirConfirmacionAlUsuario(String mensaje) {
		
		try {
			System.out.println(mensaje + "(Si / no)");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine().toLowerCase();
			boolean respuesta = false;
			if (input.equals("si") || input.equals( "sí" ));
			return respuesta;
		}
		
		catch (IOException e) {
			System.out.println("ERROR");
		}
		
		return false;

	}
	/**
     * Pide al usuario que ingrese un número entero. Vuelve a pedir la entrada si el usuario introduce un valor no válido.
     * @param mensaje El mensaje con el que se solicita la información.
     * @return El valor entero introducido por el usuario.
     */
    protected int pedirEnteroAlUsuario(String mensaje) {
        int valorResultado = Integer.MIN_VALUE;
        while( valorResultado == Integer.MIN_VALUE )
        {
            try
            {
                System.out.print( mensaje + ": " );
                BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
                String input = reader.readLine( );
                int numero = Integer.parseInt( input );
                valorResultado = numero;
            }
            catch( NumberFormatException nfe )
            {
                System.out.println( "El valor digitado no es un entero" );
            }
            catch( IOException e )
            {
                System.out.println( "Error leyendo de la consola" );
            }
        }
        return valorResultado;
    }
    
    
    /**
     * Pide al usuario que ingrese un número con decimales. Vuelve a pedir la entrada si el usuario introduce un valor no válido.
     * @param mensaje El mensaje con el que se solicita la información.
     * @return El valor decimal (double) introducido por el usuario.
     */
    protected double pedirNumeroAlUsuario(String mensaje) {
    	 double valorResultado = Integer.MIN_VALUE;
         while( valorResultado == Integer.MIN_VALUE )
         {
             try
             {
                 System.out.print( mensaje + ": " );
                 BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
                 String input = reader.readLine( );
                 double numero = Double.parseDouble( input );
                 valorResultado = numero;
             }
             catch( NumberFormatException nfe )
             {
                 System.out.println( "El valor digitado no es un entero" );
             }
             catch( IOException e )
             {
                 System.out.println( "Error leyendo de la consola" );
             }
         }
         return valorResultado;
    }
    
    /**
     * Muestra una lista de opciones y permite al usuario seleccionar una de ellas.
     * @param coleccionOpciones La colección de opciones a mostrar.
     * @return La opción seleccionada por el usuario.
     */
    protected String pedirOpcionAlUsuario( Collection<? extends Object> coleccionOpciones )
    {
        String[] opciones = new String[coleccionOpciones.size( )];
        int pos = 0;
        for( Iterator<? extends Object> iterator = coleccionOpciones.iterator( ); iterator.hasNext( ); pos++ )
        {
            opciones[ pos ] = iterator.next( ).toString( );
        }

        System.out.println( "Seleccione una de las siguientes opciones:" );
        for( int i = 1; i <= opciones.length; i++ )
        {
            System.out.println( " " + i + ". " + opciones[ i - 1 ] );
        }

        String opcion = pedirCadenaAlUSuario( "\nEscriba el número que corresponde a la opción deseada" );
        try
        {
            int opcionSeleccionada = Integer.parseInt( opcion );
            if( opcionSeleccionada > 0 && opcionSeleccionada <= opciones.length )
                return opciones[ opcionSeleccionada - 1 ];
            else
            {
                System.out.println( "Esa no es una opción válida. Digite solamente números entre 1 y " + opciones.length );
                return pedirOpcionAlUsuario( coleccionOpciones );
            }
        }
        catch( NumberFormatException nfe )
        {
            System.out.println( "Esa no es una opción válida. Digite solamente números." );
            return pedirOpcionAlUsuario( coleccionOpciones );
        }
    }
    
    
    /**
     * Muestra un menú con opciones numeradas y permite al usuario seleccionar una de ellas.
     * @param nombreMenu El nombre del menú.
     * @param opciones Las opciones que se le presentan al usuario.
     * @return El número de la opción seleccionada por el usuario, contando desde 1.
     */
    protected int mostrarMenu( String nombreMenu, String[] opciones )
    {
        System.out.println( "\n---------------------" );
        System.out.println( nombreMenu );
        System.out.println( "---------------------" );

        for( int i = 1; i <= opciones.length; i++ )
        {
            System.out.println( " " + i + ". " + opciones[ i - 1 ] );
        }
        String opcion = pedirCadenaAlUSuario( "Escoja la opción deseada" );
        try
        {
            int opcionSeleccionada = Integer.parseInt( opcion );
            if( opcionSeleccionada > 0 && opcionSeleccionada <= opciones.length )
                return opcionSeleccionada;
            else
            {
                System.out.println( "Esa no es una opción válida. Digite solamente números entre 1 y " + opciones.length );
                return mostrarMenu( nombreMenu, opciones );
            }
        }
        catch( NumberFormatException nfe )
        {
            System.out.println( "Esa no es una opción válida. Digite solamente números." );
            return mostrarMenu( nombreMenu, opciones );
        }
    }
    
    
    /**
     * Pide al usuario que ingrese un solo carácter.
     * @param mensaje El mensaje con el que se solicita la información.
     * @return El carácter introducido por el usuario.
     */
    protected char pedirCaracterAlUsuario(String mensaje) {
        try {
            System.out.print(mensaje + ": ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            if (input.length() == 1) {
                return input.charAt(0);
            } else {
                System.out.println("Debe introducir solo un carácter.");
                return pedirCaracterAlUsuario(mensaje);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo de la consola.");
        }
        return '\0'; 
    }
    
    /**
     * Muestra un mensaje en la consola sin esperar una entrada del usuario.
     * @param mensaje El mensaje a mostrar.
     */
    protected void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    /**
     * Pausa la ejecución hasta que el usuario presione Enter.
     */
    protected void esperarEnterParaContinuar() {
        System.out.println("Presione Enter para continuar...");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();
        } catch (IOException e) {
            System.out.println("Error leyendo de la consola.");
        }
    }

    /**
     * Pide al usuario que ingrese un número entero dentro de un rango específico.
     * @param mensaje El mensaje con el que se solicita la información.
     * @param min El valor mínimo aceptable.
     * @param max El valor máximo aceptable.
     * @return El valor entero introducido por el usuario dentro del rango.
     */
    protected int pedirNumeroEnRangoAlUsuario(String mensaje, int min, int max) {
        int valorResultado = Integer.MIN_VALUE;
        while (valorResultado == Integer.MIN_VALUE) {
            try {
                System.out.print(mensaje + " (" + min + " - " + max + "): ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input = reader.readLine();
                int numero = Integer.parseInt(input);
                if (numero >= min && numero <= max) {
                    valorResultado = numero;
                } else {
                    System.out.println("Por favor, ingrese un número entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("El valor digitado no es un entero.");
            } catch (IOException e) {
                System.out.println("Error leyendo de la consola.");
            }
        }
        return valorResultado;
    }  


}
