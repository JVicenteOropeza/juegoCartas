package juegoCartasSiete;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import juegoCartasSiete.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import juegoCartasSiete.*;
import java.lang.NumberFormatException;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class MainJuego extends JFrame implements ActionListener {
	
	private FondoCarta PanelMain;
	private JTextField textCantidadJ, textoMensaje;
	private JButton btnInicioJuego, btnQuien, btnRepartir;
	private JLabel lblCartasJ1, lblCartasJ2, lblCartasJ3, lblCartasJ4, lblMoneda1,
	lblMoneda2, lblMoneda3, lblMoneda4;
	
	private ImageIcon moneda;
	private Image escalaMoneda;
	private ImageIcon escalaAplicada;
	private int numeroDeJugadores;
	private int numJugadores; 
	private List<Carta> mazo = new ArrayList<>();
	//Objetos de las clases
	Carta objeto = new Carta();
	Jugador jugador1, jugador2, jugador3, jugador4;
	AccionesJuego acciones = new AccionesJuego();
	
	//Se lanza la palicación
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJuego frame = new MainJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Constructor de la clase MainJuego
	public MainJuego() {
		setResizable(false);
		setBackground(new Color(0, 128, 128));
		setIconImage(Toolkit.getDefaultToolkit().getImage("ImageSiete/palos.png"));
		setTitle("Siete de la Suerte JVOS");
		setForeground(new Color(0, 64, 128));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 341);
	
		PanelMain = new FondoCarta("ImageSiete/mesa.jpg");
		PanelMain.setBackground(new Color(128, 0, 0));
		PanelMain.setLayout(null);
		PanelMain.setBounds(0, 0, getWidth(), getHeight());
		PanelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(PanelMain);
		iniciarComponentes();
	}
	//Se inician los componentes de la aplicación
	private void iniciarComponentes() {
		JLabel lblCantidadJ = new JLabel("Numero de Jugadores: ");
		lblCantidadJ.setForeground(new Color(255, 255, 255));
		lblCantidadJ.setBounds(21, 11, 136, 24);
		PanelMain.add(lblCantidadJ);
		
		//imagen con moneda de casino
		moneda = new ImageIcon("ImageSiete/monedaCasino.png");
		escalaMoneda = moneda.getImage().getScaledInstance(87, 73, Image.SCALE_SMOOTH);
		escalaAplicada = new ImageIcon(escalaMoneda);
		
		textCantidadJ = new JTextField();
		textCantidadJ.setHorizontalAlignment(SwingConstants.CENTER);
		textCantidadJ.setBounds(167, 13, 45, 20);
		textCantidadJ.setText("0");
		textCantidadJ.setEditable(false);
		PanelMain.add(textCantidadJ);
		textCantidadJ.setColumns(10);
		
		//Botón de inicio del juego
		btnInicioJuego = new JButton("Iniciar Juego");
		btnInicioJuego.setBounds(335, 12, 136, 23);
		btnInicioJuego.addActionListener(this);
		PanelMain.add(btnInicioJuego);
		
		//Botón de para saber quien gana
		btnQuien = new JButton("¿Quién Gana?");
		btnQuien.addActionListener(this);
		btnQuien.setBounds(21, 80, 136, 23);
		btnQuien.setEnabled(false);
		PanelMain.add(btnQuien);
		
		//Visual del jugador UNO
		JLabel lblJugadorUno = new JLabel("Jugador UNO");
		lblJugadorUno.setForeground(new Color(255, 255, 255));
		lblJugadorUno.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblJugadorUno.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugadorUno.setBounds(10, 154, 109, 24);
		PanelMain.add(lblJugadorUno);
		
		//Visual del jugador DOS
		JLabel lblJugadorDos = new JLabel("Jugador DOS");
		lblJugadorDos.setForeground(new Color(255, 255, 255));
		lblJugadorDos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblJugadorDos.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugadorDos.setBounds(128, 154, 107, 24);
		PanelMain.add(lblJugadorDos);
		
		//Visual del jugador TRES
		JLabel lblJugadorTres = new JLabel("Jugador TRES");
		lblJugadorTres.setForeground(new Color(255, 255, 255));
		lblJugadorTres.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblJugadorTres.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugadorTres.setBounds(245, 154, 108, 24);
		PanelMain.add(lblJugadorTres);
		
		//Visual del jugador CUATRO
		JLabel lblJugadorCuatro = new JLabel("Jugador CUATRO");
		lblJugadorCuatro.setForeground(new Color(255, 255, 255));
		lblJugadorCuatro.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugadorCuatro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblJugadorCuatro.setBounds(363, 154, 108, 24);
		PanelMain.add(lblJugadorCuatro);
		
		//Visual del título de los MENSAJES del juego
		JLabel lblGanador = new JLabel("MENSAJES");
		lblGanador.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGanador.setForeground(new Color(255, 255, 255));
		lblGanador.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGanador.setBounds(363, 46, 108, 19);
		PanelMain.add(lblGanador);
		
		//Visual de los MENSAJES 
		textoMensaje = new JTextField();
		textoMensaje.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		textoMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		textoMensaje.setPreferredSize(new Dimension(200, textoMensaje.getPreferredSize().height));
		textoMensaje.setText("DA CLICK EN: Iniciar Juego");
		textoMensaje.setEditable(false);
		textoMensaje.setBounds(184, 64, 287, 54);
		PanelMain.add(textoMensaje);
		textoMensaje.setColumns(50);
		Border border = BorderFactory.createLineBorder(Color.YELLOW);
		textoMensaje.setBorder(border);
		
		//Boton para REPARTIR CARTAS
		btnRepartir = new JButton("Repartir Cartas");
		btnRepartir.addActionListener(this);
		btnRepartir.setBounds(21, 46, 136, 23);
		btnRepartir.setEnabled(false);
		PanelMain.add(btnRepartir);
		
		//Visual del las CARTAS del jugador uno
		lblCartasJ1 = new JLabel("CartasJ1");
		lblCartasJ1.setVerticalAlignment(SwingConstants.TOP);
		lblCartasJ1.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblCartasJ1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartasJ1.setBounds(10, 179, 109, 33);
		lblCartasJ1.setOpaque(true);
		lblCartasJ1.setEnabled(false);
		lblCartasJ1.setBackground(Color.LIGHT_GRAY);
		PanelMain.add(lblCartasJ1);
		
		//Visual del las CARTAS del jugador dos
		lblCartasJ2 = new JLabel("CartasJ2");
		lblCartasJ2.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblCartasJ2.setVerticalAlignment(SwingConstants.TOP);
		lblCartasJ2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartasJ2.setBounds(127, 180, 108, 33);
		lblCartasJ2.setOpaque(true);
		lblCartasJ2.setEnabled(false);
		lblCartasJ2.setBackground(Color.LIGHT_GRAY);
		PanelMain.add(lblCartasJ2);
		
		//Visual del las CARTAS del jugador tres
		lblCartasJ3 = new JLabel("CartasJ3");
		lblCartasJ3.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblCartasJ3.setVerticalAlignment(SwingConstants.TOP);
		lblCartasJ3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartasJ3.setBounds(245, 180, 108, 33);
		lblCartasJ3.setOpaque(true);
		lblCartasJ3.setEnabled(false);
		lblCartasJ3.setBackground(Color.LIGHT_GRAY);
		PanelMain.add(lblCartasJ3);
		
		//Visual del las CARTAS del jugador cuatro
		lblCartasJ4 = new JLabel("CartasJ4");
		lblCartasJ4.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblCartasJ4.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartasJ4.setVerticalAlignment(SwingConstants.TOP);
		lblCartasJ4.setBounds(363, 180, 108, 33);
		lblCartasJ4.setOpaque(true);
		lblCartasJ4.setEnabled(false);
		lblCartasJ4.setBackground(Color.LIGHT_GRAY);
		PanelMain.add(lblCartasJ4);
		
		//Visual de la MONEDA CASINO que sale cuando el jugador UNO esta activo
		lblMoneda1 = new JLabel(escalaAplicada);
		lblMoneda1.setBounds(20, 218, 87, 73);
		lblMoneda1.setVisible(false);
		PanelMain.add(lblMoneda1);
		
		//Visual de la MONEDA CASINO que sale cuando el jugador DOS esta activo
		lblMoneda2 = new JLabel(escalaAplicada);
		lblMoneda2.setBounds(137, 218, 87, 73);
		lblMoneda2.setVisible(false);
		PanelMain.add(lblMoneda2);
		
		//Visual de la MONEDA CASINO que sale cuando el jugador TRES esta activo
		lblMoneda3 = new JLabel(escalaAplicada);
		lblMoneda3.setBounds(255, 218, 87, 73);
		lblMoneda3.setVisible(false);
		PanelMain.add(lblMoneda3);
		
		//Visual de la MONEDA CASINO que sale cuando el jugador CUATRO esta activo
		lblMoneda4 = new JLabel(escalaAplicada);
		lblMoneda4.setBounds(373, 218, 87, 73);
		lblMoneda4.setVisible(false);
		PanelMain.add(lblMoneda4);
	}
	
	//Se cachan los eventos para la aplicación
	@Override
	public void actionPerformed(ActionEvent e) {
		//Se obtiene el evento del momento
		String textoBoton = ((JButton) e.getSource()).getText();
        System.out.println("Funcion "+textoBoton);
        
        //Iniciar juago empieza con establecer el numero de jugadores
        if(textoBoton == "Iniciar Juego" || textoBoton == "¿Jugar otra vez?") {
    
        	textCantidadJ.setEditable(true);
        	btnRepartir.setEnabled(true);
        	textoMensaje.setText("");
        	textCantidadJ.setText("");
        	
        	//Se ocultan las monedaCasino
        	lblMoneda1.setVisible(false);
        	lblMoneda2.setVisible(false);
        	lblMoneda3.setVisible(false);
        	lblMoneda4.setVisible(false);
        	
        	//Limpia la secciónde cartas
        	lblCartasJ1.setText("");
        	lblCartasJ2.setText("");
        	lblCartasJ3.setText("");
        	lblCartasJ4.setText("");
        	lblCartasJ1.setBackground(Color.LIGHT_GRAY);
        	lblCartasJ2.setBackground(Color.LIGHT_GRAY);
        	lblCartasJ3.setBackground(Color.LIGHT_GRAY);
        	lblCartasJ4.setBackground(Color.LIGHT_GRAY);
        	
        	//Se crea el mazo de cartas en orden
        	mazo = objeto.crearMazo();
        	//Se ve el mazo en consola en ORDEN
        	for(Carta carta:mazo) {
        		System.out.println(carta);
        	}
        	
        	//BARAJEA el Mazo para que queden en desorden
        	mazo = acciones.barajearMazo(mazo);
        	//Se ve el mazo en consola en DESORDEN
        	for(Carta carta:mazo) {
        		System.out.println(carta);
        	}
        	//Después de la acción de INICIO
        	textoMensaje.setText("Ingresa numero de jugadores (MAX 4)");
        	lblCartasJ1.setEnabled(false);
        	lblCartasJ2.setEnabled(false);
        	lblCartasJ3.setEnabled(false);
        	lblCartasJ4.setEnabled(false);
        }
        
        //Boton repartir
        if(textoBoton == "Repartir Cartas") {
        	textCantidadJ.setEditable(false);
        	btnInicioJuego.setText("Iniciar Juego");
        	btnInicioJuego.setEnabled(false);
        	btnQuien.setEnabled(true);
        	
        	//Se cacha una posible excepción
        	try {
        		numJugadores = Integer.parseInt(textCantidadJ.getText());
        	}catch(NumberFormatException ex) {
        		textCantidadJ.setText("INGRE UN NUMERO DE JUGADOR. (Iniciar Juego)");
        	}
        	//Se limpia el campo de cantidad de jugadores el numero de jugadores yas e guardo
    		textCantidadJ.setText("");
    		
    		//Se valida el NUMERO DE JUGADORES
        	if(numJugadores <= 0 || numJugadores >= 5) {
        		textCantidadJ.setText("0");
        		textoMensaje.setText("SOLO PUEDEN JUGAR 4 A LA VEZ.(Iniciar Juego)");
        		btnQuien.setEnabled(false);
        		btnInicioJuego.setEnabled(true);
        	}else {
	        	//Numeros de jugadores y acción por cantidad de jugadores SWITCH
	        	switch(numJugadores){
	        		case 1:
	        			numeroDeJugadores = 1;
	        			lblCartasJ1.setEnabled(true);
	        			lblCartasJ1.setBackground(Color.WHITE);
	        			List<Carta> susCartas11 = new ArrayList<>();
	        			susCartas11 = acciones.RepartirCartas(mazo, 2);
	        			lblMoneda1.setVisible(true);
	        			jugador1 = new Jugador(susCartas11);
	        			
	        			//paraJLabel viene de AccionesJuego y permite que JLabel muester las cartas
	        			lblCartasJ1.setText(acciones.paraJLabel(jugador1));
	        			
	        			break;
	        		case 2:
	        			numeroDeJugadores = 2;
	        			jugador1 = new Jugador();
	        			jugador2 = new Jugador();
	        			
	        			lblCartasJ1.setEnabled(true);
	        			lblCartasJ1.setBackground(Color.WHITE);
	        			lblCartasJ2.setEnabled(true);
	        			lblCartasJ2.setBackground(Color.WHITE);
	        			
	        			List<Carta> susCartas21 = new ArrayList<>();
	        			susCartas21 = acciones.RepartirCartas(mazo, 2);
	        			jugador1 = new Jugador(susCartas21);
	        			lblMoneda1.setVisible(true);
	        			//paraJLabel viene de AccionesJuego y permite que JLabel muester las cartas
	        			lblCartasJ1.setText(acciones.paraJLabel(jugador1));
	        			
	        			List<Carta> susCartas22 = new ArrayList<>();
	        			susCartas22 = acciones.RepartirCartas(mazo, 2);
	        			jugador2 = new Jugador(susCartas22);
	        			lblMoneda2.setVisible(true);
	        			//paraJLabel viene de AccionesJuego y permite que JLabel muester las cartas
	        			lblCartasJ2.setText(acciones.paraJLabel(jugador2));
	        			
	        			break;
	        		case 3:
	        			numeroDeJugadores = 3;
	        			jugador1 = new Jugador();
	        			jugador2 = new Jugador();
	        			jugador3 = new Jugador();
	        			
	        			lblCartasJ1.setEnabled(true);
	        			lblCartasJ1.setBackground(Color.WHITE);
	        			lblCartasJ2.setEnabled(true);
	        			lblCartasJ2.setBackground(Color.WHITE);
	        			lblCartasJ3.setEnabled(true);
	        			lblCartasJ3.setBackground(Color.WHITE);
	        			
	        			List<Carta> susCartas31 = new ArrayList<>();
	        			susCartas31 = acciones.RepartirCartas(mazo, 2);
	        			jugador1 = new Jugador(susCartas31);
	        			lblMoneda1.setVisible(true);
	        			//paraJLabel viene de AccionesJuego y permite que JLabel muester las cartas
	        			lblCartasJ1.setText(acciones.paraJLabel(jugador1));
	        			
	        			List<Carta> susCartas32 = new ArrayList<>();
	        			susCartas32 = acciones.RepartirCartas(mazo, 2);
	        			jugador2 = new Jugador(susCartas32);
	        			lblMoneda2.setVisible(true);
	        			//paraJLabel viene de AccionesJuego y permite que JLabel muester las cartas
	        			lblCartasJ2.setText(acciones.paraJLabel(jugador2));
	        			
	        			List<Carta> susCartas33 = new ArrayList<>();
	        			susCartas33 = acciones.RepartirCartas(mazo, 2);
	        			jugador3 = new Jugador(susCartas33);
	        			lblMoneda3.setVisible(true);
	        			//paraJLabel viene de AccionesJuego y permite que JLabel muester las cartas
	        			lblCartasJ3.setText(acciones.paraJLabel(jugador3));
	        			break;
	        		case 4:
	        			numeroDeJugadores = 4;
	        			jugador1 = new Jugador();
	        			jugador2 = new Jugador();
	        			jugador3 = new Jugador();
	        			jugador4 = new Jugador();
	        			
	        			lblCartasJ1.setEnabled(true);
	        			lblCartasJ1.setBackground(Color.WHITE);
	        			lblCartasJ2.setEnabled(true);
	        			lblCartasJ2.setBackground(Color.WHITE);
	        			lblCartasJ3.setEnabled(true);
	        			lblCartasJ3.setBackground(Color.WHITE);
	        			lblCartasJ4.setEnabled(true);
	        			lblCartasJ4.setBackground(Color.WHITE);
	        			
	        			List<Carta> susCartas41 = new ArrayList<>();
	        			susCartas41 = acciones.RepartirCartas(mazo, 2);
	        			jugador1 = new Jugador(susCartas41);
	        			lblMoneda1.setVisible(true);
	        			
	        			//paraJLabel viene de AccionesJuego y permite que JLabel muester las cartas
	        			lblCartasJ1.setText(acciones.paraJLabel(jugador1));
	        
	        			List<Carta> susCartas42 = new ArrayList<>();
	        			susCartas42 = acciones.RepartirCartas(mazo, 2);
	        			jugador2 = new Jugador(susCartas42);
	        			lblMoneda2.setVisible(true);
	        			
	        			//paraJLabel viene de AccionesJuego y permite que JLabel muester las cartas
	        			lblCartasJ2.setText(acciones.paraJLabel(jugador2));
	        			
	        			List<Carta> susCartas43 = new ArrayList<>();
	        			susCartas43 = acciones.RepartirCartas(mazo, 2);
	        			jugador3 = new Jugador(susCartas43);
	        			lblMoneda3.setVisible(true);
	        			
	        			//paraJLabel viene de AccionesJuego y permite que JLabel muester las cartas
	        			lblCartasJ3.setText(acciones.paraJLabel(jugador3));
	        			
	        			List<Carta> susCartas44 = new ArrayList<>();
	        			susCartas44 = acciones.RepartirCartas(mazo, 2);
	        			jugador4 = new Jugador(susCartas44);
	        			lblMoneda4.setVisible(true);
	        			
	        			//paraJLabel viene de AccionesJuego y permite que JLabel muester las cartas
	        			lblCartasJ4.setText(acciones.paraJLabel(jugador4));
	        			break;
	        		default:
	        			textoMensaje.setText("SOLO PUEDEN JUGAR 4 A LA VEZ");
	        	}
        	}
        	btnRepartir.setEnabled(false);
        }
        
        //Boton QUIEN GANA?
        if(textoBoton == "¿Quién Gana?" ) {
        	btnInicioJuego.setEnabled(true);
        	btnRepartir.setEnabled(false);
        	btnInicioJuego.setText("¿Jugar otra vez?");
        	switch(numeroDeJugadores) {
        	
        	//JUGADORES = 1
        	case 1:
        		double resultado11;
        		resultado11 = acciones.sumarPuntosCartas(jugador1);
        		textoMensaje.setText("El jugador "+numeroDeJugadores+" tiene "+resultado11+" puntos");
        		if(resultado11 == 7 || resultado11 == 7.5) {
        			textoMensaje.setText("El jugador "+numeroDeJugadores+" QUE SUERTE, GANO!!!");
        		}else {
        			textoMensaje.setText("El jugador "+numeroDeJugadores+" ESTA BIEN SALADO !!!");
        		}
        		break;
        		
        		//JUGADORES = 2
        	case 2:
        		double resultado21;
        		resultado21 = acciones.sumarPuntosCartas(jugador1);
        		double resultado22;
        		resultado22 = acciones.sumarPuntosCartas(jugador2);
        		textoMensaje.setText("El jugador 1 tiene: "+resultado21+"\n el jugador 2 tiene: "+resultado22);
        		
        		if( (resultado21 == 7 || resultado21 == 7.5) && (resultado22 != 7 && resultado22 != 7.5)) {
        			textoMensaje.setText("El jugador "+(numeroDeJugadores - 1)+" QUE SUERTE, GANO!!!");
        		}else if( (resultado22 == 7 || resultado22 == 7.5) && (resultado21 != 7 && resultado21 != 7.5)) {
        			textoMensaje.setText("El jugador "+numeroDeJugadores+" QUE SUERTE, GANO!!!");
        		}else if((resultado21 == 7 || resultado21 == 7.5) && (resultado22 == 7 || resultado22 == 7.5) ) {
        			textoMensaje.setText("GANADORES "+(numeroDeJugadores - 1)+"--"+numeroDeJugadores+" !!!");
        		}else {
        			textoMensaje.setText("NADIE GANA !!!!");
        		}
        		
//        		if(resultado21 == 7 || resultado21 == 7.5) {
//        			textoMensaje.setText("El jugador "+(numeroDeJugadores - 1)+" QUE SUERTE, GANO!!!");
//        		}else if(resultado22 == 7 || resultado22 == 7.5) {
//        			textoMensaje.setText("El jugador "+numeroDeJugadores+" QUE SUERTE, GANO!!!");
//        		}else if ((resultado21 == 7 || resultado21 == 7.5) && (resultado22 == 7 || resultado22 == 7.5)){
//        			textoMensaje.setText("GANADORES "+(numeroDeJugadores - 1)+"--"+numeroDeJugadores+" !!!");
//        		}else {
//        			textoMensaje.setText("NADIE GANA !!!!");
//        		}
        		break;
        		
        		//JUGADORES = 3
        	case 3:
        		double resultado31;
        		resultado31 = acciones.sumarPuntosCartas(jugador1);
        		double resultado32;
        		resultado32 = acciones.sumarPuntosCartas(jugador2);
        		double resultado33;
        		resultado33 = acciones.sumarPuntosCartas(jugador3);
        		double[] quienSera = new double[3];
        		quienSera[0]=resultado31;
        		quienSera[1]=resultado32;
        		quienSera[2]=resultado33;
        		List<Integer> valores7 = acciones.resultadoValor(quienSera, 7);
        		List<Integer> valores75 = acciones.resultadoValor(quienSera, 7.5);
        		
        		//Empieza la validación
        		if(valores7.isEmpty() && valores75.isEmpty()) {
        			
        			textoMensaje.setText("NADIE GANA !!!");
        			break;
        		}
        		//Encuentra un valor de 7 los de 7.5 estan vacios
        		if(!valores7.isEmpty() && valores75.isEmpty()) {
        			//Solo uno encontrado por resultado
        			if(valores7.size() == 1) {
        				
        				textoMensaje.setText("GANO EL JUGADOR "+ valores7.get(0)+" SUERTUDO !!!");
        				break;
        			}else {
        		
        				String ganadores;
        				ganadores ="LOS GANADORES SON ";
        				for(int indice:valores7) {
        					ganadores = ganadores + indice+ "--";
        				}
        				ganadores = ganadores + " !!!";
        				textoMensaje.setText(ganadores);
        				break;
        			}
        		}
        		if(valores7.isEmpty() && !valores75.isEmpty()) {
        			if(valores75.size() == 1) {
        				textoMensaje.setText("GANO EL JUGADOR "+valores75.get(0)+" SUERTUDO !!!");
        				
        				break;
        			}else {
        				String ganadores;
        				ganadores = "LOS GANADORES SON ";
        				for(int indice:valores75) {
        					ganadores = ganadores +indice+ "--";
        					
        				}
        				ganadores = ganadores + " !!!";
        				textoMensaje.setText(ganadores);
        				
        				break;
        			}
        		}
        		if(!valores7.isEmpty() && !valores75.isEmpty()) {
        			String ganadores; 
        			ganadores = "GANADORES: ";
        			for(Integer indice:valores7) {
        				ganadores = ganadores + indice+"--";
        			}
        			for(Integer indice:valores75) {
        				ganadores = ganadores + indice+"--";
        			}
        			ganadores = ganadores + " !!!";
        			textoMensaje.setText(ganadores);
        			break;
        		}
        		
        	//JUGADORES = 4	
        	case 4:
        		double resultado41;
        		resultado41 = acciones.sumarPuntosCartas(jugador1);
        		double resultado42;
        		resultado42 = acciones.sumarPuntosCartas(jugador2);
        		double resultado43;
        		resultado43 = acciones.sumarPuntosCartas(jugador3);
        		double resultado44;
        		resultado44 = acciones.sumarPuntosCartas(jugador4);
        		
        		double[] quienSera4 = new double[4];
        		quienSera4[0]=resultado41;
        		quienSera4[1]=resultado42;
        		quienSera4[2]=resultado43;
        		quienSera4[3]=resultado44;
        		
        		List<Integer> valores7_4 = acciones.resultadoValor(quienSera4, 7);
        		List<Integer> valores75_4 = acciones.resultadoValor(quienSera4, 7.5);
        		
        		if(valores7_4.isEmpty() && valores75_4.isEmpty()) {
        			textoMensaje.setText("NADIE GANA !!!");
        			break;
        		}
        		if(!valores7_4.isEmpty() && valores75_4.isEmpty()) {
        			if(valores7_4.size() == 1) {
        				textoMensaje.setText("GANO EL JUGADOR "+ valores7_4.get(0)+" SUERTUDO !!!");
        				break;
        			}else {
        				String ganadores;
        				ganadores ="LOS GANADORES SON ";
        				for(int indice:valores7_4) {
        					ganadores = ganadores + indice+ "--";
        				}
        				ganadores = ganadores + " !!!";
        				textoMensaje.setText(ganadores);
        				break;
        			}
        		}
        		if(valores7_4.isEmpty() && !valores75_4.isEmpty()) {
        			if(valores75_4.size() == 1) {
        				textoMensaje.setText("GANO EL JUGADOR "+valores75_4.get(0)+" SUERTUDO !!!");
        				break;
        			}else {
        				String ganadores;
        				ganadores = "LOS GANADORES SON ";
        				for(int indice:valores75_4) {
        					ganadores = ganadores +indice+ "--";
        				}
        				ganadores = ganadores + " !!!";
        				textoMensaje.setText(ganadores);
        				break;
        			}
        		}
        		if(!valores7_4.isEmpty() && !valores75_4.isEmpty()) {
        			String ganadores; 
        			ganadores = "GANADORES: ";
        			for(Integer indice:valores7_4) {
        				ganadores = ganadores + indice+"--";
        			}
        			for(Integer indice:valores75_4) {
        				ganadores = ganadores + indice+"--";
        			}
        			ganadores = ganadores + " !!!";
        			textoMensaje.setText(ganadores);
        			break;
        		}
        	}
        	btnQuien.setEnabled(false);
        }
	}
}

