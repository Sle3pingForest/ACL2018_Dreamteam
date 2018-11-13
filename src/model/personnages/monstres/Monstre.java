package model.personnages.monstres;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import vues.VueHeros;
import vues.VueLabyrinthe;
import model.Labyrinthe;
import model.Item.Item;
import model.mur.Mur;
import model.personnages.Heros;
import model.personnages.Personnage;

public abstract class Monstre extends Personnage {


	public final static  float VITESSE_M = 0.05f;

	protected  int LARGEUR_SPRITE = 30;    
	protected  int HAUTEUR_SPRITE = 30;
	protected  int HAUTEUR = 30;
	protected  int LARGEUR = 30;
	
	protected float posXPrecedent, posYprecedent;




	public Monstre(int x, int y){
		super(x,y);
		posXPrecedent = x;
		posYprecedent = y;
		goBas();
	}

	public void directionAleatoire(){
		vertical = 0;
		horizontal = 0;
		Random r = new Random();
		int alea = r.nextInt(4);
		switch(alea){
			case 0:
				vertical = -1;
				directionActu = Personnage.AVANCER_HAUT;
				break;
			case 1:
				horizontal = 1;
				directionActu = Personnage.AVANCER_DROITE;
				break;
			case 2:
				directionActu = Personnage.AVANCER_BAS;
				vertical = 1;
				break;
			case 3:
				directionActu = Personnage.AVANCER_GAUCHE;
				horizontal = -1;
		}
	}
	

	public float getPosXPrecedent() {
		return posXPrecedent;
	}

	public float getPosYprecedent() {
		return posYprecedent;
	}
	

	public void setPosXPrecedent(float posXPrecedent) {
		this.posXPrecedent = posXPrecedent;
	}

	public void setPosYprecedent(float prosYprecedent) {
		this.posYprecedent = prosYprecedent;
	}

}
