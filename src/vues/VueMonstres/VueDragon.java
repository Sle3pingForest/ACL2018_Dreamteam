package vues.VueMonstres;

import java.util.Random;

import model.personnages.Personnage;
import model.personnages.monstres.Dragon;
import model.personnages.monstres.Monstre;
import model.personnages.monstres.Soldat;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class VueDragon extends VueMonstres {
	private final static  String CHEMIN_DRAGON = "main/resources/Personnages/Monstres/Dragon.png";
	private final static  String CHEMIN_MORT= "main/resources/Personnages/Monstres/death.png";

	private final static int HAUTEUR_SPRITES = 50;
	private final static int LARGEUR_SPRITES = 49;

	public VueDragon(Dragon dragon) throws SlickException{
		super(dragon); 
		this.chargerAnimation(CHEMIN_DRAGON);
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
		mortEffect();

	}

	protected void chargerStaticBas(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 0), 200);
		this.animations[Monstre.BAS] = animation;
	}

	protected void chargerStaticGauche(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(1, 0), 200);
		this.animations[Monstre.GAUCHE] = animation;
	}

	protected void chargerStaticHaut(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(3, 0), 200);
		this.animations[Monstre.HAUT] = animation;
	}

	protected void chargerStaticDroite(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(2, 0), 200);
		this.animations[Monstre.DROITE] = animation;
	}

	protected void chargerMarcheBas(String chemin, int largeur, int hauteur) throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 0), 100);
        animation.addFrame(spriteSheet.getSprite(1, 0), 100);
        animation.addFrame(spriteSheet.getSprite(2, 0), 100);
        animation.addFrame(spriteSheet.getSprite(3, 0), 100);

		this.animations[Monstre.AVANCER_BAS] = animation;
	}

	protected void chargerMarcheDroite(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);//+3  car les largeur sur le sprite son pas toute les meme
		Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 2), 100);
        animation.addFrame(spriteSheet.getSprite(1, 2), 100);
        animation.addFrame(spriteSheet.getSprite(2, 2), 100);
        animation.addFrame(spriteSheet.getSprite(3, 2), 100);
		this.animations[Monstre.AVANCER_DROITE] = animation;
	}

	protected void chargerMarcheGauche(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);//+2  car les largeur sur le sprite son pas toute les meme
		Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 1), 100);
        animation.addFrame(spriteSheet.getSprite(1, 1), 100);
        animation.addFrame(spriteSheet.getSprite(2, 1), 100);
        animation.addFrame(spriteSheet.getSprite(3, 1), 100);
		this.animations[Monstre.AVANCER_GAUCHE] = animation;
	}


	protected void chargerMarcheHaut(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();

        animation.addFrame(spriteSheet.getSprite(0, 3), 100);
        animation.addFrame(spriteSheet.getSprite(1, 3), 100);
        animation.addFrame(spriteSheet.getSprite(2, 3), 100);
        animation.addFrame(spriteSheet.getSprite(3, 3), 100);
		this.animations[Monstre.AVANCER_HAUT] = animation;
	}
	
	public void mortEffect() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(CHEMIN_MORT, 26, 32);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(3, 0), 200);
		this.animations[Monstre.MORT] = animation;
	}


}
