package vues;

import model.mur.Mur;
import model.personnages.Heros;

import java.io.Serializable;

import org.newdawn.slick.*;
public class VueHeros implements Serializable{

    public final static int VERT = 0;
    public final static int ROUGE = 1;
    public final static int BLEU = 2;
    public final static int VIOLET = 3;

    private final static  String CHEMIN_VERT = "main/resources/Personnages/Heros/Vert.png";
    private final static  String CHEMIN_ROUGE = "main/resources/Personnages/Heros/Rouge.png";
    private final static  String CHEMIN_BLEU = "main/resources/Personnages/Heros/Bleu.png";
    private final static  String CHEMIN_VIOLET = "main/resources/Personnages/Heros/Violet.png";


    private Animation[] animations = new Animation[20];
    
    private Heros heros;

    public VueHeros(int choix,Heros heros) throws SlickException {
    	this.heros = heros;
        switch(choix){
            case VERT:
                chargerAnimationVert();
                break;
            case ROUGE:
                chargerAnimationRouge();
                break;
            case BLEU:
                chargerAnimationBleu();
                break;
            case VIOLET:
                chargerAnimationViolet();
                break;
                default: break;

        }
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
        animation.addFrame(spriteSheet.getSprite(3, 0), 50);
        animation.addFrame(spriteSheet.getSprite(4, 0), 50);
        animation.addFrame(spriteSheet.getSprite(5, 0), 50);
        animation.addFrame(spriteSheet.getSprite(6, 0), 50);
        animation.addFrame(spriteSheet.getSprite(7, 0), 50);
        animation.addFrame(spriteSheet.getSprite(8, 0), 50);
        animation.addFrame(spriteSheet.getSprite(9, 0), 50);
        animation.addFrame(spriteSheet.getSprite(10, 0), 50);
        animation.addFrame(spriteSheet.getSprite(11, 0), 50);
        animation.addFrame(spriteSheet.getSprite(12, 0), 50);
        this.animations[Heros.AVANCER_BAS] = animation;
    }

    private void chargerMarcheDroite(String chemin) throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
        Animation animation = new Animation();
        Image img = spriteSheet.getSprite(3, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(4, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(5, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(6, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(7, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(8, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(9, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(10, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(11, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(12, 51);
        img = img.getFlippedCopy(true,false);
        animation.addFrame(img, 100);
        this.animations[Heros.AVANCER_DROITE] = animation;
    }

    private void chargerMarcheGauche(String chemin) throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
        Animation animation = new Animation();
        Image img = spriteSheet.getSprite(3, 51);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(4, 51);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(5, 51);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(6, 51);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(7, 51);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(8, 51);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(9, 51);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(10, 51);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(11, 51);
        animation.addFrame(img, 100);
        img = spriteSheet.getSprite(12, 51);
        animation.addFrame(img, 100);
        this.animations[Heros.AVANCER_GAUCHE] = animation;
    }


    private void chargerMarcheHaut(String chemin)throws SlickException{
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(3, 26), 50);
        animation.addFrame(spriteSheet.getSprite(4, 26), 50);
        animation.addFrame(spriteSheet.getSprite(5, 26), 50);
        animation.addFrame(spriteSheet.getSprite(6, 26), 50);
        animation.addFrame(spriteSheet.getSprite(7, 26), 50);
        animation.addFrame(spriteSheet.getSprite(8, 26), 50);
        animation.addFrame(spriteSheet.getSprite(9, 26), 50);
        animation.addFrame(spriteSheet.getSprite(10, 26), 50);
        animation.addFrame(spriteSheet.getSprite(11, 26), 50);
        animation.addFrame(spriteSheet.getSprite(12, 26), 50);

        this.animations[Heros.AVANCER_HAUT] = animation;
    }

    private void chargerAnimationVert() throws SlickException {
        // chargement des animation static
        chargerStaticBas(CHEMIN_VERT);
        chargerStaticGauche(CHEMIN_VERT);
        chargerStaticHaut(CHEMIN_VERT);
        chargerStaticDroite(CHEMIN_VERT);

        //chargement des animation de mouvement
        chargerMarcheBas(CHEMIN_VERT);
        chargerMarcheDroite(CHEMIN_VERT);
        chargerMarcheGauche(CHEMIN_VERT);
        chargerMarcheHaut(CHEMIN_VERT);
    }

    private void chargerAnimationRouge() throws SlickException {
        // chargement des animation static
        chargerStaticBas(CHEMIN_ROUGE);
        chargerStaticGauche(CHEMIN_ROUGE);
        chargerStaticHaut(CHEMIN_ROUGE);
        chargerStaticDroite(CHEMIN_ROUGE);

        //chargement des animation de mouvement
        chargerMarcheBas(CHEMIN_ROUGE);
        chargerMarcheDroite(CHEMIN_ROUGE);
        chargerMarcheGauche(CHEMIN_ROUGE);
        chargerMarcheHaut(CHEMIN_ROUGE);
    }

    private void chargerAnimationBleu() throws SlickException {
        // chargement des animation static
        chargerStaticBas(CHEMIN_BLEU);
        chargerStaticGauche(CHEMIN_BLEU);
        chargerStaticHaut(CHEMIN_BLEU);
        chargerStaticDroite(CHEMIN_BLEU);

        //chargement des animation de mouvement
        chargerMarcheBas(CHEMIN_BLEU);
        chargerMarcheDroite(CHEMIN_BLEU);
        chargerMarcheGauche(CHEMIN_BLEU);
        chargerMarcheHaut(CHEMIN_BLEU);
    }    	

    private void chargerAnimationViolet() throws SlickException {
        // chargement des animation static
        chargerStaticBas(CHEMIN_VIOLET);
        chargerStaticGauche(CHEMIN_VIOLET);
        chargerStaticHaut(CHEMIN_VIOLET);
        chargerStaticDroite(CHEMIN_VIOLET);

        //chargement des animation de mouvement
        chargerMarcheBas(CHEMIN_VIOLET);
        chargerMarcheDroite(CHEMIN_VIOLET);
        chargerMarcheGauche(CHEMIN_VIOLET);
        chargerMarcheHaut(CHEMIN_VIOLET);
    }

    public void render(GameContainer container, Graphics g)  {
    	float x = heros.getX();
    	float y = heros.getY();
        g.drawAnimation(animations[heros.getDirectionActu()],(int)x,(int)y);
    }

    public String tt() {
    	return heros.getX() + "  " + heros.getY();
    }


    
    


}
