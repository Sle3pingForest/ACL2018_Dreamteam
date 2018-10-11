package model.personnages.monstres;

public class FabriqueMonstre {

	public Monstre creerMonstres(String type, int x, int y) {
		Monstre m = null;
		if(type.equals("Orc")){
			m = new Orc(x,y);
		}
		if(type.equals("Dragon")){
			m = new Dragon(x,y);
		}
		return m;
	}
	
}
