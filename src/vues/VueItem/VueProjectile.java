package vues.VueItem;

import model.Item.Epee;
import model.Item.Projectile;
import model.personnages.Heros;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class VueProjectile extends VueItem {

    private final static  String CHEMIN_PROJECTILE = "main/resources/Personnages/Heros/Bleu.png";
    protected Projectile projectile;
    
    
    public VueProjectile(Projectile projectile) throws SlickException {
        super(projectile);
        this.projectile = projectile;
        animations = new Animation[1];
        largeur_Sprite = 30;
        chargerAnimation(CHEMIN_PROJECTILE);
    }

    private void chargerStaticBas(String chemin) throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 0), 500);
        animation.addFrame(spriteSheet.getSprite(1, 0), 300);
        animation.addFrame(spriteSheet.getSprite(2, 0), 300);
        animation.addFrame(spriteSheet.getSprite(2, 0), 300);
        animation.addFrame(spriteSheet.getSprite(1, 0), 300);
        animation.addFrame(spriteSheet.getSprite(0, 0), 500);
        this.animations[Heros.BAS] = animation;
    }

    private void chargerStaticGauche(String chemin)throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 51), 500);
        animation.addFrame(spriteSheet.getSprite(1, 51), 300);
        animation.addFrame(spriteSheet.getSprite(2, 51), 300);
        animation.addFrame(spriteSheet.getSprite(2, 51), 300);
        animation.addFrame(spriteSheet.getSprite(1, 51), 300);
        animation.addFrame(spriteSheet.getSprite(0, 51), 500);
        this.animations[Heros.GAUCHE] = animation;
    }

    private void chargerStaticHaut(String chemin)throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 26), 50);
        this.animations[Heros.HAUT] = animation;
    }

    private void chargerStaticDroite(String chemin)throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
        Animation animation = new Animation();
        Image img = spriteSheet.getSprite(0, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 500);
        img = spriteSheet.getSprite(1, 51);
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
        animation.addFrame(img, 500);
        this.animations[Heros.DROITE] = animation;
    }

    private void chargerMarcheBas(String chemin) throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(9, 19), 150);
        this.animations[Projectile.BAS] = animation;
    }

    /*private void chargerMarcheDroite(String chemin) throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
        Animation animation = new Animation();
        Image img = spriteSheet.getSprite(19, 72);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 100);
        this.animations[Heros.AVANCER_DROITE] = animation;
    }

    private void chargerMarcheGauche(String chemin) throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(72, 19), 150);
        this.animations[Projectile.GAUCHE] = animation;
    }

    private void chargerMarcheHaut(String chemin)throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(45, 19), 50);

        this.animations[Heros.AVANCER_HAUT] = animation;
    }*/

    
    private void chargerAnimation(String chemin) throws SlickException {
        // chargement des animation static
        /*chargerStaticBas(chemin);
        chargerStaticGauche(chemin);
        chargerStaticHaut(chemin);
        chargerStaticDroite(chemin);
*/
        //chargement des animation de mouvement
        chargerMarcheBas(chemin);
        //chargerMarcheDroite(chemin);
        //chargerMarcheGauche(chemin);
        //chargerMarcheHaut(chemin);
        
       /* attaquerDevantBas(chemin);
        attaquerDevantHaut(chemin);
        attaquerDevantGauche(chemin);
        attaquerDevantDroite(chemin);*/
        
    }
    
    public void render(GameContainer container, Graphics g)  {
    	float x = this.projectile.getPosX();
    	float y = this.projectile.getPosY();
        g.drawAnimation(animations[this.projectile.getDirectionActu()],(int)x,(int)y);
    }

    public String toString() {
    	return this.projectile.getPosX() + "  " + this.projectile.getPosY();
    }
}
