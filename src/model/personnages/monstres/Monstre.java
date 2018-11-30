package model.personnages.monstres;

import model.Labyrinthe;
import model.mur.Mur;
import model.personnages.Personnage;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Random;

public abstract class Monstre extends Personnage {


	public final static  float VITESSE_M = 0.05f;


	
	protected int xCasePrece;
	protected int yCasePrece;




	public Monstre(int x, int y){
		super(x,y);
		goBas();
		xCasePrece = getXCaseCentre();
		yCasePrece = getYCaseCentre();
		collision = false;
	}

	/**
	 * Fonction de mise ajour d'un monstre
	 * @param lab
	 * @param delta
	 * @throws SlickException
	 */
	public void updateMonstre(Labyrinthe lab, int delta) throws SlickException{

		float vitesseActu = delta*VITESSE_M;

		chekDirection(lab);

		float futureX = x + horizontal * vitesseActu;
		float futureY = y + vertical * vitesseActu;



		if(futureX > 0 && futureX < lab.getLongeurCarte() - Labyrinthe.LARGEUR_MUR ){
			if (getCollision()) {
				if(horizontal == 1){
					if(collisionHorizontale(lab)){

					}
				}
				if(horizontal == -1){
					if(!collisionHorizontale(lab)){

					}
				}
			} else {
				setX(futureX);

			}



			if(futureY > 0 && futureY < lab.getHauteurCarte()- Labyrinthe.HAUTEUR_MUR){
				if (getCollision()) {
					if(vertical == -1){
						if(collisionVetical(lab)){

						}
					}
					if(vertical == 1){
						if(collisionVetical( lab)){

						}
					}

					if(collision) {
						changerDirection(lab);
					}
				}else {
					setY(futureY);
				}
			}
		}
	}

	/**
	 * Fonction qui permet au monstre de changer de direction si collision, pas de demi-tour si possible
	 * @param lab
	 * @return boolean vrai s'il existe une sortie
	 * @throws SlickException
	 */
	public void chekDirection(Labyrinthe lab){

		int xCaseNouvelle = getXCaseCentre();
		int yCaseNouvelle = getYCaseCentre();


		if(horizontal == 1){
			if (xCaseNouvelle != xCasePrece) {
				if(x >= (xCaseNouvelle-1/2)*Labyrinthe.LARGEUR_MUR){
					changerDirection(lab);
				}
			}
		}

		if(horizontal == -1){
			if (xCaseNouvelle != xCasePrece) {
				if(x <= (xCaseNouvelle+1/2)*Labyrinthe.LARGEUR_MUR){
					changerDirection(lab);
				}
			}
		}

		if(vertical == 1){
			if(y >= (yCaseNouvelle-1/2)*Labyrinthe.HAUTEUR_MUR)
			{
				if (yCaseNouvelle != yCasePrece)
				{
					changerDirection(lab);
				}
			}
		}

		if(vertical == -1){
			if (yCaseNouvelle != yCasePrece) {
				if(y <= (yCaseNouvelle+1/2)*Labyrinthe.HAUTEUR_MUR)
				{
					changerDirection(lab);
				}
			}
		}



	}

	public void changerDirection(Labyrinthe lab){
		int choix;
		Random r = new Random();
		int xCaseNouvelle = getXCaseCentre();
		int yCaseNouvelle = getYCaseCentre();
		ArrayList<Integer> lesDirection =  directionPossible(lab);
		setVertical(0);
		setHorizontal(0);
		choix = r.nextInt(lesDirection.size());
		choix = lesDirection.get(choix);
		xCasePrece = xCaseNouvelle;
		yCasePrece = yCaseNouvelle;

		switch(choix){
			case Personnage.AVANCER_BAS:
				this.goBas();
				break;
			case Personnage.AVANCER_DROITE:
				this.goDroite();
				break;
			case Personnage.AVANCER_GAUCHE:
				this.goGauche();
				break;
			case Personnage.AVANCER_HAUT:
				this.goHaut();
				break;
			default:
				break;
		}
	}

	private ArrayList<Integer> directionPossible(Labyrinthe lab){
		Mur[][] tabMur = lab.getTabMur();
		int xCaseNouvelle = getXCaseCentre();
		int yCaseNouvelle = getYCaseCentre();
		ArrayList<Integer> lesDirectionPossible = new ArrayList<Integer>();
		if(horizontal == -1){
			if(tabMur[xCaseNouvelle-1][yCaseNouvelle] == null){
				lesDirectionPossible.add(Personnage.AVANCER_GAUCHE);
			}
			if(tabMur[xCaseNouvelle][yCaseNouvelle+1] == null){
				lesDirectionPossible.add(Personnage.AVANCER_BAS);
			}
			if(tabMur[xCaseNouvelle][yCaseNouvelle-1] == null){
				lesDirectionPossible.add(Personnage.AVANCER_HAUT);
			}
			if(lesDirectionPossible.isEmpty()){
				lesDirectionPossible.add(Personnage.AVANCER_DROITE);
			}
		}

		if(horizontal == 1){
			if(tabMur[xCaseNouvelle+1][yCaseNouvelle] == null){
				lesDirectionPossible.add(Personnage.AVANCER_DROITE);
			}
			if(tabMur[xCaseNouvelle][yCaseNouvelle+1] == null){
				lesDirectionPossible.add(Personnage.AVANCER_BAS);
			}
			if(tabMur[xCaseNouvelle][yCaseNouvelle-1] == null){
				lesDirectionPossible.add(Personnage.AVANCER_HAUT);
			}
			if(lesDirectionPossible.isEmpty()){
				lesDirectionPossible.add(Personnage.AVANCER_GAUCHE);
			}
		}

		if(vertical == -1){
			if(yCaseNouvelle-1 > 0){
				if(tabMur[xCaseNouvelle][yCaseNouvelle-1] == null){
					lesDirectionPossible.add(Personnage.AVANCER_HAUT);
				}
			}
			if(tabMur[xCaseNouvelle+1][yCaseNouvelle] == null){
				lesDirectionPossible.add(Personnage.AVANCER_DROITE);
			}
			if(tabMur[xCaseNouvelle-1][yCaseNouvelle] == null){
				lesDirectionPossible.add(Personnage.AVANCER_GAUCHE);
			}
			if(lesDirectionPossible.isEmpty()){
				lesDirectionPossible.add(Personnage.AVANCER_BAS);
			}
		}

		if(vertical == 1){
			if(tabMur[xCaseNouvelle][yCaseNouvelle+1] == null){
				lesDirectionPossible.add(Personnage.AVANCER_BAS);
			}
			if(tabMur[xCaseNouvelle+1][yCaseNouvelle] == null){
				lesDirectionPossible.add(Personnage.AVANCER_DROITE);
			}
			if(tabMur[xCaseNouvelle-1][yCaseNouvelle] == null){
				lesDirectionPossible.add(Personnage.AVANCER_GAUCHE);
			}
			if(lesDirectionPossible.isEmpty()){
				lesDirectionPossible.add(Personnage.AVANCER_HAUT);
			}
		}

		return  lesDirectionPossible;
	}







	public int getLargeur(){
		return  largeur;
	}

	public int getDecalage_hauteur() {
		return decalage_hauteur;
	}

	public int getDecalage_largeur(){
		return decalage_largeur;
	}

	public int getHauteur() {
		return hauteur;
	}
}
