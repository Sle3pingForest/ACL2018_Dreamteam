package vues.VueItem;

import model.Item.Tresor;
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
        chargerCoffreOuvere(CHEMIN_TRESOR);
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

    private void chargerCoffreOuvere(String chemin) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet(chemin, largeur_Sprite, 30);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(2, 0), 50);
        this.animations[2] = animation;
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        super.render(container, g);
        if(animationEnCour == 1){
            if(animations[animationEnCour].getFrame() == 2){
                animationEnCour = 2;
            }
        }
        if(animationEnCour == 0 && item.isRamasser()){
            animationEnCour = 1;
        }
    }
}
