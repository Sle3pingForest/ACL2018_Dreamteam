package vues.VueMonstres;

import java.util.Observable;
import java.util.Observer;

import model.personnages.Heros;
import model.personnages.monstres.Monstre;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import vues.VueLabyrinthe;

public abstract class VueMonstres implements Observer {

	

	protected Animation[] animations = new Animation[20];
	protected Monstre m;

	public VueMonstres(Monstre m){
		this.m = m;
	}

	protected void chargerAnimation(String chemin,int largeur, int hauteur) throws SlickException{

		chargerStaticGauche(chemin, largeur, hauteur);
		chargerStaticBas(chemin, largeur, hauteur);
		chargerStaticGauche(chemin, largeur, hauteur);
		chargerStaticHaut(chemin, largeur, hauteur);
		chargerStaticDroite(chemin, largeur, hauteur);


		chargerMarcheBas(chemin, largeur, hauteur);
		chargerMarcheDroite(chemin, largeur, hauteur);
		chargerMarcheGauche(chemin, largeur, hauteur);
		chargerMarcheHaut(chemin, largeur, hauteur);

	}

	private void chargerStaticBas(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 4), 200);
		animation.addFrame(spriteSheet.getSprite(1, 4), 200);
		animation.addFrame(spriteSheet.getSprite(2, 4), 200);
		animation.addFrame(spriteSheet.getSprite(3, 4), 200);
		animation.addFrame(spriteSheet.getSprite(4, 4), 200);
		animation.addFrame(spriteSheet.getSprite(3, 4), 200);
		animation.addFrame(spriteSheet.getSprite(2, 4), 200);
		animation.addFrame(spriteSheet.getSprite(1, 4), 200);
		animation.addFrame(spriteSheet.getSprite(0, 4), 200);
		this.animations[Monstre.BAS_M] = animation;
	}

	private void chargerStaticGauche(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 3), 200);
		this.animations[Monstre.GAUCHE_M] = animation;
	}

	private void chargerStaticHaut(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 2), 200);
		this.animations[Monstre.HAUT_M] = animation;
	}

	private void chargerStaticDroite(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 1), 200);
		this.animations[Monstre.DROITE_M] = animation;
	}

	private void chargerMarcheBas(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 0), 50);
		animation.addFrame(spriteSheet.getSprite(1, 0), 50);
		animation.addFrame(spriteSheet.getSprite(2, 0), 50);
		animation.addFrame(spriteSheet.getSprite(3, 0), 50);
		animation.addFrame(spriteSheet.getSprite(4, 0), 50);
		animation.addFrame(spriteSheet.getSprite(5, 0), 50);
		animation.addFrame(spriteSheet.getSprite(6, 0), 50);
		animation.addFrame(spriteSheet.getSprite(7, 0), 50);
		animation.addFrame(spriteSheet.getSprite(8, 0), 50);
		animation.addFrame(spriteSheet.getSprite(9, 0), 50);
		animation.addFrame(spriteSheet.getSprite(10, 0), 50);
		this.animations[Monstre.AVANCER_BAS_M] = animation;
	}

	private void chargerMarcheDroite(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 1), 50);
		animation.addFrame(spriteSheet.getSprite(1, 1), 50);
		animation.addFrame(spriteSheet.getSprite(2, 1), 50);
		animation.addFrame(spriteSheet.getSprite(3, 1), 50);
		animation.addFrame(spriteSheet.getSprite(4, 1), 50);
		animation.addFrame(spriteSheet.getSprite(5, 1), 50);
		animation.addFrame(spriteSheet.getSprite(6, 1), 50);
		animation.addFrame(spriteSheet.getSprite(7, 1), 50);
		animation.addFrame(spriteSheet.getSprite(8, 1), 50);
		animation.addFrame(spriteSheet.getSprite(9, 1), 50);
		animation.addFrame(spriteSheet.getSprite(10, 1), 50);
		this.animations[Monstre.AVANCER_DROITE_M] = animation;
	}

	private void chargerMarcheGauche(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 3), 50);
		animation.addFrame(spriteSheet.getSprite(1, 3), 50);
		animation.addFrame(spriteSheet.getSprite(2, 3), 50);
		animation.addFrame(spriteSheet.getSprite(3, 3), 50);
		animation.addFrame(spriteSheet.getSprite(4, 3), 50);
		animation.addFrame(spriteSheet.getSprite(5, 3), 50);
		animation.addFrame(spriteSheet.getSprite(6, 3), 50);
		animation.addFrame(spriteSheet.getSprite(7, 3), 50);
		animation.addFrame(spriteSheet.getSprite(8, 3), 50);
		animation.addFrame(spriteSheet.getSprite(9, 3), 50);
		animation.addFrame(spriteSheet.getSprite(10, 3), 50);
		this.animations[Monstre.AVANCER_GAUCHE_M] = animation;
	}


	private void chargerMarcheHaut(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 2 ), 50);
		animation.addFrame(spriteSheet.getSprite(1, 2), 50);
		animation.addFrame(spriteSheet.getSprite(2, 2), 50);
		animation.addFrame(spriteSheet.getSprite(3, 2), 50);
		animation.addFrame(spriteSheet.getSprite(4, 2), 50);
		animation.addFrame(spriteSheet.getSprite(5, 2), 50);
		animation.addFrame(spriteSheet.getSprite(6, 2), 50);
		animation.addFrame(spriteSheet.getSprite(7, 2), 50);
		animation.addFrame(spriteSheet.getSprite(8, 2), 50);
		animation.addFrame(spriteSheet.getSprite(9, 2), 50);
		animation.addFrame(spriteSheet.getSprite(10, 2), 50);

		this.animations[Monstre.AVANCER_HAUT_M] = animation;
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public void render(GameContainer container, Graphics g) {
		float x = m.getX() ;
		float y = m.getY();
		g.drawAnimation(animations[m.getDirectionActu()],(int)x,(int)y);

	}

}
