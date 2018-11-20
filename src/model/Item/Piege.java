package model.Item;

import model.personnages.Heros;

public class Piege extends Item {

    public Piege(int posX, int posY) {
        super(posX, posY);
    }

    public void blesser(Heros heros){
        heros.setPointVie(-1);
    }
}
