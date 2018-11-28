package model.Item;

import org.newdawn.slick.geom.Rectangle;

public class Epee extends Item{
    
    public Epee(int posX, int posY) {
        super(posX, posY);
        setRamassable(true);
        boxCollider = new Rectangle(posX+9,posY+8, 14,15);
    }
}
