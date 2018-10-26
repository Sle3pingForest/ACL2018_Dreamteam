package model.personnages.monstres;

public class FabriqueMonstre {

	//Les Type
	public final static int ORC = 0;
	public final static int DRAGON = 1;
	
	public Monstre creerMonstres(int type, int x, int y) {
		Monstre m = null;
		switch(type){
			case ORC:m = new Orc(x,y);break;
			case DRAGON: m = new Dragon(x,y);break;
		}
		return m;
	}
	
}
