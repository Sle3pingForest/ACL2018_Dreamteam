package model.Item;

public abstract class Item {

    private float posX;
    private float posY;

    private boolean ramasser;

    public Item(float posX,float posY){
        this.posX = posX;
        this.posY = posY;
        ramasser = false;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public boolean isRamasser() {
        return ramasser;
    }

    public void ramasser(){
        ramasser = true;
    }
}
