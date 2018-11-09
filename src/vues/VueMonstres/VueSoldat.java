package vues.VueMonstres;

import model.personnages.monstres.Monstre;
import model.personnages.monstres.Soldat;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.Random;

public class VueSoldat extends VueMonstres {

	private final static  String CHEMIN_Ennemis = "main/resources/Personnages/Monstres/ennemis.png";

	private final static int HAUTEUR_SPRITES = 39;
	private final static int LARGEUR_SPRITES = 37;
    private int xSprite =0;


	// largeur orc 30 / hauteur orc 55

	public VueSoldat(Soldat soldat) throws SlickException{
		super(soldat);
        Random r = new Random();
        int valeur =  r.nextInt(2);
        if(valeur == 1){
            xSprite = 3;
        }
		this.chargerAnimation(CHEMIN_Ennemis);
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
		animation.addFrame(spriteSheet.getSprite(0+xSprite, 4), 200);
		this.animations[Monstre.BAS] = animation;
	}

	private void chargerStaticGauche(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(1+xSprite, 4), 200);
		this.animations[Monstre.GAUCHE] = animation;
	}

	private void chargerStaticHaut(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(2+xSprite, 4), 200);
		this.animations[Monstre.HAUT] = animation;
	}

	private void chargerStaticDroite(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(1+xSprite, 4), 200);
		this.animations[Monstre.DROITE] = animation;
	}

	private void chargerMarcheBas(String chemin, int largeur, int hauteur) throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
		Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0+xSprite, 4), 100);
        animation.addFrame(spriteSheet.getSprite(0+xSprite, 5), 100);
        animation.addFrame(spriteSheet.getSprite(0+xSprite, 6), 100);
        animation.addFrame(spriteSheet.getSprite(0+xSprite, 7), 100);

		this.animations[Monstre.AVANCER_BAS] = animation;
	}

	private void chargerMarcheDroite(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
		Animation animation = new Animation();
		Image img = spriteSheet.getSprite(1+xSprite, 4);
		img = img.getFlippedCopy(true,false);
		animation.addFrame(img, 100);
		img = spriteSheet.getSprite(1+xSprite, 5);
		img = img.getFlippedCopy(true,false);
		animation.addFrame(img, 100);
		img = spriteSheet.getSprite(1+xSprite, 6);
		img = img.getFlippedCopy(true,false);
		animation.addFrame(img, 100);
		this.animations[Monstre.AVANCER_DROITE] = animation;
	}

	private void chargerMarcheGauche(String chemin, int largeur, int hauteur) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(1+xSprite, 4), 100);
		animation.addFrame(spriteSheet.getSprite(1+xSprite, 5), 100);
		animation.addFrame(spriteSheet.getSprite(1+xSprite, 6), 100);
		this.animations[Monstre.AVANCER_GAUCHE] = animation;
	}


	private void chargerMarcheHaut(String chemin, int largeur, int hauteur)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(2+xSprite, 4), 100);
		animation.addFrame(spriteSheet.getSprite(2+xSprite, 5), 100);
		animation.addFrame(spriteSheet.getSprite(2+xSprite, 6), 100);
		animation.addFrame(spriteSheet.getSprite(2+xSprite, 7), 100);

		this.animations[Monstre.AVANCER_HAUT] = animation;
	}



}
