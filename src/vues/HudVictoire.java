package vues;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class HudVictoire extends Hud{
	
	private int compteur;
	private int hauteur = 256;
	private int largeur = 1024;

	@Override
	public void init(float x, float y) throws SlickException {
		// TODO Auto-generated method stub
		this.playerbars = new Image("main/resources/hud/Victory_royale.png");
		this.x = x;
		this.y = y;
		compteur = 0;
	}

	@Override
	public void render(int cameraX, int cameraY, Graphics g, GameContainer container) {
		// TODO Auto-generated method stub
		if (compteur < 400) {
			g.drawImage(this.playerbars,cameraX+(container.getWidth()-largeur)/2,cameraY+hauteur);
			compteur++;
		}
	}

}
