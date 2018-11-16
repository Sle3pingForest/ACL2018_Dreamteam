package vues;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.Labyrinthe;

public class HudBarreDeVie extends Hud{

	@Override
	public void init(float x, float y) throws SlickException {
		// TODO Auto-generated method stub
		this.playerbars = new Image("main/resources/hud/Heart_Sprite.png");
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(this.playerbars,(1*Labyrinthe.HAUTEUR_MUR),(1*Labyrinthe.HAUTEUR_MUR));
	
	}

}
