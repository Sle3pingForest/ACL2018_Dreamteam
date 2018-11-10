package vues.VueItem;

import model.Item.Item;
import model.Labyrinthe;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract  class VueItem {

    protected Item item;
    protected Animation[] animations;
    protected int animationEnCour = 0;
    protected int largeur_Sprite = 0;

    public VueItem(Item item){
        this.item = item;
    }

    public void render(GameContainer container, Graphics g) {
        float x = item.getPosX();
        float y = item.getPosY();
        g.drawAnimation(animations[animationEnCour],(int)(x+(Labyrinthe.LARGEUR_MUR-largeur_Sprite)/2),(int)y);
    }
}
