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

public abstract class VueMonstres implements Observer {
	
	protected Animation[] animations = new Animation[20];
	protected Monstre m;
	
	public VueMonstres(Monstre m){
		this.m = m;
	}
	
	protected void chargerAnimation(String chemin,int largeur, int hauteur) throws SlickException{

        chargerStaticBas(chemin, largeur, hauteur);
        /*chargerStaticGauche(chemin, largeur, hauteur);
        chargerStaticHaut(chemin, largeur, hauteur);
        chargerStaticDroite(chemin, largeur, hauteur);*/
        

        /*chargerMarcheBas(CHEMIN_ORC);
        chargerMarcheDroite(CHEMIN_ORC);
        chargerMarcheGauche(CHEMIN_ORC);
        chargerMarcheHaut(CHEMIN_ORC);*/
	 
 }
 
    private void chargerStaticBas(String chemin, int largeur, int hauteur) throws SlickException{
    	SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 0), 500);
        this.animations[Heros.BAS] = animation;
    }

    private void chargerStaticGauche(String chemin, int largeur, int hauteur)throws SlickException{
    	SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(3, 0), 500);
        animation.addFrame(spriteSheet.getSprite(3, 0), 500);
        animation.addFrame(spriteSheet.getSprite(3, 0), 500);
        animation.addFrame(spriteSheet.getSprite(3, 0), 500);
        animation.addFrame(spriteSheet.getSprite(3, 0), 500);
        animation.addFrame(spriteSheet.getSprite(3, 0), 500);
        /*
        animation.addFrame(spriteSheet.getSprite(3, 56), 300);
        animation.addFrame(spriteSheet.getSprite(3, 56), 300);
        animation.addFrame(spriteSheet.getSprite(3, 56), 300);
        animation.addFrame(spriteSheet.getSprite(3, 56), 300);
        animation.addFrame(spriteSheet.getSprite(3, 56), 500);*/
        this.animations[Heros.GAUCHE] = animation;
    }

    private void chargerStaticHaut(String chemin, int largeur, int hauteur)throws SlickException{
    	SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(2, 0), 50);
        this.animations[Heros.HAUT] = animation;
    }

    private void chargerStaticDroite(String chemin, int largeur, int hauteur)throws SlickException{
    	SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur, hauteur);
        Animation animation = new Animation();
        Image img = spriteSheet.getSprite(0, 0);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 500);
        /*img = spriteSheet.getSprite(1, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 300);
        img = spriteSheet.getSprite(2, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 300);
        img = spriteSheet.getSprite(2, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 300);
        img = spriteSheet.getSprite(1, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 300);
        img = spriteSheet.getSprite(0, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 500);*/
        this.animations[Heros.DROITE] = animation;
    }
	   
   

@Override
public void update(Observable o, Object arg) {
	// TODO Auto-generated method stub
	
}

public void render(GameContainer container, Graphics g) {

	float x = m.getX();
	float y = m.getY();
    g.drawAnimation(animations[m.getDirectionActu()],(int)x,(int)y);
	
}

}
