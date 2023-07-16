package juegoCartasSiete;

import java.util.List;
import java.util.ArrayList;

public class Jugador {
	private List<Carta> susCartas;
	
	public List<Carta> getSusCartas() {
		return susCartas;
	}

	public void setSusCartas(List<Carta> susCartas) {
		this.susCartas = susCartas;
	}
	
	//Constructor 1 de Jugador Aquí se crea al jugador y se relaciona las coartas que tendré
	public Jugador(List<Carta> susCartas) {
		this.susCartas = susCartas;
	}
	//Constructor 2 de Jugador
	public Jugador(){
		
	}
	
}
