package practica6;

import java.util.*;
import java.io.*;
public class Puntuacion {

	private static final String FICHERO = "puntuaciones.csv";
	
	public static HashMap<String, Integer> leerPuntuacion() {

        HashMap<String, Integer> puntuacion = new HashMap<>();
        ObjectInputStream ois =null;
        try {
        		ois= new ObjectInputStream(new FileInputStream(FICHERO));
        		puntuacion = (HashMap<String, Integer>) ois.readObject();
        		
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
        return puntuacion;
	}
	public static void guardar(HashMap<String, Integer> puntuaciones ) {
		ObjectOutputStream oos =null;
	    try {
	    		oos= new ObjectOutputStream(new FileOutputStream(FICHERO));
	        oos.writeObject(puntuaciones);

	    } catch (IOException e) {
	        System.out.println("Error al guardar puntuaciones.");
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
	 public static void registrar(String nombre, int puntuacion) {

	        HashMap<String, Integer> puntuaciones = leerPuntuacion();

	        if (!puntuaciones.containsKey(nombre) || puntuacion > puntuaciones.get(nombre)) {
	            puntuaciones.put(nombre, puntuacion);
	            guardar(puntuaciones);
	            System.out.println("Puntuación registrada.");
	        } else {
	            System.out.println("Ya tenías una puntuación mayor registrada.");
	        }
	    }
	 public static void mostrar() {

	        HashMap<String, Integer> puntuaciones = leerPuntuacion();

	        if (puntuaciones.isEmpty()) {
	            System.out.println("No hay puntuaciones registradas.");
	            return;
	        }
	        System.out.println("Puntuaciones:");
	        System.out.println(puntuaciones);
	    }
}
