package vues;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.Labyrinthe;

public class HudBarreDeVie extends Hud{
	private Labyrinthe laby;


	public HudBarreDeVie(Labyrinthe lyb){
		this.laby = lyb;
	}

	@Override
	public void init(float x, float y) throws SlickException {
		// TODO Auto-generated method stub
		this.playerbars = new Image("main/resources/hud/Heart_Sprite.png");
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(Graphics g) {

		int taille = laby.getLink().getPointVie();
		x = Labyrinthe.HAUTEUR_MUR;
		y = Labyrinthe.HAUTEUR_MUR;
		for (int i=1; i <= taille ; i++) {
			g.drawImage(this.playerbars, x, y);
			x+=18;
		}
	}
}
