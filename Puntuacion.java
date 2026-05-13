package practica6;

import java.util.*;
import java.io.*;
public class Puntuacion {

	private static final String FICHERO = "src/practica6/puntuaciones.csv";

	public static HashMap<String, Integer> leerPuntuacion() {

		HashMap<String, Integer> puntuacion = new HashMap<>();
		BufferedReader br = null;
		try {
			br=new BufferedReader(new FileReader(FICHERO));
			String linea;
			while ((linea = br.readLine()) != null) {

				String[] partes = linea.split(",");

				if (partes.length == 2) {
					String nombre = partes[0];
					int puntos = Integer.parseInt(partes[1]);
					puntuacion.put(nombre, puntos);
				}
			}

		} catch (IOException e) {

		}finally {
			if(br!=null) {
				try {
					br.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return puntuacion;
	}



	public static void guardar(HashMap<String, Integer> puntuaciones ) {
		BufferedWriter bw = null;
		try {
			bw=new BufferedWriter(new FileWriter(FICHERO,true));
			for (String nombre : puntuaciones.keySet()) {
				bw.write(nombre + "," + puntuaciones.get(nombre));
				bw.newLine();
			}

		} catch (IOException e) {
			System.out.println("Error al guardar puntuaciones.");
		}finally {
			if(bw!=null) {
				try {
					bw.close();
				}catch(IOException e){
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
		BufferedReader br = null;
		try {
			br=new BufferedReader(new FileReader(FICHERO));
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}

		}catch (IOException e) {

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

}
