package model.personnages.monstres;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.SlickException;

import org.newdawn.slick.geom.Rectangle;
import model.Labyrinthe;
import model.mur.Mur;
import model.personnages.Personnage;

public abstract class Monstre extends Personnage {


	public final static  float VITESSE_M = 0.05f;

	protected  int LARGEUR_SPRITE = 30;    
	protected  int HAUTEUR_SPRITE = 30;
	protected  int HAUTEUR = 30;
	protected  int LARGEUR = 30;
	
	protected float posXPrecedent, posYprecedent;



	protected int pixelParcouruDepuisDernierChangement = 0 ;


	public Monstre(int x, int y){
		super(x,y);
		posXPrecedent = x;
		posYprecedent = y;
		goBas();
		boxCollider = new Rectangle(0,0,0,0);
	}

	public void directionAleatoire(){
		vertical = 0;
		horizontal = 0;
		Random r = new Random();
		int alea = r.nextInt(4);
		switch(alea){
			case 0:
				vertical = -1;
				directionActu = Personnage.AVANCER_HAUT;
				break;
			case 1:
				horizontal = 1;
				directionActu = Personnage.AVANCER_DROITE;
				break;
			case 2:
				directionActu = Personnage.AVANCER_BAS;
				vertical = 1;
				break;
			case 3:
				directionActu = Personnage.AVANCER_GAUCHE;
				horizontal = -1;
		}
	}

	/**
	 * Fonction de mise ajour d'un monstre
	 * @param lab
	 * @param delta
	 * @throws SlickException
	 */
	public void updateMonstre(Labyrinthe lab, int delta) throws SlickException{



		float vitesseActu = delta*Monstre.VITESSE_M;

		float futureX = x + horizontal * vitesseActu;
		float futureY = y + vertical * vitesseActu;

		if(futureX > 0 && futureX < lab.getLongeurCarte() - Labyrinthe.LARGEUR_MUR && futureY > 0 && futureY < lab.getHauteurCarte()- Labyrinthe.HAUTEUR_MUR){
			if (getCollision()) {
				intersection(lab,delta);
				if(vertical == -1){
					if(!collisionVetical(lab, futureX, futureY)){
						setPosYprecedent(y);
						setY(futureY);
					}else
					{
						//monstre.directionAleatoire();
						if(this instanceof Soldat){

							changerDirection(lab, delta);
						}
					}
				}
				if(vertical == 1){
					if(!collisionVetical( lab,futureX, futureY)){
						setPosYprecedent(y);
						setY(futureY);
					}else
					{
						//monstre.directionAleatoire();
						changerDirection(lab, delta);
					}
				}

				if(horizontal == -1){
					if(!collisionHorizontale(lab, futureX, futureY)){
						setPosXPrecedent(x);
						setX(futureX);
					}else
					{
						//monstre.directionAleatoire();
						changerDirection(lab, delta);
					}
				}
				if(horizontal == 1){
					if(!collisionHorizontale(lab, futureX, futureY)){
						setPosXPrecedent(x);
						setX(futureX);
					}else
					{
						//monstre.directionAleatoire();
						changerDirection(lab, delta);
					}
				}
			} else {
				setX(futureX);
				setY(futureY);
			}

		}
	}

	/**
	 * Fonction qui permet au monstre de changer de direction si collision, pas de demi-tour si possible
	 * @param lab
	 * @param delta
	 * @return boolean vrai s'il existe une sortie
	 * @throws SlickException
	 */
	public boolean changerDirection(Labyrinthe lab, int delta) throws SlickException {

		setVertical(0);
		setHorizontal(0);

		int x = (int)(getX() + 6 ) / Labyrinthe.LARGEUR_MUR;
		int y = (int)(getY() + 19 ) / Labyrinthe.HAUTEUR_MUR;
		float vitesseActu = delta*Monstre.VITESSE;

		float fx = getX();
		float fy = getY();

		float futureX = fx;
		float futureY = fy;

		int hauteur = lab.getHauteurLaby();
		int longueur = lab.getLongueurLaby();
		Mur[][] tabMur = lab.getTabMur();

		boolean changer = false;

		ArrayList<Integer> cheminValide = new ArrayList<>();


		futureX = fx;
		futureY = fy + vitesseActu;
		if(y+1 < hauteur-1 && tabMur[x][y+1] == null && !collisionVetical(lab, futureX, futureY)) {
			cheminValide.add(0);
		}
		futureX = fx + vitesseActu;
		futureY = fy;
		if(x+1 < longueur-1 && tabMur[x+1][y] == null && !collisionHorizontale(lab, futureX, futureY) ) {
			cheminValide.add(1);
		}
		futureX = fx;
		futureY = fy - vitesseActu;
		if(y-1 > 0 && tabMur[x][y-1] == null  && !collisionVetical(lab, futureX, futureY)  ) {
			cheminValide.add(2);
		}
		futureX = fx - vitesseActu;
		futureY = fy;
		if (x-1 > 0 && tabMur[x-1][y] == null && !collisionHorizontale(lab, futureX, futureY)) {
			cheminValide.add(3);
		}

		if (cheminValide.size() > 0) {
			Random r = new Random();
			int rd =  r.nextInt(cheminValide.size());

			int i = getDirectionActu();
			int oppose = (i%4 - 2 >= 0) ? (i%4) - 2  : (i%4) + 2 ;
			// si le monstre a choisit de retourner et qu'on a plus d'une direction possible
			// on choisit une autre direction
			while (cheminValide.size() > 1 &&  cheminValide.get(rd) == oppose ) {
				rd =  r.nextInt(cheminValide.size());
			}

			int direction = cheminValide.get(rd);
			switch(direction){
				case 0:
					setVertical(1);
					break;
				case 1:
					setHorizontal(1);
					break;
				case 2:
					setVertical(-1);
					break;
				case 3:
					setHorizontal(-1);
			}

			setDirectionActu(direction + 4);
			changer = true;
		}

		return changer;
	}

	// si le monstre est sur une nouvelle case
	// regarde dans toutes les directions les chemins possible

	/**
	 * Appel changerDirection à chaque position du monstre.
	 * @param lab
	 * @param delta
	 * @throws SlickException
	 * @see #changerDirection(Labyrinthe, int)
	 */
	private void intersection(Labyrinthe lab, int delta) throws SlickException {

		int xAvant = (int)(getPosXPrecedent() ) / Labyrinthe.LARGEUR_MUR;
		int yAvant = (int)(getPosYprecedent() ) / Labyrinthe.HAUTEUR_MUR;

		int xActu = (int)(getX() ) / Labyrinthe.LARGEUR_MUR;
		int yActu = (int)(getY() ) / Labyrinthe.HAUTEUR_MUR;

		if (xAvant  != xActu || yAvant != yActu) {
			changerDirection(lab, delta);
		}
	}
	

	public float getPosXPrecedent() {
		return posXPrecedent;
	}

	public float getPosYprecedent() {
		return posYprecedent;
	}
	

	public void setPosXPrecedent(float posXPrecedent) {
		this.posXPrecedent = posXPrecedent;
	}

	public void setPosYprecedent(float prosYprecedent) {
		this.posYprecedent = prosYprecedent;
	}

}
