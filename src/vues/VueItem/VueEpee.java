package vues.VueItem;

import model.Item.Epee;
import org.newdawn.slick.*;

public class VueEpee extends VueItem {

    private final static String CHEMIN_EPEE="main/resources/Items/epee/epee.png";

    public VueEpee(Epee epee) throws SlickException {
        super(epee);
        animations = new Animation[1];
        largeur_Sprite = 45;
        afficherEpee(CHEMIN_EPEE);
    }

    private void afficherEpee(String cheminEpee) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet(cheminEpee, largeur_Sprite, 30 );
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 0), 300);
        this.animations[0] = animation;
    }
}
