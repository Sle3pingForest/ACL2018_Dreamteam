package model.personnages;

import model.mur.Mur;

import java.io.Serializable;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import vues.VueHeros;
import vues.VueLabyrinthe;

public class Heros extends Personnage {

    private String nom;
    
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

    
    public final static  float VITESSE = 0.2f;
    
    // sert a enlever les collisions pour tester plus facilement
    private boolean check = true;
    
    private int directionActu = BAS;


    public Heros(float x, float y, String nom){
    	super(x,y);
    	this.pointVie = 5;
    	this.nom = nom;
    	this.attaque = 2;
    	this.defense = 0;
    }

    public String toString(){
        String donnee = "Le Heros est en  x :"+x+" , +y : "+y;

        return donnee;
    }
    

    public String getNom() {
		return nom;
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
    
    public int getDirectionActu(){
    	return directionActu;
    }
    
public void update(GameContainer container, int delta) throws SlickException{
    	
    	
        float vitesseActu = delta*Heros.VITESSE;

        float futureX = x + horizontal * vitesseActu;
        float futureY = y + vertical * vitesseActu;
        if (check) {
	        if(vertical == -1){
		        if(!collisionHaut( futureX, futureY)){
			        y = futureY;
		        }
	        }
	        if(vertical == 1){
		        if(!collisionBas( futureX, futureY)){
			        y = futureY;
		        }
	        }
	        
	        if(horizontal == -1){
		        if(!collisionGauche( futureX, futureY)){
			        x = futureX;
		        }
	        }
	        if(horizontal == 1){
		        if(!collisionDroite( futureX, futureY)){
			        x = futureX;
		        }
	        }
        } else {
        	x = futureX;
        	y = futureY;
        }

       
    }
    
    private boolean collisionHaut(float futureX,float futureY) throws SlickException {
    	
		VueLabyrinthe lab =  VueLabyrinthe.getInstance();
		Mur[][] mur = lab.getLab().getTabMur();
		
		int xCaseFuture = (int)((futureX+6)/VueLabyrinthe.LARGEUR_MUR);
		int yCaseFuture = (int)((futureY+19)/VueLabyrinthe.HAUTEUR_MUR);
		
		if(horizontal == -1){
			xCaseFuture = (int)((futureX+6-2)/VueLabyrinthe.LARGEUR_MUR);
		}
		if(horizontal == 1){
			xCaseFuture = (int)((futureX+6+2)/VueLabyrinthe.LARGEUR_MUR);
		}
		
		if(mur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		xCaseFuture = (int)((futureX-6 + VueHeros.LARGEUR_SPRITE)/VueLabyrinthe.LARGEUR_MUR);
		
		if(mur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		
		
    	return false;
    }
    
    private boolean collisionBas(float futureX,float futureY) throws SlickException {
    	
    	
		VueLabyrinthe lab =  VueLabyrinthe.getInstance();
		Mur[][] mur = lab.getLab().getTabMur();
		
		int xCaseFuture = (int)((futureX+6)/VueLabyrinthe.LARGEUR_MUR);
		int yCaseFuture = (int)((futureY-6)/VueLabyrinthe.HAUTEUR_MUR)+1;
		
		if(horizontal == -1){
			xCaseFuture = (int)((futureX+6+2)/VueLabyrinthe.LARGEUR_MUR);
		}
		if(horizontal == 1){
			xCaseFuture = (int)((futureX+6-2)/VueLabyrinthe.LARGEUR_MUR);
		}
		
		if(mur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		xCaseFuture = (int)((futureX-6 + VueHeros.LARGEUR_SPRITE)/VueLabyrinthe.LARGEUR_MUR);
		
		if(mur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		
		
    	return false;
    }
    
    private boolean collisionGauche(float futureX,float futureY) throws SlickException {
    	
		VueLabyrinthe lab =  VueLabyrinthe.getInstance();
		Mur[][] mur = lab.getLab().getTabMur();
		
		int xCaseFuture = (int)((futureX+6)/VueLabyrinthe.LARGEUR_MUR);
		int yCaseFuture = (int)((futureY+19)/VueLabyrinthe.HAUTEUR_MUR);
		
		if(vertical == -1){
			 xCaseFuture = (int)((futureX+6)/VueLabyrinthe.LARGEUR_MUR);
			 yCaseFuture = (int)((futureY+2+19)/VueLabyrinthe.HAUTEUR_MUR);
		}
		
		if(mur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
    	return false;
    }

	private boolean collisionDroite(float futureX,float futureY) throws SlickException {
		
		
		VueLabyrinthe lab =  VueLabyrinthe.getInstance();
		Mur[][] mur = lab.getLab().getTabMur();
		
		int xCaseFuture = (int)((futureX+6)/VueLabyrinthe.LARGEUR_MUR);
		int yCaseFuture = (int)((futureY+19)/VueLabyrinthe.HAUTEUR_MUR);
		
		if(vertical == -1){
			 xCaseFuture = (int)((futureX+6)/VueLabyrinthe.LARGEUR_MUR);
			 yCaseFuture = (int)((futureY+2+19)/VueLabyrinthe.HAUTEUR_MUR);
		}
		if(mur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		xCaseFuture = (int)((futureX-6 + VueHeros.LARGEUR_SPRITE)/VueLabyrinthe.LARGEUR_MUR);
		
		if(mur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		
		
		return false;
    }
}
