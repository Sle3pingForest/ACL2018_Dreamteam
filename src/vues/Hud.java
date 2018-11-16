package vues;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.Labyrinthe;

public class Hud {
	
	private Image playerbars;
	private float x,y;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static int width = (int) screenSize.getWidth();
	private static int height = (int) screenSize.getHeight();
	
	public void init(float x, float y) throws SlickException {
		this.playerbars = new Image("main/resources/hud/Heart_Sprite.png");
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		g.drawImage(this.playerbars,(1*Labyrinthe.HAUTEUR_MUR),(1*Labyrinthe.HAUTEUR_MUR));
	}

}
