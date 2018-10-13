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

	public void deplacement(int a){
		if(a == 1 ){
			goDroite();
		}
		if(a == 2){
			goHaut();
		}
		if(a == 3){
			goGauche();
		}
		else{
			goBas();
		}
	}

}
