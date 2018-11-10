package model.personnages.monstres;

import java.io.Serializable;

public class FabriqueMonstre implements Serializable{

	//Les Type
	public final static int Soldat = 0;
	public final static int DRAGON = 1;
	
	public Monstre creerMonstres(int type, int x, int y) {
		Monstre m = null;
		switch(type){
			case Soldat:m = new Soldat(x,y);break;
			case DRAGON: m = new Dragon(x,y);break;
		}
		return m;
	}
	
}
