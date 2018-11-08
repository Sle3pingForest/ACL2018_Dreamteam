package vues.VueMonstres;

import java.util.Observable;

import model.personnages.Heros;
import model.personnages.Personnage;
import model.personnages.monstres.Monstre;
import model.personnages.monstres.Orc;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class VueOrc extends VueMonstres {

	private final static  String CHEMIN_ORC = "main/resources/Personnages/Monstres/enemies.png";

	private final static int HAUTEUR_SPRITES = 39;
	private final static int LARGEUR_SPRITES = 37;

	// largeur orc 30 / hauteur orc 55

	public VueOrc(Orc orc) throws SlickException{
		super(orc);  
		this.chargerAnimation(CHEMIN_ORC);
	}

	protected void chargerAnimation(String chemin) throws SlickException{

		chargerStaticGauche(chemin, LARGEUR_SPRITES, HAUTEUR_SPRITES);
		chargerStaticBas(chemin, LARGEUR_SPRITES, HAUTEUR_SPRITES);
		chargerStaticGauche(chemin, LARGEUR_SPRITES, HAUTEUR_SPRITES);
		chargerStaticHaut(chemin, LARGEUR_SPRITES, HAUTEUR_SPRITES);
		chargerStaticDroite(chemin, LARGEUR_SPRITES, HAUTEUR_SPRITES);


		chargerMarcheBas(chemin, LARGEUR_SPRITES, HAUTEUR_SPRITES);
		chargerMarcheDroite(chemin, LARGEUR_SPRITES, HAUTEUR_SPRITES);
		chargerMarcheGauche(chemin, LARGEUR_SPRITES, HAUTEUR_SPRITES);
		chargerMarcheHaut(chemin, LARGEUR_SPRITES, HAUTEUR_SPRITES);

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
		this.animations[Monstre.BAS] = animation;
	}

	private void chargerStaticGauche(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 3), 200);
		this.animations[Monstre.GAUCHE] = animation;
	}

	private void chargerStaticHaut(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 2), 200);
		this.animations[Monstre.HAUT] = animation;
	}

	private void chargerStaticDroite(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 1), 200);
		this.animations[Monstre.DROITE] = animation;
	}

	private void chargerMarcheBas(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 4), 100);
		animation.addFrame(spriteSheet.getSprite(0, 5), 100);
		animation.addFrame(spriteSheet.getSprite(0, 6), 100);
		animation.addFrame(spriteSheet.getSprite(0, 7), 100);

		this.animations[Monstre.AVANCER_BAS] = animation;
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
		this.animations[Monstre.AVANCER_DROITE] = animation;
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
		this.animations[Monstre.AVANCER_GAUCHE] = animation;
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

		this.animations[Monstre.AVANCER_HAUT] = animation;
	}



}
