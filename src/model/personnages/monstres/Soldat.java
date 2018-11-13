package model.personnages.monstres;

public class Soldat extends Monstre{
	


	public Soldat(int x, int y) {
		super(x, y);
		this.attaque = 1;
		this.pointVie = 1;
		this.nom ="Soldat";
		LARGEUR_SPRITE = 30;
		HAUTEUR_SPRITE = 30;
		HAUTEUR = 27;
		LARGEUR = 14;
		collision = true;
	}
	
	
	
}
