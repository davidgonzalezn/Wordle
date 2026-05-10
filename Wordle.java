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
	            System.out.println("3. Salir");
	            System.out.print("Elige opción: ");

	            int opcion = sc.nextInt();
	            sc.nextLine();

	            switch (opcion) {

	                case 1->{
	                    System.out.print("Introduce tu nombre: ");
	                    String nombre = sc.nextLine();
	                    partida = new Partida(nombre);
	                    partida.jugar();  
	                }
	                case 2 -> {
	                	//lo de cargar partida guardada
	                }
	                case 3 ->{
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
