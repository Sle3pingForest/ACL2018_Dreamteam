package vues.VueItem;

import model.Item.Piege;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class VuePiege extends VueItem{

    private final static String CHEMIN_PIEGE="main/resources/Items/piege/piege.png";

    public VuePiege(Piege piege) throws SlickException {
        super(piege);
        animations = new Animation[1];
        largeur_Sprite = 32;
        afficherPiege(CHEMIN_PIEGE);
    }

    private void afficherPiege(String cheminPiege) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet(cheminPiege, largeur_Sprite, 32 );
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 0), 300);
        this.animations[0] = animation;
    }
}