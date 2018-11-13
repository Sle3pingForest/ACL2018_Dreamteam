package model.Item;

public abstract class Item {

    private int posX;
    private int posY;

    private boolean ramasser;

    public Item(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
        ramasser = false;
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
}