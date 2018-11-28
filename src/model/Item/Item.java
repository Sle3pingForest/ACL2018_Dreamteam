package model.Item;

import model.personnages.Heros;
import org.newdawn.slick.geom.Rectangle;

public abstract class Item {

    private int posX;
    private int posY;
    private boolean ramasser;
    private boolean ramassable;
    protected Rectangle boxCollider;

    public Item(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        ramasser = false;
        ramassable = true;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isRamasser() {
        return ramasser;
    }

    public void ramasser(){
        ramasser = true;
    }

    public boolean isRamassable() {
        return ramassable;
    }

    public void setRamassable(boolean ramassable) {
        this.ramassable = ramassable;
    }

    public void blesser(Heros heros){
        heros.perdrePointDeVie(1);
    }

    public Rectangle getBoxCollider() {
        return boxCollider;
    }
}
