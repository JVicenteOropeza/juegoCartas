package juegoCartasSiete;

import java.util.List;
import java.util.ArrayList;

public class Carta {
	private String palo;
	private String valor;
	//SETs y GETS
	public String getPalo() {
		return palo;
	}
	
	public void setPalo(String palo) {
		this.palo = palo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	//se sobrescribe el método toString para traer todo la carta completa
	@Override
	public String toString() {
		return valor+" de "+palo;
	}
	//Constructor 1
	public Carta(String palo, String valor) {
		this.palo = palo;
		this.valor = valor;
	}
	
	//Construcor 2
	public Carta() {
		
	}
	
	public List<Carta> crearMazo(){
		List<Carta> mazo = new ArrayList<>();
		
		String[] palos = {"Picas","Tréboles","Corazones","Diamantes"};
		String[] valores = {"As","2","3","4","5","6","7","8","9","10","J","Q","K"};
		
		for(String palo:palos) {
			for(String valor: valores) {
				Carta carta = new Carta(palo, valor);
				mazo.add(carta);
			}
		}
		return mazo;
	}
}
