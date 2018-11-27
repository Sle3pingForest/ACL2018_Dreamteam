package model.personnages.monstres;

import java.util.ArrayList;
import java.util.Random;

import model.personnages.Heros;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.geom.Rectangle;
import model.Labyrinthe;
import model.mur.Mur;
import model.personnages.Personnage;

public abstract class Monstre extends Personnage {


	public final static  float VITESSE_M = 0.05f;

	protected int largeur = 17;
	protected int hauteur = 41;
	protected int decalage_largeur = 15;
	protected int decalage_hauteur = 20;
	
	protected int xCasePrece;
	protected int yCasePrece;




	public Monstre(int x, int y){
		super(x,y);
		goBas();
		xCasePrece = getXCaseCentre();
		yCasePrece = getYCaseCentre();
		//collision = false;
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

		boxCollider.setY(futureY+decalage_hauteur);
		boxCollider.setX(futureX+decalage_largeur);
		boolean collision = false;


		if(futureX > 0 && futureX < lab.getLongeurCarte() - Labyrinthe.LARGEUR_MUR ){
			if (getCollision()) {
				if(horizontal == 1){
					if(!collisionHorizontale(lab, x+largeur, y)){
						setX(futureX);
					}else{
						boxCollider.setX(x+decalage_largeur);
						boxCollider.setY(y+decalage_hauteur);
						collision = true;
					}
				}
				if(horizontal == -1){
					if(!collisionHorizontale(lab, futureX, futureY)){
						setX(futureX);
					}else{
						boxCollider.setX(x+decalage_largeur);
						boxCollider.setY(y+decalage_hauteur);
						collision = true;
					}
				}
			} else {
				setX(futureX);

			}



			if(futureY > 0 && futureY < lab.getHauteurCarte()- Labyrinthe.HAUTEUR_MUR){
				if (getCollision()) {
					if(vertical == -1){
						if(!collisionVetical(lab, futureX, futureY)){
							setY(futureY);
						}else{
							boxCollider.setX(x+decalage_largeur);
							boxCollider.setY(y+decalage_hauteur);
							collision = true;
						}
					}
					if(vertical == 1){
						if(!collisionVetical( lab,futureX, futureY+hauteur)){
							setY(futureY);
						}else{
							boxCollider.setX(x+decalage_largeur);
							boxCollider.setY(y+decalage_hauteur);
							collision = true;
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


	public int getXCentre(){
		return (int)(x +(decalage_largeur+ largeur)/2);
	}

	public int getYCentre(){
		return (int)(y +(decalage_hauteur+ hauteur)/2);
	}

	public int getYCaseCentre(){
		return (int)(getYCentre())/Labyrinthe.HAUTEUR_MUR;
	}

	public int getXCaseCentre(){
		return (int)(getXCentre())/Labyrinthe.LARGEUR_MUR;
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
