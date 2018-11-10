package model.personnages.monstres;

public class Dragon extends Monstre{

	public Dragon(int x, int y) {
		super(x, y);
		this.attaque = 10;
		this.pointVie = 10;
		this.defense = 5;
		this.nom="dragon";
	}

	public String getNom(){
		return this.nom;
	}
	

}
