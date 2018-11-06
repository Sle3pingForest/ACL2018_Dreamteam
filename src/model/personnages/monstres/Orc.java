package model.personnages.monstres;

public class Orc extends Monstre{
	
	

	public Orc(int x, int y) {
		super(x, y);
		this.attaque = 1;
		this.pointVie = 3;
		this.nom ="orc";
	}
	
	public String getNom(){
		return this.nom;
	}
	
}
