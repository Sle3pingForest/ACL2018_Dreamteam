package vues.VueMonstres;

import java.util.Observable;

import model.personnages.Heros;
import model.personnages.monstres.Orc;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class VueOrc extends VueMonstres {

	private final static  String CHEMIN_ORC = "main/resources/Personnages/Monstres/Orc_reduc.png";



	// largeur orc 30 / hauteur orc 55

	public VueOrc(Orc orc) throws SlickException{
		super(orc);  
		this.chargerAnimation(CHEMIN_ORC, orc.getLARGEUR_SPRITE(), orc.getHAUTEUR_SPRITE());
	}



}
