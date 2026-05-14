package practica6;
import java.util.Scanner;
public class Wordle {
	 public static void main(String[] args) {
		 
		 Scanner sc = new Scanner(System.in);
		 Partida partida=null;
		 while (true) {

	            System.out.println("Menú:");
	            System.out.println("1. Nuevo juego");
	            System.out.println("2. Cargar juego");
	            System.out.println("3:Ver puntuaciones");
	            System.out.println("4. Salir");
	            System.out.println();
	            System.out.print("Elige opción: ");

	            int opcion = sc.nextInt();
	            sc.nextLine();

	            switch (opcion) {

	                case 1->{
	                    partida = new Partida();
	                    partida.jugar();  
	                }
	                case 2 -> {
	                	partida = Partida.cargarPartida();
	                    if (partida != null) {
	                        System.out.println("Partida cargada.");
	                        partida.jugar();
	                    } else {
	                        System.out.println("No se pudo cargar la partida.");
	                    }
	                    
	                }
	                case 3 ->{
	                		Puntuacion.mostrar();
	                }
	                case 4 ->{
	                	 System.out.println("Adios");
	                	 return;
	                }
	                default ->{
	                	System.out.println("Opción no válida");
	                }
	            }
		 }
	                    
	 }
}
