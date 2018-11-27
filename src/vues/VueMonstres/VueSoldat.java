package vues.VueMonstres;

import model.personnages.monstres.Monstre;
import model.personnages.monstres.Soldat;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.Random;

public class VueSoldat extends VueMonstres {

	private final static  String CHEMIN_VERT = "main/resources/Personnages/Monstres/GreenSoldier.png";
	private final static  String CHEMIN_ROUGE = "main/resources/Personnages/Monstres/RedSoldier.png";
	private final static  String CHEMIN_BLEU = "main/resources/Personnages/Monstres/BlueSoldier.png";
	private final static  String CHEMIN_MORT= "main/resources/Personnages/Monstres/death.png";




	public VueSoldat(Soldat soldat) throws SlickException{
		super(soldat);
        Random r = new Random();
        int couleur =  r.nextInt(3);
		switch(couleur){
			case 0 :
				this.chargerAnimation(CHEMIN_BLEU);
				break;
			case 1 :
				this.chargerAnimation(CHEMIN_ROUGE);
				break;
			case 2 :
				this.chargerAnimation(CHEMIN_VERT);
				break;
		}

	}

	protected void chargerAnimation(String chemin) throws SlickException{

		int largeur = m.getSPRITE_LARGEUR();
		int hauteur = m.getSPRITE_HAUTEUR();
		chargerStaticGauche(chemin,largeur, hauteur);
		chargerStaticBas(chemin,largeur,hauteur);
		chargerStaticGauche(chemin,  largeur,hauteur);
		chargerStaticHaut(chemin,  largeur, hauteur);
		chargerStaticDroite(chemin,  largeur, hauteur);


		chargerMarcheBas(chemin, largeur, hauteur);
		chargerMarcheDroite(chemin, largeur, hauteur);
		chargerMarcheGauche(chemin,  largeur, hauteur);
		chargerMarcheHaut(chemin,  largeur, hauteur);
		mortEffect();

	}


	protected void chargerStaticBas(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(1, 4), 200);
		this.animations[Monstre.BAS] = animation;
	}

	protected void chargerStaticGauche(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(1, 4), 200);
		this.animations[Monstre.GAUCHE] = animation;
	}

	protected void chargerStaticHaut(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(2, 4), 200);
		this.animations[Monstre.HAUT] = animation;
	}

	protected void chargerStaticDroite(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(1, 4), 200);
		this.animations[Monstre.DROITE] = animation;
	}

	protected void chargerMarcheBas(String chemin, int largeur, int hauteur) throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(1, 0), 150);
        animation.addFrame(spriteSheet.getSprite(1, 1), 150);
        animation.addFrame(spriteSheet.getSprite(1, 2), 150);
        animation.addFrame(spriteSheet.getSprite(1, 3), 150);

		this.animations[Monstre.AVANCER_BAS] = animation;
	}

	protected void chargerMarcheDroite(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);//+3  car les largeur sur le sprite son pas toute les meme
		Animation animation = new Animation();
		Image img = spriteSheet.getSprite(0, 0);
		img = img.getFlippedCopy(true,false);
		animation.addFrame(img, 100);
		img = spriteSheet.getSprite(0, 1);
		img = img.getFlippedCopy(true,false);
		animation.addFrame(img, 100);
		img = spriteSheet.getSprite(0, 2);
		img = img.getFlippedCopy(true,false);
		animation.addFrame(img, 100);
		this.animations[Monstre.AVANCER_DROITE] = animation;
	}

	protected void chargerMarcheGauche(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);//+2  car les largeur sur le sprite son pas toute les meme
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 0), 100);
		animation.addFrame(spriteSheet.getSprite(0, 1), 100);
		animation.addFrame(spriteSheet.getSprite(0, 2), 100);
		this.animations[Monstre.AVANCER_GAUCHE] = animation;
	}


	protected void chargerMarcheHaut(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();


		Image img = spriteSheet.getSprite(2, 0);
		img = img.getFlippedCopy(false,true);
		animation.addFrame(img, 150);
		img = spriteSheet.getSprite(2, 1);
		img = img.getFlippedCopy(false,true);
		animation.addFrame(img, 150);
		img = spriteSheet.getSprite(2, 2);
		img = img.getFlippedCopy(false,true);
		animation.addFrame(img, 150);
		img = spriteSheet.getSprite(2, 3);
		img = img.getFlippedCopy(false,true);
		animation.addFrame(img, 150);
		this.animations[Monstre.AVANCER_HAUT] = animation;
	}
	
	protected void mortEffect() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(CHEMIN_MORT, 26, 32);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(3, 0), 200);
		this.animations[Monstre.MORT] = animation;
	}


}
