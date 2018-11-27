package vues;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.Labyrinthe;

public class HudEndGame extends Hud{
	
	public void init(float x, float y) throws SlickException {
		this.playerbars = new Image("main/resources/hud/you_died.png");
		this.x = x;
		this.y = y;
	}
	
	public void render(int cameraX, int cameraY,Graphics g) {
		g.drawImage(this.playerbars,x/2,y/2);
	}

}
