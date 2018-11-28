package model.Item;

import org.newdawn.slick.geom.Rectangle;

public class Piege extends Item {



    public Piege(int posX, int posY) {
        super(posX, posY);
        setRamassable(false);
        boxCollider = new Rectangle(posX+9,posY+8, 14,15);
    }


}
