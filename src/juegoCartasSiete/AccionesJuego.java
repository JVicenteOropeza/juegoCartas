package juegoCartasSiete;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class AccionesJuego {
	
	//Constructor
	public AccionesJuego() {
		
	}
	
	//BarajearCartas
	public List<Carta> barajearMazo(List<Carta> cartasMazo){
		//método para revolver
		Collections.shuffle(cartasMazo);
		return cartasMazo;
	}
	//RepartirCartas regresa una lista de Carta de 2 componentes la primera ves
	public List<Carta> RepartirCartas(List<Carta> sortMazo, int numeroCartas){
		//Da y borra de sortMazo
		List<Carta> susCartas = new ArrayList<>();
		if(sortMazo != null) {
			for(int i =1; i <= numeroCartas; i++) {
				susCartas.add(sortMazo.get(i));
				sortMazo.remove(i);
			}
		}else {
			System.out.println("SIN CARTAS");
		}
		return susCartas;
	}
	//SumarValorCartas
	public double sumarPuntosCartas(Jugador jugador) {
		double suma = 0;
		List<Carta> susCartasTemp = jugador.getSusCartas();
		for(Carta carta:susCartasTemp) {
			String valor = carta.getValor();
			
			switch(valor) {
			case "As":
				suma = suma + 1;
				break;
			case "2":
				suma = suma + 2;
				break;
			case "3":
				suma = suma + 3;
				break;
			case "4":
				suma = suma + 4;
				break;
			case "5":
				suma = suma + 5;
				break;
			case "6":
				suma = suma + 6;
				break;
			case "7":
				suma = suma + 7;
				break;
			case "8":
				suma = suma + 8;
				break;
			case "9":
				suma = suma + 9;
				break;
			case "10":
			case "J":
			case "Q":
			case "K":
				suma = suma + 0.5;
				break;
			default:
				System.out.println("VALOR DESCONOCIDDO");
			}
		}
		return suma;
	}
	//Recibes al jugador ya con su cartas
	public String paraJLabel(Jugador jugador){
		StringBuilder verCartasBuilder = new StringBuilder("<html>");
		for(Carta carta:jugador.getSusCartas()) {
			System.out.println("J1: "+carta);
			verCartasBuilder.append(carta.toString()).append("<br>");
		}
		verCartasBuilder.append(sumarPuntosCartas(jugador)).append("<br>");
		verCartasBuilder.append("</html>");
		String verCartas = verCartasBuilder.toString();
		
		return verCartas;
	}
	
	public String paraJTextField(String elTextoJT) {
		StringBuilder verCartasBuilder = new StringBuilder("<html>");
		return elTextoJT;
		
	}
	
	public List<Integer> resultadoValor(double[] quienSera, double valor) {
		List<Integer> indices = new ArrayList<>();
		
		for(int i = 0; i < quienSera.length; i++) {
			if(quienSera[i] == valor) {
				indices.add(i + 1);
			}
		}
		return indices;
	}
	
}
