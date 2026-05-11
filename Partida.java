package practica6;
//prueba
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Partida implements Serializable {

	private String ruta = "Palabras5L.txt";
	BufferedReader br = null;
	private String texto;
	private HashSet <String> palabras = new HashSet <String>();
	private ArrayList <String> sorteo = new ArrayList <String> ();
	private ArrayList <Character> letras = new ArrayList <Character> ();
	ArrayList <Character> usuarioList = new ArrayList <Character> ();
	private String [] arrayPalabras;
	private String nombreJugador;
	private String palabraSecreta;
	private int vidas=6;
	private int puntuacionFinal;
	


	//COLORES 
	final String RESET = "\u001B[0m";

	final String FONDO_VERDE = "\u001B[42m";
	final String FONDO_AMARILLO = "\u001B[43m";
	final String FONDO_NEGRO = "\u001B[40m";

	final String NEGRO = "\u001B[30m";
	final String BLANCO = "\u001B[37m";

	public Partida(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}
	

	//carga nuestro fichero de palabras
	public void cargarPalabras () { 
		try {
			br = new BufferedReader(new FileReader(ruta));

			this.texto = br.readLine();
			arrayPalabras = texto.split(", ");

			for (int i = 0; i < arrayPalabras.length; i++) {
				palabras.add(arrayPalabras[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void hacerSorteo () {
		sorteo.clear();//esto para que cada vez que hagamos el sorteo en el addAll añada las palabras de nuevo
		sorteo.addAll(palabras);
		Collections.shuffle(sorteo);
	}
	//carga una palabra y reinicia las vidas
	public void iniciarPartida() {
		hacerSorteo();
		palabraSecreta = sorteo.get(0);
		letras.clear();//por si acaso hay algo de la anterior
		for (int i = 0; i < palabraSecreta.length(); i++) {
			letras.add(palabraSecreta.charAt(i));
		}
		vidas=6;

	}

	//Transformar palabra usuario a lista <Character> 

	public ArrayList <Character> getUsuarioList (String palabraUsuario) {

		for (int i = 0; i < palabraUsuario.length(); i++) {
			usuarioList.add(palabraUsuario.charAt(i));
		}
		return usuarioList;
	}

	//Compara las posiciones + equals
	public void comparadorLetras () {
		
		for (int i = 0; i < palabraSecreta.length(); i++) {
			if (usuarioList.get(i).equals(letras.get(i))) {
				System.out.print(FONDO_VERDE + NEGRO + usuarioList.get(i) + RESET);
				
			} else {
				boolean encontrada = false;
				for (int j = 0; j < palabraSecreta.length(); j++) {
					if (usuarioList.get(i).equals(letras.get(j))) {
						encontrada = true;
						break;
					}
				}
				if (encontrada) {
					System.out.print(FONDO_AMARILLO + NEGRO + usuarioList.get(i) + RESET);
				} else 
					System.out.print(FONDO_NEGRO + BLANCO + usuarioList.get(i) + RESET);
			}
		}
	}
	//metodo que valida el intento, resta vida o carga nueva palabra
	public boolean intento(String palabraSecreta) {
		 palabraSecreta = palabraSecreta.toLowerCase();
	        getUsuarioList(palabraSecreta);
	        comparadorLetras();

	        if (palabraSecreta.equals(palabraSecreta)) {
	        		puntuacionFinal += vidas*100;
	            System.out.println("Adivinada, nueva palabra:");
	 
	            iniciarPartida();
	            return true;
	        } else {
	            vidas--;
	            System.out.println("Vidas restantes: " + vidas);
	            return false;
	        }
	   }
	//metdodo principal para jugar
	public void jugar() {

        Scanner sc = new Scanner(System.in);
        	cargarPalabras();
        	iniciarPartida();
        while (vidas>0) {

            System.out.print("Introduce la palabra ");
            String intento = sc.nextLine().toUpperCase();
            //aqui habria que poner por si quiere guardar la partida
            if (intento.length() != 5) {
                System.out.println("La palabra debe tener 5 letras.");
                continue;
            }

            intento(intento);
        }

        System.out.println("Has perdido, puntuación final: "+puntuacionFinal);
        System.out.println("Quieres guardar la puntuacion? 1. SI 2.NO");
        int guardar=sc.nextInt();
        if(guardar==1) {
        		System.out.println("Introduce tu nombre: ");
        		String nombre =sc.next();
        		Puntuacion.registrar(nombre, puntuacionFinal);
        		
        }  
    }
	//guardar partida
	public void guardarPartida() {
		ObjectOutputStream oos =null;
		try {
			 oos = new ObjectOutputStream(new FileOutputStream("partidaGuardada.dat"));
	         oos.writeObject(this);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(oos!=null) {
				try {
					oos.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//cargar partida

	//Compara las listas 
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Partida otra = (Partida) obj;

		return letras.equals(otra.letras) && usuarioList.equals(otra.usuarioList);
	}

}
