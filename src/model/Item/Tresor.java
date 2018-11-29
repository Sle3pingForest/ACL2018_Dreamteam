package model.Item;

import org.newdawn.slick.geom.Rectangle;

public class Tresor extends Item{

    public Tresor(int posX,int posY,Item item){
        super(posX,posY);
        setRamassable(true);
        boxCollider = new Rectangle(posX+1,posY+9,22,19);
    }
}
