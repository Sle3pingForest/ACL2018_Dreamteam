package vues;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.Labyrinthe;

public class HudEndGame {
	
	private Image playerbars;
	private float x,y;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static int width = (int) screenSize.getWidth();
	private static int height = (int) screenSize.getHeight();
	
	public void init(float x, float y) throws SlickException {
		this.playerbars = new Image("main/resources/hud/you_died.png");
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		g.drawImage(this.playerbars,x/2,y/2);
	}

}
