package vues;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.Labyrinthe;
import org.newdawn.slick.geom.Rectangle;


public class HudBarreDeVie extends Hud{
	private Labyrinthe laby;
	private int tailleCoeur = 18;


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
	public void render(int cameraX,int cameraY,Graphics g) {

		int taille = laby.getLink().getPointVie();
		x = (int)cameraX+Labyrinthe.LARGEUR_MUR;
		y = (int)cameraY+Labyrinthe.HAUTEUR_MUR;
		for (int i=1; i <= taille ; i++) {
			g.drawImage(this.playerbars, x, y);
			x+=tailleCoeur;
		}
	}
}
