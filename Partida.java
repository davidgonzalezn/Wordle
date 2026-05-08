package practica6;
//prueba
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Partida {

	private String ruta = "C:\\Palabras5L.txt";
	BufferedReader br = null;
	private String texto;
	private HashSet <String> palabras = new HashSet <String>();
	private ArrayList <String> sorteo = new ArrayList <String> ();
	private ArrayList <Character> letras = new ArrayList <Character> ();
	ArrayList <Character> usuarioList = new ArrayList <Character> ();
	private String [] arrayPalabras;
	private String nombreJugador;
	private String palabraSecreta;


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
		sorteo.addAll(palabras);
		Collections.shuffle(sorteo);
	}
	public void iniciarPartida() {

		palabraSecreta = sorteo.get(0);
		for (int i = 0; i < palabraSecreta.length(); i++) {
			letras.add(palabraSecreta.charAt(i));
		}

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
