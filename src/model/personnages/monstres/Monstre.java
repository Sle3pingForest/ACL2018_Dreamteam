package model.personnages.monstres;

import model.personnages.Personnage;

public abstract class Monstre extends Personnage {
	
	public Monstre(int x, int y){
		super(x,y);
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}

}
