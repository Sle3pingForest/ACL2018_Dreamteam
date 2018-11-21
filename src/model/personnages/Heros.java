package model.personnages;

import model.Item.Item;
import model.Item.Projectile;
import model.Item.Piege;
import model.Item.Ramassable;
import model.Item.Tresor;
import model.mur.Mur;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;


public class Heros extends Personnage {

    private Tresor tresorDeMap = null;
    private ArrayList<Item> inventaire;
    private Projectile projectile;
    private ArrayList<Projectile> lprojectile;

    private int pointVie;


    protected int tailleInventaire = 10;


    public Heros(float x, float y, String nom){
    	super(x,y);
    	this.pointVie = 5;
    	this.nom = nom;
    	this.attaque = 3;
    	this.defense = 0;
    	inventaire = new ArrayList<Item>();
    	tailleInventaire = 10;
    	this.projectile = new Projectile(x,y);
    	lprojectile = new ArrayList<>();
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

    public int getTailleInventaire() {
        return tailleInventaire;
    }

    public void setTailleInventaire(int tailleInventaire) {
        this.tailleInventaire = tailleInventaire;
    }
    
    public int getDirectionActu(){
    	return directionActu;
    }
    
    public Tresor getTresorDeMap(){
        return  tresorDeMap;
    }

    
    
    public Projectile getProjectile() {
		return projectile;
	}

	public void setProjectile(Projectile projectile) {
		this.projectile = projectile;
	}

	 public void tirer(){
		 this.lprojectile.add(new Projectile(this.x, this.y));
		 if(directionActu == BAS){
	   		 	this.lprojectile.get(lprojectile.size() -1).deplacer(ATTAQUER_BAS);
	    	}
	    	else if(directionActu == HAUT){
	    		this.lprojectile.get(lprojectile.size() -1).deplacer(ATTAQUER_HAUT);
	    	}
	    	else if(directionActu == GAUCHE){
	    		this.lprojectile.get(lprojectile.size() -1).deplacer(ATTAQUER_GAUCHE);
	    	}
	    	else if(directionActu == DROITE){
	    		this.lprojectile.get(lprojectile.size() -1).deplacer(ATTAQUER_DROITE);
	    	}
		 /*this.projectile.setX(this.x);
		 this.projectile.setY(this.y);
		 this.projectile.setxInit(this.x);
		 this.projectile.setyInit(this.y);
	    	if(directionActu == BAS){
	   		 	this.projectile.deplacer(ATTAQUER_BAS);
	    	}
	    	else if(directionActu == HAUT){
		   		 this.projectile.deplacer(ATTAQUER_HAUT);
	    	}
	    	else if(directionActu == GAUCHE){
		   		 this.projectile.deplacer(ATTAQUER_GAUCHE);
	    		
	    	}
	    	else if(directionActu == DROITE){
		   		 this.projectile.deplacer(ATTAQUER_DROITE);
	    	}
	    	*/
	 }
	
	public ArrayList<Projectile> getLprojectile() {
		return lprojectile;
	}

	public void setLprojectile(ArrayList<Projectile> lprojectile) {
		this.lprojectile = lprojectile;
	}


    public int getPointVie() {
        return pointVie;
    }

    public void setPointVie(int pointVie) {
        this.pointVie = pointVie;
    }

    public void ajouterAInventaire(Item i){
        if(!i.getClass().getName().equals("model.Item.Tresor")) {
            if(!i.isRamasser() && i.isRamassable()) {
                if (inventaire.size() < tailleInventaire) {
                    inventaire.add(i);
                    i.ramasser();
                    System.out.println(inventaire);
                }
            }
            else if(i.getClass().getName().equals("model.Item.Piege")){
                if(!i.isRamasser()){
                    System.out.println("Avant" + this.pointVie);
                    i.blesser(this);
                    i.ramasser();
                    System.out.println("Après" + this.pointVie);
                }
            }
        }else{
            tresorDeMap = (Tresor)i;
        }
    }
}
