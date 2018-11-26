package model.personnages.monstres;

import org.newdawn.slick.geom.Rectangle;

public class Soldat extends Monstre{



	public Soldat(int x, int y) {
		super(x, y);
		largeur = 17;
		hauteur = 25;
		decalage_largeur = 15;
		decalage_hauteur = 15;
		SPRITE_HAUTEUR = 41;
		SPRITE_LARGEUR = 32;
		this.attaque = 1;
		this.pointVie = 1;
		this.nom ="Soldat";
		boxCollider = new Rectangle(x+decalage_largeur,y+decalage_hauteur,largeur-decalage_largeur,hauteur-decalage_hauteur);

	}



	
	
	
}
