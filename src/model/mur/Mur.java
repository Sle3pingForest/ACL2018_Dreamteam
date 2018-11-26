package model.mur;

import org.newdawn.slick.geom.Rectangle;

import java.io.Serializable;

public class Mur implements Serializable{

    private int posX,posY;
    private final static int LARGEUR=32;
    private final static int HAUTEUR=31;
    private Rectangle boxCollider;

    public Mur(int x, int y){
        this.posX = x;
        this.posY = y;
        boxCollider = new Rectangle(x*LARGEUR,y*HAUTEUR,LARGEUR,HAUTEUR);
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public Rectangle getBoxCollider(){
        return boxCollider;
    }

    public void setPosX(int x){
        this.posX = x;
        boxCollider.setX(x*LARGEUR);
    }

    public void setPosY(int y){
        this.posY = y;
        boxCollider.setY(y*HAUTEUR);
    }


}
