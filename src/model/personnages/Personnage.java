package model.personnages;

import model.Item.Item;

import java.io.Serializable;
import java.util.Observable;

public abstract class Personnage extends Observable implements Serializable{


    protected String nom;
	protected float x ,y;
    protected int vertical = 0;
    protected int horizontal = 0;
    //Les Direction


    //Position static
    public final static int BAS = 0;
    public final static int DROITE = 1;
    public final static int HAUT = 2;
    public final static int GAUCHE = 3;

    // Avancer
    public final static int AVANCER_BAS = 4;
    public final static int AVANCER_DROITE = 5;
    public final static int AVANCER_HAUT = 6;
    public final static int AVANCER_GAUCHE = 7;

    public final static int ATTAQUER_BAS = 8;
    public final static int ATTAQUER_HAUT = 9;
    public final static int ATTAQUER_GAUCHE = 10;
    public final static int ATTAQUER_DROITE = 11;

    public final static int MORT = 12;
    public final static int MORT_BAS = 13;
    public final static int MORT_HAUT = 14;
    public final static int MORT_DROITE = 15;
    public final static int MORT_GAUCHE = 16;

    public final static  float VITESSE = 0.2f;
    public final static int LARGEUR_SPRITE = 30;



    protected int directionActu = BAS;
    
	// sert a enlever les collisions pour tester plus facilement
	protected boolean collision = true;
    
	protected int pointVie;
	protected int defense, attaque, vitesse;
	public Personnage(float x, float y) {
		this.pointVie = 1; 
		this.attaque = 1;
		this.defense = 0;
		this.x = x;
		this.y = y;
		this.vitesse = 1;
	}

    public void goDroite(){
        horizontal = 1;
        if(vertical != 0) {
            if (directionActu != AVANCER_HAUT && directionActu != AVANCER_BAS) {
                directionActu = AVANCER_DROITE;
            }
        }else{
            directionActu = AVANCER_DROITE;
        }
    }

    public void goGauche(){
        horizontal = -1;
        if(vertical != 0) {
            if (directionActu != AVANCER_HAUT && directionActu != AVANCER_BAS) {
                directionActu = AVANCER_GAUCHE;
            }
        }else{
            directionActu = AVANCER_GAUCHE;
        }
    }
    public void goBas(){
        vertical = 1;
        if(horizontal != 0) {
            if (directionActu != AVANCER_GAUCHE && directionActu != AVANCER_DROITE) {
                directionActu = AVANCER_BAS;
            }
        }else{
            directionActu = AVANCER_BAS;
        }
    }
    public void goHaut(){

        vertical = -1;
        if(horizontal != 0) {
            if (directionActu != AVANCER_GAUCHE && directionActu != AVANCER_DROITE) {
                directionActu = AVANCER_HAUT;
            }
        }else{
            directionActu = AVANCER_HAUT;
        }
    }

    public void arretGauche(){

        if(horizontal == -1) {
            horizontal = 0;

            if(vertical == 0){
                directionActu = GAUCHE;
            }else  if(vertical == -1){
                directionActu = AVANCER_HAUT;
            }else{
                directionActu = AVANCER_BAS;
            }
        }
    }

    public  void arretDroite(){

        if(horizontal == 1) {
            horizontal = 0;

            if(vertical == 0){
                directionActu = DROITE;
            }else  if(vertical == -1){
                directionActu = AVANCER_HAUT;
            }else{
                directionActu = AVANCER_BAS;
            }
        }
    }

    public void arretBas(){

        if(vertical == 1) {
            vertical = 0;

            if(horizontal == 0){
                directionActu = BAS;
            }else  if(horizontal == -1){
                directionActu = AVANCER_GAUCHE;
            }else{
                directionActu = AVANCER_DROITE;
            }
        }
    }
    
    public void attaquer(){
    	if(directionActu == BAS){
    		directionActu = ATTAQUER_BAS;
    	}
    	else if(directionActu == HAUT){
    		directionActu = ATTAQUER_HAUT;
    	}
    	else if(directionActu == GAUCHE){
    		directionActu = ATTAQUER_GAUCHE;
    		
    	}
    	else if(directionActu == DROITE){
    		directionActu = ATTAQUER_DROITE;
    	}
    }
    
    public void mort(){
    
    	if(directionActu == ATTAQUER_BAS){
    		directionActu = MORT_BAS;
		}
    	else if(directionActu == ATTAQUER_HAUT){
			directionActu = MORT_HAUT;
		}
		else if(directionActu == ATTAQUER_GAUCHE){
			directionActu = MORT_GAUCHE;
			
		}
		else if(directionActu == ATTAQUER_DROITE){
			directionActu = MORT_DROITE;
		}
    }
    
    public void mortMonstres(){
		directionActu = MORT;
    }
    
    public void attaquerStop(){
        directionActu = BAS;
   }

    public void arretHaut(){

        if(vertical == -1) {
            vertical = 0;

            if(horizontal == 0){
                directionActu = HAUT;
            }else  if(horizontal == -1){
                directionActu = AVANCER_GAUCHE;
            }else{
                directionActu = AVANCER_DROITE;
            }
        }
    }
    
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    public int getVertical(){
    	return vertical;
    }
	
    public int getHorizontal(){
    	return horizontal;
    }
	public int getPointVie() {
		return pointVie;
	}
	public int getDefense() {
		return defense;
	}
	public int getAttaque() {
		return attaque;
	}
	public int getVitesse() {
		return vitesse;
	}

    public void ajouterAInventaire(Item i){}

    public void setDirectionActu(int directionActu) {
        this.directionActu = directionActu;
    }
    public int getDirectionActu(){
        return directionActu;
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }

	public void setVertical(int vertical) {
		this.vertical = vertical;
	}

	public void setHorizontal(int horizontal) {
		this.horizontal = horizontal;
	}
	
	public boolean getCollision(){
		return collision;
	}
	
	public void setCollision(boolean b){
		collision = b;
	}

    public String getNom(){
        return this.nom;
    }
    
    public void setPointVie(int i){
    	if(i >= defense){
        	this.pointVie = this.pointVie- (i - defense);
    	}
    	else{
    		this.pointVie = this.pointVie;
    	}
    }

}
