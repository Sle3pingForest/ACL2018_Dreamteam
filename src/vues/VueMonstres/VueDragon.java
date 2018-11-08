package vues.VueMonstres;

import model.personnages.Personnage;
import model.personnages.monstres.Dragon;

import org.newdawn.slick.SlickException;


public class VueDragon extends VueMonstres {
	private final static  String CHEMIN_DRAGON = "main/resources/Personnages/Monstres/Orc_reduc.png";


	public VueDragon(Dragon dragon) throws SlickException{
		super(dragon);  
		this.chargerAnimation(CHEMIN_DRAGON, Personnage.LARGEUR_SPRITE, Personnage.LARGEUR_SPRITE);
	}

}
