package practica6;

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
    private String [] arrayPalabras;
    private String nombreJugador;

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
    	
    	String palabraSecreta = sorteo.get(0);
    	for (int i = 0; i < palabraSecreta.length(); i++) {
    		letras.add(palabraSecreta.charAt(i));
    	}
    	
    }
    
    
    @Override
    public boolean equals(String palabraUsuario) {
    	
    	if ()
    }
}