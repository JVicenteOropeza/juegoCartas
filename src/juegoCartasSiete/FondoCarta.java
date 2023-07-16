package juegoCartasSiete;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
//Lo utilece para poner el fondo
public class FondoCarta extends JPanel {
		private Image fondoCartas;

	    public FondoCarta(String imagePath) {
	        fondoCartas = new ImageIcon(imagePath).getImage();
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(fondoCartas, 0, 0, getWidth(), getHeight(), this);
	    }
	}
	