package model.personnages;

import java.io.Serializable;
import java.util.Observable;

public abstract class Personnage extends Observable implements Serializable{
	
    public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}

	protected float x ,y;
    protected int vertical = 0;
    protected int horizontal = 0;
    
	protected int pointVie;
	protected int defense, attaque, vitesse;
	public Personnage(float x, float y) {
		this.pointVie = 1; 
		this.attaque = 1;
		this.defense = 0;
		this.x = x;
		this.y = y;
		this.vitesse = 1;
		
	}
    
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    public int getVertical(){
    	return vertical;
    }
	
    public int getHorizontal(){
    	return horizontal;
    }
	public int getPointVie() {
		return pointVie;
	}
	public int getDefense() {
		return defense;
	}
	public int getAttaque() {
		return attaque;
	}
	public int getVitesse() {
		return vitesse;
	}

}
