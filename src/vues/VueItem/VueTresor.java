package vues.VueItem;

import model.Item.Tresor;
import model.Labyrinthe;
import model.personnages.Heros;
import org.newdawn.slick.*;

public class VueTresor extends VueItem {

    private final static  String CHEMIN_TRESOR= "main/resources/Items/coffre/coffre.png";


    public VueTresor(Tresor coffre) throws SlickException {
        super(coffre);
        animations = new Animation[3];
        largeur_Sprite = 25;
        chargerCoffreFermer(CHEMIN_TRESOR);
        chargerCoffreQuiSOuvre(CHEMIN_TRESOR);
        chargerCoffreOuver(CHEMIN_TRESOR);
    }

    private void chargerCoffreFermer(String chemin) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur_Sprite, 30);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 0), 500);
        this.animations[0] = animation;
    }

    private void chargerCoffreQuiSOuvre(String chemin) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur_Sprite, 30);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 0), 50);
        animation.addFrame(spriteSheet.getSprite(1, 0), 50);
        animation.addFrame(spriteSheet.getSprite(2, 0), 50);
        this.animations[1] = animation;
    }

    private void chargerCoffreOuver(String chemin) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur_Sprite, 30);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(2, 0), 50);
        this.animations[2] = animation;
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        if(animationEnCour == 0 && !item.isRamasser()){
        	animationEnCour = 0;
        }
        else if(animationEnCour == 1){
                animationEnCour = 2;
        }
        else if(animationEnCour == 0 && item.isRamasser()){
            animationEnCour = 1;
        }
        float x = item.getPosX();
        float y = item.getPosY();
        g.drawAnimation(animations[animationEnCour],(int)(x+(Labyrinthe.LARGEUR_MUR-largeur_Sprite)/2),(int)y);
    }
}
