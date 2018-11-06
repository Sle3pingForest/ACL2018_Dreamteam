package model.personnages.monstres;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import vues.VueHeros;
import vues.VueLabyrinthe;
import model.Item.Item;
import model.mur.Mur;
import model.personnages.Heros;
import model.personnages.Personnage;

public abstract class Monstre extends Personnage {
	
	protected String nom;
    //Position static
    public final static int BAS_M = 0;

	public final static int DROITE_M = 1;
    public final static int HAUT_M = 2;
    public final static int GAUCHE_M = 3;

    // Avancer
    public final static int AVANCER_BAS_M = 4;
    public final static int AVANCER_DROITE_M = 5;
    public final static int AVANCER_HAUT_M = 6;
    public final static int AVANCER_GAUCHE_M = 7;

    
    public final static  float VITESSE_M = 0.05f;
    
    // sert a enlever les collisions pour tester plus facilement
    private boolean check = true;
    
    private int directionActu = BAS_M;
	public Monstre(int x, int y){
		super(x,y);
	}
	
	public String getNom(){
		return this.nom;
	}
	

    public void goDroite(){
        horizontal = 1;
        if(vertical != 0) {
            if (directionActu != AVANCER_HAUT_M && directionActu != AVANCER_BAS_M) {
                directionActu = AVANCER_DROITE_M;
            }
        }else{
            directionActu = AVANCER_DROITE_M;
        }
    }
    
    public void goGauche(){
        horizontal = -1;
        if(vertical != 0) {
            if (directionActu != AVANCER_HAUT_M && directionActu != AVANCER_BAS_M) {
                directionActu = AVANCER_GAUCHE_M;
            }
        }else{
            directionActu = AVANCER_GAUCHE_M;
        }
    }
    public void goBas(){
        vertical = 1;
        if(horizontal != 0) {
            if (directionActu != AVANCER_GAUCHE_M && directionActu != AVANCER_DROITE_M) {
                directionActu = AVANCER_BAS_M;
            }
        }else{
            directionActu = AVANCER_BAS_M;
        }
    }
    public void goHaut(){

        vertical = -1;
        if(horizontal != 0) {
            if (directionActu != AVANCER_GAUCHE_M && directionActu != AVANCER_DROITE_M) {
                directionActu = AVANCER_HAUT_M;
            }
        }else{
            directionActu = AVANCER_HAUT_M;
        }
    }

    public void arretGauche(){

        if(horizontal == -1) {
            horizontal = 0;

            if(vertical == 0){
                directionActu = GAUCHE_M;
            }else  if(vertical == -1){
                directionActu = AVANCER_HAUT_M;
            }else{
                directionActu = AVANCER_BAS_M;
            }
        }
    }

    public  void arretDroite(){

        if(horizontal == 1) {
            horizontal = 0;

            if(vertical == 0){
                directionActu = DROITE_M;
            }else  if(vertical == -1){
                directionActu = AVANCER_HAUT_M;
            }else{
                directionActu = AVANCER_BAS_M;
            }
        }
    }

    public void arretBas(){

        if(vertical == 1) {
            vertical = 0;

            if(horizontal == 0){
                directionActu = BAS_M;
            }else  if(horizontal == -1){
                directionActu = AVANCER_GAUCHE_M;
            }else{
                directionActu = AVANCER_DROITE_M;
            }
        }
    }

    public void arretHaut(){

        if(vertical == -1) {
            vertical = 0;

            if(horizontal == 0){
                directionActu = HAUT_M;
            }else  if(horizontal == -1){
                directionActu = AVANCER_GAUCHE_M;
            }else{
                directionActu = AVANCER_DROITE_M;
            }
        }
    }
    
    public int getDirectionActu(){
    	return directionActu;
    }
    
    public void update(GameContainer container, int delta) throws SlickException{
    	
    	VueLabyrinthe vueLab = VueLabyrinthe.getInstance();
        float vitesseActu = delta*Monstre.VITESSE_M;

        float futureX = x + horizontal * vitesseActu;
        float futureY = y + vertical * vitesseActu;
        if(futureX > 0 && futureX < vueLab.getLongeurCarte() - VueLabyrinthe.LARGEUR_MUR && futureY > 0 && futureY < vueLab.getHauteurCarte()-VueLabyrinthe.HAUTEUR_MUR){
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
        Item[][] lesItem = lab.getLab().getLesObjets();
		
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
        Item[][] lesItem = lab.getLab().getLesObjets();
		
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
