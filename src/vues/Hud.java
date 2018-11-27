package vues;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.Labyrinthe;

public abstract class Hud {
	
	protected Image playerbars;
	protected float x,y;
	protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	protected static int width = (int) screenSize.getWidth();
	protected static int height = (int) screenSize.getHeight();
	
	public abstract void init(float x, float y) throws SlickException;
	
	public abstract void render(int cameraX ,int cameraY,Graphics g);

}
