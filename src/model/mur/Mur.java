package model.mur;

import java.io.Serializable;

public class Mur implements Serializable{

    private int posX,posY;

    public Mur(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }


}
