package practica6;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

import practica6.Letra.ESTADO;

public class Partida implements Serializable {

	private final String RUTA = "src/practica6/Palabras5.txt";
	private HashSet <String> palabras = new HashSet <String>();
	private ArrayList <String> sorteo = new ArrayList <String> ();
	//private ArrayList <Character> letras = new ArrayList <Character> ();
	//ArrayList <Character> usuarioList = new ArrayList <Character> ();
	private String [] arrayPalabras;
	private String palabraSecreta;
	private int vidas=6;
	private int puntuacionFinal;
	
	private ArrayList <Letra> secreta = new ArrayList <> ();
	private ArrayList <Letra> palabraUsuario = new ArrayList <> ();
			
			
	//COLORES 
	final String RESET = "\u001B[0m";

	final String FONDO_VERDE = "\u001B[42m";
	final String FONDO_AMARILLO = "\u001B[43m";
	final String FONDO_NEGRO = "\u001B[40m";

	final String NEGRO = "\u001B[30m";
	final String BLANCO = "\u001B[37m";




	//carga nuestro fichero de palabras
	public void cargarPalabras () { 
		BufferedReader br = null;
		String texto;
		try {
			br = new BufferedReader(new FileReader(RUTA));
			texto = br.readLine();
			arrayPalabras = texto.split(", ");

			for (int i = 0; i < arrayPalabras.length; i++) {
				palabras.add(arrayPalabras[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(br!=null) {
				try {
					br.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
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
		secreta.clear();//por si acaso hay algo de la anterior
		for (int i = 0; i < palabraSecreta.length(); i++) {
			char c = palabraSecreta.charAt(i);
			secreta.add(new Letra (c));
		}
		vidas=6;

	}

	//Transformar palabra usuario a lista <Character> 

	public ArrayList <Letra> getPalabraUsuario (String userWord) {
		for (int i = 0; i < userWord.length(); i++) {
			char c = userWord.charAt(i);
			palabraUsuario.add(new Letra(c));
		}
		return palabraUsuario;
	}
	
	public void comprobarVerdes() {
		for (int i = 0; i<palabraSecreta.length(); i++) {
			if (palabraUsuario.get(i).getLetra() == secreta.get(i).getLetra()) {
				palabraUsuario.get(i).estado = ESTADO.VERDE;
				secreta.get(i).estado = ESTADO.VERDE;
			}
		}
	}
	
	public void comprobarAmarrillas () {
		for (int i = 0; i < palabraSecreta.length(); i++) {
			Letra letraUser = palabraUsuario.get(i);
			if (letraUser.getEstado() != ESTADO.VERDE) {
				for(int j = 0; j < palabraSecreta.length(); j++) {
					Letra letraSecreta = secreta.get(j);
					if (letraSecreta.getEstado() == ESTADO.NEGRO) {
						if(letraUser.getLetra() == letraSecreta.getLetra()) {
							letraUser.estado = ESTADO.AMARILLO;
							letraSecreta.estado = ESTADO.AMARILLO;
							break;
						}
					}
				}
			}
		}
	}
	
	public void imprimirPalabraUsuario () {
		for(int i = 0;i < palabraSecreta.length(); i++ ) {
			Letra letraUser = palabraUsuario.get(i);
			switch (letraUser.estado) {
				case VERDE :
					System.out.print(FONDO_VERDE + NEGRO + palabraUsuario.get(i).getLetra() + RESET);
					break;
				
				case AMARILLO:
					System.out.print(FONDO_AMARILLO + NEGRO + palabraUsuario.get(i).getLetra() + RESET);
					break;
					
				case NEGRO:
					System.out.print(FONDO_NEGRO + BLANCO + palabraUsuario.get(i).getLetra() + RESET);
					break;
			}
		}
	}
	
	
	public void compararLetras () {
		comprobarVerdes();
		comprobarAmarrillas();
		imprimirPalabraUsuario();
	}
	
	//metodo que valida el intento, resta vida o carga nueva palabra
	public boolean intento(String intento) {
		intento = intento.toLowerCase();
		palabraUsuario.clear();
		getPalabraUsuario(intento);
		compararLetras();

		if (palabraSecreta.equals(intento)) {
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
			if(palabraSecreta==null) {
	        cargarPalabras();
	        iniciarPartida();
			}
		while (vidas>0) {

			System.out.print("Introduce la palabra o ('guardar' para salir) ");
			String intento = sc.nextLine().toLowerCase();
			//aqui habria que poner por si quiere guardar la partida
			if (intento.equals("guardar")) {
                guardarPartida();
                System.out.println("Partida guardada. Volviendo al menú.");
                return;
            }
			if (intento.length() != 5) {
				System.out.println("La palabra debe tener 5 letras.");
				continue;
			}

			intento(intento);
		}

		System.out.println("Has perdido, puntuación final: "+puntuacionFinal);
		System.out.println("La palabra era: " + palabraSecreta);
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
			oos = new ObjectOutputStream(new FileOutputStream("src\\practica6\\partidaGuardada.dat"));
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
	//cargarPArtida
	public static Partida cargarPartida() {
		ObjectInputStream ois =null;
		try {
			ois= new ObjectInputStream(new FileInputStream("src\\practica6\\partidaGuardada.dat"));
			return (Partida)ois.readObject();
			
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(ois!=null) {
				try {
					ois.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

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

		return secreta.equals(otra.secreta) && palabraUsuario.equals(otra.palabraUsuario);
	}

}
