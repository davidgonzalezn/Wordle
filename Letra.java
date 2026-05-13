package practica6;

import java.io.Serializable;

public class Letra implements Serializable{
	enum ESTADO { 
		VERDE, AMARILLO, NEGRO
	};
	
	char letra;
	ESTADO estado = ESTADO.NEGRO;
	
	public Letra (char letra) {
		this.letra = letra;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	public ESTADO getEstado() {
		return estado;
	}

	public void setEstado(ESTADO estado) {
		this.estado = estado;
	}
}
