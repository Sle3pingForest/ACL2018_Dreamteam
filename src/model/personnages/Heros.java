package model.personnages;

import model.Item.Item;
import model.Item.Tresor;
import model.mur.Mur;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;


public class Heros extends Personnage {

    private String nom;

    private Tresor tresorDeMap = null;
    private ArrayList<Item> inventaire;


    public Heros(float x, float y, String nom){
    	super(x,y);
    	this.pointVie = 5;
    	this.nom = nom;
    	this.attaque = 2;
    	this.defense = 0;
    	inventaire = new ArrayList<Item>();
    }
    
    public void charge(Heros h) {
    	this.x = h.getX();
    	this.y = h.getY();
    	this.pointVie = h.getPointVie();
    	this.nom = h.getNom();
    	this.attaque = h.getAttaque();
    	this.defense = h.getDefense();
    	inventaire = h.getInventaire();
    }

    public ArrayList<Item> getInventaire() {
		return inventaire;
	}

	public String toString(){
        String donnee = "Le Heros est en  x :"+x+" , +y : "+y;

        return donnee;
    }
    

    public String getNom() {
		return nom;
	}
    
    

    
    public int getDirectionActu(){
    	return directionActu;
    }
    


    public Tresor getTresorDeMap(){
        return  tresorDeMap;
    }

    public void ajouterAInventaire(Item i){
        if(!i.getClass().getName().equals("model.Item.Tresor")) {
            inventaire.add(i);
        }else{
            tresorDeMap = (Tresor)i;
        }
    }
}
