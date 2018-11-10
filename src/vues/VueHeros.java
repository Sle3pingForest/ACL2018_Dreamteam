package vues;

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
                chargerAnimation(CHEMIN_VERT);
                break;
            case ROUGE:
                chargerAnimation(CHEMIN_ROUGE);
                break;
            case BLEU:
                chargerAnimation(CHEMIN_BLEU);
                break;
            case VIOLET:
                chargerAnimation(CHEMIN_VIOLET);
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
    
    private void attaquerDevantBas(String chemin ) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 40, 30);
       Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(3, 12), 100);
        animation.addFrame(spriteSheet.getSprite(4, 12), 100);
        animation.addFrame(spriteSheet.getSprite(5,12), 100);
        animation.addFrame(spriteSheet.getSprite(6,12), 100);
        animation.addFrame(spriteSheet.getSprite(7,12), 100);
        animation.addFrame(spriteSheet.getSprite(8,12), 100);
        animation.addFrame(spriteSheet.getSprite(9,12), 100);
        this.animations[Heros.ATTAQUER_BAS] = animation;
    }

    private void attaquerDevantHaut(String chemin ) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 40, 30);
       Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(3, 38), 100);
        animation.addFrame(spriteSheet.getSprite(4, 38), 100);
        animation.addFrame(spriteSheet.getSprite(5,38), 100);
        animation.addFrame(spriteSheet.getSprite(6,38), 100);
        animation.addFrame(spriteSheet.getSprite(7,38), 100);
        animation.addFrame(spriteSheet.getSprite(8,38), 100);
        animation.addFrame(spriteSheet.getSprite(9,38), 100);
        this.animations[Heros.ATTAQUER_HAUT] = animation;
    }
    
    private void attaquerDevantGauche(String chemin ) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 40, 30);
       Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(3, 64), 100);
        animation.addFrame(spriteSheet.getSprite(4, 64), 100);
        animation.addFrame(spriteSheet.getSprite(5,64), 100);
        animation.addFrame(spriteSheet.getSprite(6,64), 100);
        animation.addFrame(spriteSheet.getSprite(7,64), 100);
        animation.addFrame(spriteSheet.getSprite(8,64), 100);
        animation.addFrame(spriteSheet.getSprite(9,64), 100);
        this.animations[Heros.ATTAQUER_GAUCHE] = animation;
    }
    
    private void attaquerDevantDroite(String chemin ) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet(chemin, 40, 30);
       Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(3, 64).getFlippedCopy(true,false), 100);
        
        animation.addFrame(spriteSheet.getSprite(4, 64).getFlippedCopy(true,false), 100);
        
        animation.addFrame(spriteSheet.getSprite(5, 64).getFlippedCopy(true,false), 100);
       
        animation.addFrame( spriteSheet.getSprite(6, 64).getFlippedCopy(true,false), 100);
        
        animation.addFrame(spriteSheet.getSprite(7, 64).getFlippedCopy(true,false), 100);
        
        animation.addFrame(spriteSheet.getSprite(8, 64).getFlippedCopy(true,false), 100);
       
        animation.addFrame( spriteSheet.getSprite(9, 64).getFlippedCopy(true,false), 100);
        this.animations[Heros.ATTAQUER_DROITE] = animation;
    }
    
    private void chargerAnimation(String chemin) throws SlickException {
        // chargement des animation static
        chargerStaticBas(chemin);
        chargerStaticGauche(chemin);
        chargerStaticHaut(chemin);
        chargerStaticDroite(chemin);

        //chargement des animation de mouvement
        chargerMarcheBas(chemin);
        chargerMarcheDroite(chemin);
        chargerMarcheGauche(chemin);
        chargerMarcheHaut(chemin);
        
        attaquerDevantBas(chemin);
        attaquerDevantHaut(chemin);
        attaquerDevantGauche(chemin);
        attaquerDevantDroite(chemin);
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
