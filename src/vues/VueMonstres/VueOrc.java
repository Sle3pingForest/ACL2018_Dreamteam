package vues.VueMonstres;

import java.util.Observable;

import model.personnages.Heros;
import model.personnages.monstres.Orc;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class VueOrc extends VueMonstres {
	
	 private final static  String CHEMIN_ORC = "main/resources/Personnages/Monstres/Orc.png";
	
	 
	 public final static int LARGEUR_SPRITE = 64;    
	 public final static int HAUTEUR_SPRITE = 64;
	 
	 public VueOrc(Orc orc) throws SlickException{
		 super(orc);
		 this.chargerAnimation(CHEMIN_ORC, LARGEUR_SPRITE, HAUTEUR_SPRITE);
	 }
	 
	 

}
