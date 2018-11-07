package model.personnages.monstres;

public class Orc extends Monstre{
	


	public Orc(int x, int y) {
		super(x, y);
		this.attaque = 1;
		this.pointVie = 3;
		this.nom ="orc";
		LARGEUR_SPRITE = 30;    
		HAUTEUR_SPRITE = 30;
		HAUTEUR = 27;
		LARGEUR = 14;
	}
	
	public String getNom(){
		return this.nom;
	}
	
}
