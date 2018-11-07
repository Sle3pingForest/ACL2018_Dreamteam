package model.personnages.monstres;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import vues.VueHeros;
import vues.VueLabyrinthe;
import model.Labyrinthe;
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


	protected  int LARGEUR_SPRITE = 30;    
	protected  int HAUTEUR_SPRITE = 30;
	protected  int HAUTEUR = 30;
	protected  int LARGEUR = 30;



	// sert a enlever les collisions pour tester plus facilement
	private boolean check = true;

	private int directionActu = BAS_M;
	private boolean collision = false;

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public Monstre(int x, int y){
		super(x,y);
	}

	public String getNom(){
		return this.nom;
	}

	public int getLARGEUR_SPRITE() {
		return LARGEUR_SPRITE;
	}

	public int getHAUTEUR_SPRITE() {
		return HAUTEUR_SPRITE;
	}

	public int getHAUTEUR() {
		return HAUTEUR;
	}

	public int getLARGEUR() {
		return LARGEUR;
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
			if (this.check) {

				int direction = 0;
				boolean chec = false;
				// si le monstre est deja en deplacement, continue 
				// vers la meme direction si il n y a pas de mur
				if (directionActu == 4) {
					//bas
					if(!collisionBas(futureX, futureY)) {
						direction = 4; 
						chec = true;
					}
					else chec = false;
				}
				else if (directionActu == 5) {
					//droite
					if(!collisionDroite(futureX, futureY)) {
						direction = 1;
						chec = true;
					}
					else chec = false;
				}
				else if (directionActu == 6) {
					// haut
					if(!collisionHaut(futureX, futureY)) {
						direction = 2;
						chec = true;
					}
					else chec = false;
				} 
				else if (directionActu == 7) {
					// gauche
					if (!collisionGauche(futureX, futureY)) {
						direction = 3;
						chec = true;
					}
					else chec = false;
				}

				// sinon cherche un nouveau chemin
				if (!chec) {
					
					ArrayList<Integer> cheminValide = new ArrayList<>();
					//cheminValide.add(0);
					if(!collisionDroite( futureX, futureY)){
						cheminValide.add(1);
					}
					if(!collisionHaut( futureX, futureY)){
						cheminValide.add(2);
					}
					if(!collisionGauche( futureX, futureY)){
						cheminValide.add(3);
					}
					if(!collisionBas( futureX, futureY)){
						cheminValide.add(4);
					}

					Random r = new Random();
					if ( cheminValide.size() > 0) {
						int rd =  r.nextInt(cheminValide.size());
						direction = cheminValide.get(rd);
					}
				}

				int dirActu = getDirectionActu();
				if (direction == 0 && dirActu > 3) {
					setDirectionActu(dirActu - 4);
				}
				if(direction == 1 ){
					goDroite();
					x = futureX;
					vertical = 0;
					//arretDroite();
				}
				if(direction == 2 ){
					goHaut();
					y = futureY;
					horizontal = 0;
					//arretHaut();
				}

				if(direction == 3 ){
					goGauche();
					x = futureX;
					vertical = 0;
					//arretGauche();
				}
				if (direction == 4 ){
					goBas();
					y = futureY;
					horizontal = 0;
					//arretBas();
				}
				else {
					x = futureX;
					y = futureY;
				}
			}


		}
	}

	private boolean collisionHaut(float futureX,float futureY) throws SlickException {

		VueLabyrinthe lab =  VueLabyrinthe.getInstance();
		Mur[][] mur = lab.getLab().getTabMur();

		int diffX = (getLARGEUR_SPRITE() - getLARGEUR() ) / 2;
		int diffY = (getHAUTEUR() );
		int xCaseFuture = (int)((futureX+ diffX )/VueLabyrinthe.LARGEUR_MUR);
		int yCaseFuture = (int)((futureY+ diffY )/VueLabyrinthe.HAUTEUR_MUR);

		if(horizontal == -1){
			xCaseFuture = (int)((futureX+6-2)/VueLabyrinthe.LARGEUR_MUR);
		}
		if(horizontal == 1){
			xCaseFuture = (int)((futureX+6+2)/VueLabyrinthe.LARGEUR_MUR);
		}

		if(mur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		xCaseFuture = (int)((futureX + diffX + getLARGEUR())/VueLabyrinthe.LARGEUR_MUR);

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

	public void setDirectionActu(int directionActu) {
		this.directionActu = directionActu;
	}

}
