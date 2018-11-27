package vues;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.Labyrinthe;

public class HudEndGame extends Hud{

	private int hauteur = 256;
	private int largeur = 1024;

	public void init(float x, float y) throws SlickException {
		this.playerbars = new Image("main/resources/hud/you_died.png");
		this.x = x;
		this.y = y;
	}
	
	public void render(int cameraX, int cameraY, Graphics g, GameContainer container) {
		g.drawImage(this.playerbars,cameraX+(container.getWidth()-largeur)/2,cameraY+hauteur);
	}

}
