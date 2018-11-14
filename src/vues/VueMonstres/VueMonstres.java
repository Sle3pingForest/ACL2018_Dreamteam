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

public abstract class VueMonstres {

	protected Animation[] animations = new Animation[20];
	protected Monstre m;

	public VueMonstres(Monstre m) throws SlickException{
		this.m = m;
	}

	
	protected abstract void chargerAnimation(String chemin) throws SlickException;

	protected abstract void chargerStaticBas(String chemin, int largeur, int hauteur) throws SlickException;

	protected abstract void chargerStaticGauche(String chemin, int largeur, int hauteur)throws SlickException;

	protected abstract void chargerStaticHaut(String chemin, int largeur, int hauteur)throws SlickException;

	protected abstract void chargerStaticDroite(String chemin, int largeur, int hauteur)throws SlickException;

	protected abstract void chargerMarcheBas(String chemin, int largeur, int hauteur) throws SlickException;

	protected abstract void chargerMarcheDroite(String chemin, int largeur, int hauteur) throws SlickException;

	protected abstract void chargerMarcheGauche(String chemin, int largeur, int hauteur) throws SlickException;

	protected abstract void chargerMarcheHaut(String chemin, int largeur, int hauteur)throws SlickException;
	
	protected abstract void mortEffect() throws SlickException;

	public void render(GameContainer container, Graphics g) {
		float x = m.getX() ;
		float y = m.getY();
		g.drawAnimation(animations[m.getDirectionActu()],(int)x,(int)y);

	}
	
}
