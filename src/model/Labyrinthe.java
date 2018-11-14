package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import model.Item.Epee;
import model.Item.Item;
import model.Item.ItemFactory;
import model.Item.Tresor;
import model.mur.Mur;
import model.personnages.Heros;
import model.personnages.Personnage;
import model.personnages.monstres.Dragon;
import model.personnages.monstres.FabriqueMonstre;
import model.personnages.monstres.Monstre;
import model.personnages.monstres.Soldat;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;


public class Labyrinthe implements Serializable{

	public final static int HAUTEUR_MUR = 31;
	public final static int LARGEUR_MUR = 32;
	private int longeurCarte;
	private int hauteurCarte;

	private int[] tabNomMonstre ={FabriqueMonstre.SOLDAT,FabriqueMonstre.SOLDAT,FabriqueMonstre.SOLDAT,FabriqueMonstre.DRAGON};
	private Item [][]lesObjets;
	private int [] tabNomItem = {ItemFactory.EPEE};
	private ItemFactory creationItem;
	private int longueur , hauteur;
	private Mur[][] tabMur ;
	private ArrayList<Monstre> listeMonstres;
	private FabriqueMonstre creationMonstres;
	public static boolean MORT_HEROS = false;
	private ArrayList<Heros> lesHeros;
	private float multiplicateurProba = 1.2f;//Il pourrait varier aleatoirement
	private boolean tresorTrouver = false;
	private final static int TAILLE_MAX_COULOIR = 6;


	private Random random = new Random();

	/**
	 * Constructeur du labyrinthe
	 * Construit un labyrinthe de taille longueur*hauteur remplie integralement de mur puis creusé.
	 * @param longueur
	 * @param hauteur
	 * @see #creuse()
	 */
	public Labyrinthe(int longueur ,int hauteur, int nbMonstres){
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.creationMonstres = new FabriqueMonstre();
		lesHeros = new ArrayList<Heros>();
		lesObjets = new Item[longueur][hauteur];
		tabMur = new Mur[longueur][hauteur];
		this.listeMonstres = new ArrayList<>();
		this.creationItem = new ItemFactory();
		longeurCarte = tabMur.length * LARGEUR_MUR;
		hauteurCarte = tabMur[0].length * HAUTEUR_MUR;




		/**** On rempli tout le laby  de mur ****/
		for(int i = 0; i < hauteur ;i++){
			for(int j = 0 ; j < longueur ; j++){
				tabMur[j][i] =  new Mur(j,i);
			}
		}

		/**** Et maintenant on creuse ****/
		creuse();
		produitMonstres(nbMonstres);
		produitItem(nbMonstres/5);
	}

	/**
	 * Fonction qui creuse la map pour creer un labyrinthe
	 * le heros est placé aleatoirement et à partir de lui on creuse dans un sens aleatoire en fonction de la fonction peutEtreCreusé()
	 * @see #peutEtreCreuser(Mur)
	 */
	private void creuse(){

		int rand,choix;
		ArrayList<Integer> listChoix = new ArrayList<Integer>();
		Mur murActu = null;
		int nbCaseACreuser = (int)(longueur*hauteur);
		int nbAEncoreCreuser = nbCaseACreuser;

		/***** On choisi aleatoirement le debut du laby la ou sera notre heros *****/
		int xDebut = random.nextInt(longueur-2)+1;
		int yDebut = random.nextInt(hauteur-2)+1;

		lesHeros.add(new Heros(xDebut*LARGEUR_MUR,yDebut*HAUTEUR_MUR, "Link"));


		ArrayList<Mur> chemin = new ArrayList<Mur>();
		chemin.add(tabMur[xDebut][yDebut]);
		tabMur[xDebut][yDebut] = null;

		while(!chemin.isEmpty()) {

			rand = random.nextInt(chemin.size());
			murActu = chemin.get(rand);
			chemin.remove(rand);
			//System.out.println(nbCaseACreuser+" : "+murActu);

			xDebut = murActu.getPosX();
			yDebut = murActu.getPosY();

			listChoix.add(0);
			listChoix.add(1);
			listChoix.add(2);
			listChoix.add(3);

			while(!listChoix.isEmpty()) {
				rand = random.nextInt(listChoix.size());
				choix = listChoix.get(rand);
				listChoix.remove(rand);

				switch(choix) {
				case 0:
					/**** creuser a gauche ****/
					if (xDebut > 1 && tabMur[xDebut - 1][yDebut] != null) {
						if (peutEtreCreuser(tabMur[xDebut - 1][yDebut])) {
							rand = random.nextInt(3*nbCaseACreuser);
							if (rand <= multiplicateurProba*nbAEncoreCreuser) {
								chemin.add(tabMur[xDebut - 1][yDebut]);
								tabMur[xDebut - 1][yDebut] = null;
								nbAEncoreCreuser--;
							}
						}
					}
					break;
				case 1:
					/**** creuser a droite ****/
					if (xDebut < longueur - 2 && tabMur[xDebut + 1][yDebut] != null) {
						if (peutEtreCreuser(tabMur[xDebut + 1][yDebut])) {
							rand = random.nextInt(nbCaseACreuser);
							if (rand <= multiplicateurProba*nbAEncoreCreuser) {
								chemin.add(tabMur[xDebut + 1][yDebut]);
								tabMur[xDebut + 1][yDebut] = null;
								nbAEncoreCreuser--;
							}
						}
					}
					break;

				case 2:
					/**** creuser en haut ****/
					if (yDebut > 1 && tabMur[xDebut][yDebut - 1] != null) {
						if (peutEtreCreuser(tabMur[xDebut][yDebut - 1])) {
							rand = random.nextInt(nbCaseACreuser);
							if (rand <= multiplicateurProba*nbAEncoreCreuser) {
								chemin.add(tabMur[xDebut][yDebut - 1]);
								tabMur[xDebut][yDebut - 1] = null;
								nbAEncoreCreuser--;
							}
						}
					}
					break;

				case 3:
					/**** creuser en bas ****/
					if (yDebut < hauteur - 2 && tabMur[xDebut][yDebut + 1] != null) {
						if (peutEtreCreuser(tabMur[xDebut][yDebut + 1])) {
							rand = random.nextInt(nbCaseACreuser);
							if (rand <= multiplicateurProba*nbAEncoreCreuser) {
								chemin.add(tabMur[xDebut][yDebut + 1]);
								tabMur[xDebut][yDebut + 1] = null;
								nbAEncoreCreuser--;
							}
						}
					}
					break;
				}
			}
		}
		lesObjets[murActu.getPosX()][murActu.getPosY()] = new Tresor(murActu.getPosX()* LARGEUR_MUR,murActu.getPosY()*HAUTEUR_MUR,null);
		//lesObjets[murActu.getPosX()][murActu.getPosY()] = new Epee(150,150);

	}

	/**
	 * Verifie que ce mur est creusable, ni trop grand ni trop large.
	 * @param mur
	 * @return boolean
	 */
	private boolean peutEtreCreuser(Mur mur){
		int x = mur.getPosX();
		int y = mur.getPosY();

		/***** La on verifie que creuser ici ne fait un chemin a 2 voix *****/
		if(tabMur[x+1][y] == null ){
			if(tabMur[x][y+1] == null){
				return false;
			}
			if(tabMur[x][y-1] == null){
				return false;
			}
		}

		if(tabMur[x-1][y] == null ){
			if(tabMur[x][y+1] == null){
				return false;
			}
			if(tabMur[x][y-1] == null){
				return false;
			}
		}
		/***** Ici on verifie que creuser a cette endroi ne cree pas un couloir trop grand ****/

		/**** couloir horizontal ***/
		int tailleCouloire = 1;
		int i = x-1;
		while(i > 0 && tabMur[i][y] == null){
			tailleCouloire++;
			if(tailleCouloire > TAILLE_MAX_COULOIR){
				return  false;
			}
			i--;
		}
		i = x+1;
		while(i < longueur-1 && tabMur[i][y] == null){
			tailleCouloire++;
			if(tailleCouloire > TAILLE_MAX_COULOIR){
				return  false;
			}
			i++;
		}

		/**** couloir vertical ***/

		tailleCouloire = 1;
		i = y-1;
		while(i > 0 && tabMur[x][i] == null){
			tailleCouloire++;
			if(tailleCouloire > TAILLE_MAX_COULOIR){
				return  false;
			}
			i--;
		}
		i = y+1;
		while(i < longueur-1 && tabMur[x][i] == null){
			tailleCouloire++;
			if(tailleCouloire > TAILLE_MAX_COULOIR){
				return  false;
			}
			i++;
		}

		return true;
	}

	/**
	 * fonction de mise a jour du labyrinthe
	 * @param container
	 * @param delta
	 * @throws SlickException
	 */
	public void update(GameContainer container, int delta) throws SlickException {

		updateHeros(lesHeros.get(0),delta);
		
		//deplacerMonstres();
		for(Monstre m : listeMonstres){
			if(m.getPointVie() >0 ){
				updateMonstre(m, delta);
			}
		}

	}

	/**
	 * fonction de mise a jour du Heros
	 * @param heros
	 * @param delta
	 * @throws SlickException
	 */
	public void updateHeros(Heros heros, int delta) throws SlickException{

		float vitesseActu = delta*Heros.VITESSE;

		float x = heros.getX();
		float y = heros.getY();
		
		/*int xH = (int)(lesHeros.get(0).getX()/LARGEUR_MUR);
		int yH = (int)(lesHeros.get(0).getY()/HAUTEUR_MUR);
		if(lesObjets[xH][yH] != null){
			//tresorTrouver = true;
			lesHeros.get(0).ajouterAInventaire(lesObjets[xH][yH]);
			lesObjets[xH][yH].isRamasser();
			System.err.println("j ai trouve mon tresort");
		}*/
		
		int horizontal = heros.getHorizontal();
		int vertical = heros.getVertical();

		float futureX = x + horizontal * vitesseActu;
		float futureY = y + vertical * vitesseActu;
		if(futureX > 0 && futureX < getLongeurCarte() - LARGEUR_MUR && futureY > 0 && futureY < getHauteurCarte()-HAUTEUR_MUR){
			if (heros.getCollision()) {
				if(vertical == -1){
					if(!collisionHaut(heros, futureX, futureY)){
						heros.setY(futureY);
					}
				}
				if(vertical == 1){
					if(!collisionBas( heros,futureX, futureY)){
						heros.setY(futureY);
					}
				}

				if(horizontal == -1){
					if(!collisionGauche(heros, futureX, futureY)){
						heros.setX(futureX);
					}
				}
				if(horizontal == 1){
					if(!collisionDroite(heros, futureX, futureY)){
						heros.setX(futureX);
					}
				}
			} else {
				heros.setX(futureX);
				heros.setY(futureY);
			}
		}

	}


	/**
	 * Fonction de mise ajour d'un monstre
	 * @param monstre
	 * @param delta
	 * @throws SlickException
	 */
	public void updateMonstre(Monstre monstre, int delta) throws SlickException{



		float vitesseActu = delta*Monstre.VITESSE_M;
		float x = monstre.getX();
		float y = monstre.getY();
		int horizontal = monstre.getHorizontal();
		int vertical = monstre.getVertical();

		float futureX = x + horizontal * vitesseActu;
		float futureY = y + vertical * vitesseActu;

		if(futureX > 0 && futureX < getLongeurCarte() - LARGEUR_MUR && futureY > 0 && futureY < getHauteurCarte()-HAUTEUR_MUR){
			if (monstre.getCollision()) {
				intersection(monstre,delta);
				if(vertical == -1){
					if(!collisionHaut(monstre, futureX, futureY)){
						monstre.setPosYprecedent(y);
						monstre.setY(futureY);
					}else
					{
						//monstre.directionAleatoire();
						if(monstre instanceof Soldat){

							changerDirection(monstre, delta);
						}
					}
				}
				if(vertical == 1){
					if(!collisionBas( monstre,futureX, futureY)){
						monstre.setPosYprecedent(y);
						monstre.setY(futureY);
					}else
					{
						//monstre.directionAleatoire();
						changerDirection(monstre, delta);
					}
				}

				if(horizontal == -1){
					if(!collisionGauche(monstre, futureX, futureY)){
						monstre.setPosXPrecedent(x);
						monstre.setX(futureX);
					}else
					{
						//monstre.directionAleatoire();
						changerDirection(monstre, delta);
					}
				}
				if(horizontal == 1){
					if(!collisionDroite(monstre, futureX, futureY)){
						monstre.setPosXPrecedent(x);
						monstre.setX(futureX);
					}else
					{
						//monstre.directionAleatoire();
						changerDirection(monstre, delta);
					}
				}
			} else {
				monstre.setX(futureX);
				monstre.setY(futureY);
			}
			
		}
	}

	private ArrayList<Integer> getDirectionPossible(Monstre m){
		ArrayList<Integer> lesDirectionPossible =  new ArrayList<Integer>();

		int direction = m.getDirectionActu();
		int xCase = (int)((m.getX())/LARGEUR_MUR);
		int yCase = (int)((m.getY())/HAUTEUR_MUR);

		switch (direction){
		case Personnage.AVANCER_BAS:
			if(tabMur[xCase][yCase+1] == null){
				lesDirectionPossible.add(Personnage.AVANCER_BAS);
			}
			if(tabMur[xCase+1][yCase] == null){
				lesDirectionPossible.add(Personnage.AVANCER_DROITE);
			}
			if(tabMur[xCase-1][yCase] == null){
				lesDirectionPossible.add(Personnage.AVANCER_GAUCHE);
			}
			if(lesDirectionPossible.isEmpty()){
				lesDirectionPossible.add(Personnage.AVANCER_HAUT);
			}
			break;
		case Personnage.AVANCER_DROITE:
			if(tabMur[xCase+1][yCase] == null){
				lesDirectionPossible.add(Personnage.AVANCER_DROITE);
			}
			if(tabMur[xCase][yCase+1] == null){
				lesDirectionPossible.add(Personnage.AVANCER_BAS);
			}
			if(tabMur[xCase][yCase-1] == null){
				lesDirectionPossible.add(Personnage.AVANCER_HAUT);
			}
			if(lesDirectionPossible.isEmpty()){
				lesDirectionPossible.add(Personnage.AVANCER_GAUCHE);
			}
			break;
		case Personnage.AVANCER_HAUT:
			if(tabMur[xCase][yCase-1] == null){
				lesDirectionPossible.add(Personnage.AVANCER_HAUT);
			}
			if(tabMur[xCase+1][yCase] == null){
				lesDirectionPossible.add(Personnage.AVANCER_DROITE);
			}
			if(tabMur[xCase-1][yCase] == null){
				lesDirectionPossible.add(Personnage.AVANCER_GAUCHE);
			}
			if(lesDirectionPossible.isEmpty()){
				lesDirectionPossible.add(Personnage.AVANCER_BAS);
			}
			break;
		case Personnage.AVANCER_GAUCHE:
			if(tabMur[xCase-1][yCase] == null){
				lesDirectionPossible.add(Personnage.AVANCER_GAUCHE);
			}
			if(tabMur[xCase][yCase+1] == null){
				lesDirectionPossible.add(Personnage.AVANCER_BAS);
			}
			if(tabMur[xCase][yCase-1] == null){
				lesDirectionPossible.add(Personnage.AVANCER_HAUT);
			}
			if(lesDirectionPossible.isEmpty()){
				lesDirectionPossible.add(Personnage.AVANCER_DROITE);
			}
			break;
		}
		return  lesDirectionPossible;
	}

	/**
	 * Verifie les collision entre la futur position du personnage et un possible mur situé au dessus de lui
	 * @param heros
	 * @param futureX
	 * @param futureY
	 * @return boolean
	 * @throws SlickException
	 */
	private boolean collisionHaut(Personnage heros,float futureX,float futureY) throws SlickException {


		int horizontal = heros.getHorizontal();

		int xCaseFuture = (int)((futureX+6)/LARGEUR_MUR);
		int yCaseFuture = (int)((futureY+19)/HAUTEUR_MUR);

		if(horizontal == -1){
			xCaseFuture = (int)((futureX+6-2)/LARGEUR_MUR);
		}
		if(horizontal == 1){
			xCaseFuture = (int)((futureX+6+2)/LARGEUR_MUR);
		}

		if(tabMur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		if(lesObjets[xCaseFuture][yCaseFuture] != null && heros instanceof Heros){
			lesObjets[xCaseFuture][yCaseFuture].ramasser();
			heros.ajouterAInventaire(lesObjets[xCaseFuture][yCaseFuture]);
		}
		xCaseFuture = (int)((futureX-6 + Heros.LARGEUR_SPRITE)/LARGEUR_MUR);

		if(tabMur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		if(lesObjets[xCaseFuture][yCaseFuture] != null && heros instanceof Heros){
			lesObjets[xCaseFuture][yCaseFuture].ramasser();
			heros.ajouterAInventaire(lesObjets[xCaseFuture][yCaseFuture]);
		}


		return false;
	}

	/**
	 * Verifie les collision entre la futur position du personnage et un possible mur situé au dessous de lui
	 * @param heros
	 * @param futureX
	 * @param futureY
	 * @return boolean
	 * @throws SlickException
	 */
	private boolean collisionBas(Personnage heros, float futureX, float futureY) throws SlickException {

		int horizontal = heros.getHorizontal();


		int xCaseFuture = (int)((futureX+6)/LARGEUR_MUR);
		int yCaseFuture = (int)((futureY-6)/HAUTEUR_MUR)+1;

		if(horizontal == -1){
			xCaseFuture = (int)((futureX+6+2)/LARGEUR_MUR);
		}
		if(horizontal == 1){
			xCaseFuture = (int)((futureX+6-2)/LARGEUR_MUR);
		}

		if(tabMur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		if(lesObjets[xCaseFuture][yCaseFuture] != null && heros instanceof Heros){
			lesObjets[xCaseFuture][yCaseFuture].ramasser();
			heros.ajouterAInventaire(lesObjets[xCaseFuture][yCaseFuture]);
		}
		xCaseFuture = (int)((futureX-6 + Heros.LARGEUR_SPRITE)/LARGEUR_MUR);

		if(tabMur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		if(lesObjets[xCaseFuture][yCaseFuture] != null && heros instanceof Heros){
			lesObjets[xCaseFuture][yCaseFuture].ramasser();
			heros.ajouterAInventaire(lesObjets[xCaseFuture][yCaseFuture]);
		}


		return false;
	}

	/**
	 * Verifie les collision entre la futur position du personnage et un possible mur situé à gauche de lui
	 * @param heros
	 * @param futureX
	 * @param futureY
	 * @return boolean
	 * @throws SlickException
	 */
	private boolean collisionGauche(Personnage heros,float futureX,float futureY) throws SlickException {

		int vertical = heros.getVertical();

		int xCaseFuture = (int)((futureX+6)/LARGEUR_MUR);
		int yCaseFuture = (int)((futureY+19)/HAUTEUR_MUR);

		if(vertical == -1){
			xCaseFuture = (int)((futureX+6)/LARGEUR_MUR);
			yCaseFuture = (int)((futureY+2+19)/HAUTEUR_MUR);
		}

		if(tabMur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		if(lesObjets[xCaseFuture][yCaseFuture] != null && heros instanceof Heros){
			lesObjets[xCaseFuture][yCaseFuture].ramasser();
			heros.ajouterAInventaire(lesObjets[xCaseFuture][yCaseFuture]);
		}
		return false;
	}

	/**
	 * Verifie les collision entre la futur position du personnage et un possible mur situé à droite de lui
	 * @param heros
	 * @param futureX
	 * @param futureY
	 * @return boolean
	 * @throws SlickException
	 */
	private boolean collisionDroite(Personnage heros,float futureX,float futureY) throws SlickException {


		int vertical = heros.getVertical();

		int xCaseFuture = (int)((futureX+6)/LARGEUR_MUR);
		int yCaseFuture = (int)((futureY+19)/HAUTEUR_MUR);

		if(vertical == -1){
			xCaseFuture = (int)((futureX+6)/LARGEUR_MUR);
			yCaseFuture = (int)((futureY+2+19)/HAUTEUR_MUR);
		}
		if(tabMur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		if(lesObjets[xCaseFuture][yCaseFuture] != null && heros instanceof Heros){
			lesObjets[xCaseFuture][yCaseFuture].ramasser();
			heros.ajouterAInventaire(lesObjets[xCaseFuture][yCaseFuture]);
		}
		xCaseFuture = (int)((futureX-6 + Heros.LARGEUR_SPRITE)/LARGEUR_MUR);

		if(tabMur[xCaseFuture][yCaseFuture] != null){
			return true;
		}
		if(lesObjets[xCaseFuture][yCaseFuture] != null && heros instanceof Heros){
			lesObjets[xCaseFuture][yCaseFuture].ramasser();
			heros.ajouterAInventaire(lesObjets[xCaseFuture][yCaseFuture]);
		}


		return false;
	}

	/**
	 * Retourne le tableau des murs.
	 * @return tabMur le tableu contenant les murs et les passages.
	 */
	public Mur[][] getTabMur(){
		return  tabMur;
	}


	/**
	 * Place des monstres dans le labyrinthe.
	 * @param nombreMonstre nombre de monstres à placer
	 */
	private void produitMonstres(int nombreMonstre){

		for(int i = 0; i < nombreMonstre; ++i){
			int rng  = (int)(Math.random() * (tabNomMonstre.length)) ;

			int posX = (int)(Math.random() * (longueur));
			int posY = (int)(Math.random() * (hauteur));
			boolean correct = false;
			while(!correct){
				if(tabMur[posX][posY] == null && posX != lesHeros.get(0).getX() && posY != lesHeros.get(0).getY()){
					correct = true;
					listeMonstres.add(this.creationMonstres.creerMonstres(tabNomMonstre[rng], posX * LARGEUR_MUR, posY*HAUTEUR_MUR));
				}
				else{
					posX = (int)(Math.random() * (longueur));
					posY = (int)(Math.random() * (hauteur));
				}
			}
		}
	}

	/**
	 * Place des Items dans le labyrinthe.
	 * @param nombreItem nombre de d'item à placer
	 */
	private void produitItem(int nombreItem){

		for(int i = 0; i < nombreItem; ++i){
			int rng  = (int)(Math.random() * (tabNomItem.length)) ;

			int posX = (int)(Math.random() * (longueur));
			int posY = (int)(Math.random() * (hauteur));
			boolean correct = false;
			while(!correct){
				if(tabMur[posX][posY] == null){
					correct = true;
							lesObjets[posX][posY] = this.creationItem.creerItems(tabNomItem[rng], posX * LARGEUR_MUR, posY * HAUTEUR_MUR);
				}
				else{
					posX = (int)(Math.random() * (longueur));
					posY = (int)(Math.random() * (hauteur));
				}
			}
		}

	}


	/**
	 * Recuperation de la largeur et hauteur du labyrinthe dans ce fichier
	 * @param fichierName nom du fichier contenant le labyrinthe.
	 */
	private void iniLargeurHauteur(String fichierName){
		//On lit deja une fois le fichier pour connaitre la hauteur  oui c lourd  mais de toute facon no fichier ne feront probablement jamais plus de quelque Ko  donc  cela reste rapide
		try
		{
			File f = new File (fichierName);
			FileReader fr = new FileReader (f);
			BufferedReader br = new BufferedReader (fr);

			try
			{
				String line = br.readLine();
				longueur = line.length()-2;// on retire 2  pour le passage a la ligne
				hauteur = 0;
				while (line != null)
				{
					if(line.length() == longueur+2){
						hauteur++;
					}
					line = br.readLine();
				}

				br.close();
				fr.close();
				tabMur = new Mur[longueur][hauteur];
			}
			catch (IOException exception)
			{
				System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
			}
		}
		catch (FileNotFoundException exception)
		{
			System.out.println ("Le fichier n'a pas été trouvé");
		}
	}
	// j'ai modifier legerement la fonction pur que l'or de la lecture il initialise lui meme longueur et hauteur
	private void constructionLabyrinthe(String fichierName){
		iniLargeurHauteur(fichierName);
		try
		{
			File f = new File (fichierName);
			FileReader fr = new FileReader (f);
			BufferedReader br = new BufferedReader (fr);

			try
			{
				String line = br.readLine();
				int xMur = 0;
				while (line != null)
				{
					for(int i = 0; i < this.longueur; i++){
						char c = line.charAt(i);
						if(c=='1'){
							this.tabMur[i][xMur] = new Mur(i, xMur);
						}
					}
					line = br.readLine();
					xMur++;
				}

				br.close();
				fr.close();
			}
			catch (IOException exception)
			{
				System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
			}
		}
		catch (FileNotFoundException exception)
		{
			System.out.println ("Le fichier n'a pas été trouvé");
		}
	}
	/*********** fin de construction**************/

	//GESTION DEPLACEMENT HEROS et Monstre
	public void goGauche(){
		lesHeros.get(0).goGauche();
	}

	public void goDroite(){
		lesHeros.get(0).goDroite();
	}

	public void goBas(){
		lesHeros.get(0).goBas();
	}

	public void goHaut(){
		lesHeros.get(0).goHaut();
	}

	public void arretBas(){
		lesHeros.get(0).arretBas();
	}

	public void arretDroite(){
		lesHeros.get(0).arretDroite();
	}
	public void arretHaut(){
		lesHeros.get(0).arretHaut();
	}

	public void arretGauche(){
		lesHeros.get(0).arretGauche();
	}

	/**
	 * Fonction permettant au heros d'attaquer
	 */
	public void attaquer(){
	lesHeros.get(0).attaquer();
	   float xH = lesHeros.get(0).getX()/LARGEUR_MUR;
	   float yH = lesHeros.get(0).getY()/HAUTEUR_MUR;
	   for(Monstre m : listeMonstres){
		   float xM = m.getX()/LARGEUR_MUR;
		   float yM = m.getY()/HAUTEUR_MUR;
		   boolean estToucher = false;
		   if(Math.abs(yH -yM) < 1 && Math.abs(xH-xM) < 1  ){
			   estToucher = true;
		   }
		   
		   if(estToucher){
			   lesHeros.get(0).setPointVie(m.getAttaque());
			   m.setPointVie(lesHeros.get(0).getAttaque());
			   if(m.getPointVie() <= 0){
				   m.mortMonstres(); 
			   }
		   }
		   if(lesHeros.get(0).getPointVie() <= 0){
			   MORT_HEROS = true;
			   lesHeros.get(0).mort();
		   }
	   }

	}
	
	
	public void attaquerStop(){
		lesHeros.get(0).attaquerStop();
	}


	/*public void deplacerMonstres(){
		for(Monstre monstre :listeMonstres){

			int x = (int)(monstre.getX() + 6)/ LARGEUR_MUR;
			int y = (int)(monstre.getY() + 19) / HAUTEUR_MUR;

			ArrayList<Integer> cheminValide = new ArrayList<>();

			if(y+1 < hauteur-1 && tabMur[x][y+1] == null) {
				cheminValide.add(2);
			}
			if(x+1 < longueur-1 && tabMur[x+1][y] == null) {
				cheminValide.add(1);
			}
			if(y-1 > 0 && tabMur[x][y-1] == null) {
				cheminValide.add(0);
			}
			if (x-1 > 0 && tabMur[x-1][y] == null) {
				cheminValide.add(3);
			}
			// si le monstre est static 
			//if (!check) direction = direction(monstre);

			Random r = new Random();
			int rd =  r.nextInt(cheminValide.size());
			int direction = cheminValide.get(rd);

			switch(direction){
			case 0:
				monstre.setVertical(-1);
				monstre.setDirectionActu(Personnage.AVANCER_HAUT);
				break;
			case 1:
				monstre.setHorizontal(1);
				monstre.setDirectionActu(Personnage.AVANCER_DROITE);
				break;
			case 2:
				monstre.setVertical(1);
				monstre.setDirectionActu(Personnage.AVANCER_BAS);
				break;
			case 3:
				monstre.setHorizontal(-1);
				monstre.setDirectionActu(Personnage.AVANCER_GAUCHE);
			}

		}
	}*/


	/**
	 * Fonction qui permet au monstre de changer de direction si collision, pas de demi-tour si possible
	 * @param monstre
	 * @param delta
	 * @return boolean vrai s'il existe une sortie
	 * @throws SlickException
	 */
	public boolean changerDirection(Monstre monstre, int delta) throws SlickException {
		monstre.setVertical(0);
		monstre.setHorizontal(0);
		int x = (int)(monstre.getX() + 6 ) / LARGEUR_MUR;
		int y = (int)(monstre.getY() + 19 ) / HAUTEUR_MUR;
		float vitesseActu = delta*Monstre.VITESSE;

		float fx = monstre.getX();
		float fy = monstre.getY();

		float futureX = fx;
		float futureY = fy;

		boolean changer = false;

		ArrayList<Integer> cheminValide = new ArrayList<>();


		futureX = fx;
		futureY = fy + vitesseActu;
		if(y+1 < hauteur-1 && tabMur[x][y+1] == null && !collisionBas(monstre, futureX, futureY)) {
			cheminValide.add(0);
		}
		futureX = fx + vitesseActu;
		futureY = fy;
		if(x+1 < longueur-1 && tabMur[x+1][y] == null && !collisionDroite(monstre, futureX, futureY) ) {
			cheminValide.add(1);
		}
		futureX = fx;
		futureY = fy - vitesseActu;
		if(y-1 > 0 && tabMur[x][y-1] == null  && !collisionHaut(monstre, futureX, futureY)  ) {
			cheminValide.add(2);
		}
		futureX = fx - vitesseActu;
		futureY = fy;
		if (x-1 > 0 && tabMur[x-1][y] == null && !collisionGauche(monstre, futureX, futureY)) {
			cheminValide.add(3);
		}

		if (cheminValide.size() > 0) {
			Random r = new Random();
			int rd =  r.nextInt(cheminValide.size());

			int i = monstre.getDirectionActu();
			int oppose = (i%4 - 2 >= 0) ? (i%4) - 2  : (i%4) + 2 ; 
			// si le monstre a choisit de retourner et qu'on a plus d'une direction possible
			// on choisit une autre direction
			while (cheminValide.size() > 1 &&  cheminValide.get(rd) == oppose ) {
				rd =  r.nextInt(cheminValide.size());
			}

			int direction = cheminValide.get(rd);
			switch(direction){
			case 0:
				monstre.setVertical(1);
				break;
			case 1:
				monstre.setHorizontal(1);
				break;
			case 2:
				monstre.setVertical(-1);
				break;
			case 3:
				monstre.setHorizontal(-1);
			}

			monstre.setDirectionActu(direction + 4);
			changer = true;
		}

		return changer;
	}


	// si le monstre est sur une nouvelle case
	// regarde dans toutes les directions les chemins possible

	/**
	 * Appel changerDirection à chaque position du monstre.
	 * @param monstre
	 * @param delta
	 * @throws SlickException
	 * @see #changerDirection(Monstre, int)
	 */
	private void intersection(Monstre monstre, int delta) throws SlickException {

		int xAvant = (int)(monstre.getPosXPrecedent() ) / LARGEUR_MUR;
		int yAvant = (int)(monstre.getPosYprecedent() ) / HAUTEUR_MUR;

		int xActu = (int)(monstre.getX() ) / LARGEUR_MUR;
		int yActu = (int)(monstre.getY() ) / HAUTEUR_MUR;
		
		if (xAvant  != xActu || yAvant != yActu) {
			changerDirection(monstre, delta);
		}
	}


	/**
	 * Verifie la collision entre les monstres et les heros
	 */
	public void collison(){
		for(Monstre monstre: listeMonstres){
			if(lesHeros.get(0).getX() == monstre.getX() && lesHeros.get(0).getY() == monstre.getY()){
				MORT_HEROS = true;
			}
		}
	}

	/**
	 * Affiche le jeu en version texte.
	 */
	public void afficher(){
		int x =  (int)lesHeros.get(0).getX();
		int y = (int)lesHeros.get(0).getY();
		for(int i = 0 ; i < this.hauteur;i++){
			for( int j = 0; j < this.longueur ; j++){
				if(i == y && j == x ){
					System.out.print("H");
				}
				else{
					if(tabMur[j][i] != null){
						System.out.print("+");
					}
					else{
						boolean avoirMonstre = false;
						for (Monstre m : listeMonstres){
							if(m.getX() == j && i == m.getY() && !avoirMonstre){
								if(m instanceof Soldat){
									System.out.print("O");
								}
								if(m instanceof Dragon){
									System.out.print("D");
								}
								avoirMonstre = true;
							}
						}
						if(!avoirMonstre){
							System.out.print("=");
						}
					}
				}

			}
			System.out.println();
		}
	}

	//GETTER ET SETTER
	public Heros getHeros(int i) {
		return lesHeros.get(i);
	}

	public void setHeros(Heros heros,int i) {
		lesHeros.set(i,heros);
	}

	public ArrayList<Monstre> getListeMonstres() {
		return listeMonstres;
	}

	public void setListeMonstres(ArrayList<Monstre> listeMonstres) {
		this.listeMonstres = listeMonstres;
	}


	public ArrayList<String> deplacementMonstre(int x,int y) {
		String t = "";

		ArrayList<String> chemin = new ArrayList<>();
		chemin.add(t);

		if (x == lesHeros.get(0).getX() && y == lesHeros.get(0).getY()) {
			return chemin;
		} else {

			if ((x+1) < longueur && tabMur[x+1][y] == null && chemin.get(chemin.size()-1) != "z") {
				chemin.add("s");
				deplacementMonstre(x+1,y);
			}
			else if ( (x-1) >= 0
					&& tabMur[x-1][y] == null
					&& chemin.get(chemin.size()-1) != "s") {
				chemin.add("z");
				deplacementMonstre(x-1,y);
			}
			else if ((y+1) < hauteur && tabMur[x][y+1] == null && chemin.get(chemin.size()-1) != "q") {
				chemin.add("d");
				deplacementMonstre(x,y+1);
			}
			else if ((y-1) >= 0 && tabMur[x][y-1] == null && chemin.get(chemin.size()-1) != "d") {
				chemin.add("q");
				deplacementMonstre(x,y-1);
			}
		}

		return chemin;
	}



	public void depMonstre(int[][] tab) {
		for (Monstre m: listeMonstres) {
			deplacementIntelligentMonstre(m, tab);
		}
	}

	public int[][] tabCheminMonstre() {
		int x = (int)lesHeros.get(0).getX();
		int y = (int)lesHeros.get(0).getY();
		int[][] tab = new int[longueur][hauteur];
		boolean trouve = false;

		// initialise les cases a -1 qui signifie pas encore calcule
		for(int v=0;v<tab.length;v++) {
			Arrays.fill(tab[v], -1);
		}

		// positionne les monstres dans le tableau de recherche de chemin avec une valeur differente de -1
		/*for (Monstre ms: listeMonstres) {
			tab[ms.getX()][ms.getY()] = -2;
		}*/

		tab[x][y] = 0;
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < longueur; j++) {
				if (tabMur[j][i] != null) tab[j][i] = -5;
			}
		}
		remplirTableau(tab, x, y);
		return tab;
	}


	/**
	 * A COMPLETER
	 * Deplace ce monstre vers le heros en prenant le chemin le plus court.
	 * @param monstre
	 * @param tab tableau du labyrinthe
	 * @see #calculChemin(int[][], Monstre)
	 */
	public void deplacementIntelligentMonstre(Monstre monstre, int[][] tab) {

		ArrayList<String> dep = calculChemin(tab, monstre);
		for (String ss : dep ) System.out.print(ss + "  " );
		System.out.println();
		// recupere le premier deplacement du monstre
		String s = dep.get(0);
		switch(s) {
		case "s":
			monstre.goBas();
			break;
		case "z":
			monstre.goHaut();
			break;
		case "q":
			monstre.goGauche();
			break;
		case "d":
			monstre.goDroite();
			break;
		default:
			break;
		}
	}


	/**
	 * A COMPLETER
	 * Cherche un chemin du monstre vers le heros en valuant les cases
	 * la valuation correspond au nombre de deplacement necessaire en partant du heros
	 * @param tab
	 * @param x
	 * @param y
	 */
	private void remplirTableau(int[][] tab,int x,int y) {
		boolean faire[] = new boolean[4];
		// tableau qui indique si la case a deja ete value
		Arrays.fill(faire, false);

		if (y >= 0 && y < hauteur && x >= 0 && x < longueur) {
			if ( /*verifChemin(tab) == false  && */ tabMur[x][y] == null) {
				// regarde pour chaque direction s'il existe un chemin dont la case n'a pas deja ete value
				if ((x+1) < longueur && tabMur[x+1][y] == null && tab[x+1][y] == -1 ) {
					tab[x+1][y] = tab[x][y] + 1;
					faire[0] = true;
				}
				if ( (x-1) >= 0 && tabMur[x-1][y] == null && tab[x-1][y] == -1) {
					tab[x-1][y] = tab[x][y] + 1;
					faire[1] = true;
				}
				if ((y+1) < hauteur && tabMur[x][y+1] == null && tab[x][y+1] == -1) {
					tab[x][y+1] = tab[x][y] + 1;
					faire[2] = true;
				}
				if ((y-1) >= 0 && tabMur[x][y-1] == null && tab[x][y-1] == -1) {
					tab[x][y-1] = tab[x][y] + 1;
					faire[3] = true;
				}

				if (faire[0] ) remplirTableau(tab, x+1, y);
				if (faire[1] ) remplirTableau(tab, x-1, y);
				if (faire[2] ) remplirTableau(tab, x, y+1);
				if (faire[3] ) remplirTableau(tab, x, y-1);
			}
		}
	}


	/**
	 * A COMPLETER
	 * Calcul du chemin le plus cours entre ce monstre et le heros.
	 * @param t
	 * @param monstre
	 * @return liste Liste des directions que ce monstre doit prendre pour aller a la position du heros.
	 */
	public ArrayList<String> calculChemin(int[][] t, Monstre monstre) {
		int x = (int)monstre.getX() /LARGEUR_MUR;
		int y = (int)monstre.getY() /LARGEUR_MUR;
		ArrayList<String> liste = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		String s = "";

		int xh = (int)lesHeros.get(0).getX() /LARGEUR_MUR, yh = (int)lesHeros.get(0).getY() /LARGEUR_MUR;
		if (x == xh && y == yh) {
			liste.add("");
		}
		// Si le monstre est a 1 case du heros
		else if (x == xh) {
			if (y + 1 == yh) s = "s";
			else s = "z";
			liste.add(0,s);
		} else if (y == yh) {
			if (x + 1 == xh) s = "d";
			else s = "q";
			liste.add(0,s);
		} else {
			// sinon on lance la boucle  pour chercher le chemin le plus court
			while (min > 1 && x >= 0 && y >= 0) {
				// on stocke le chemin du heros vers le monstre
				// tant quon est pas arrive a la case de deplacement +1 du monstre
				s = "";
				int i = -1,j = -1;
				if ((x+1) < longueur && t[x+1][y] > 0 && min > t[x+1][y] ) {
					min = t[x+1][y];
					i = x+1;
					j = y;
					s = "d";
				}
				if ( (x-1) >= 0 && t[x-1][y] > 0 && min > t[x-1][y] ) {
					min =  t[x-1][y];
					i = x-1;
					j = y;
					s = "q";
				}
				if ((y+1) < hauteur && t[x][y+1] > 0 && min > t[x][y+1] ) {
					min = t[x][y+1];
					i = x;
					j = y+1;
					s = "s";
				}
				if ((y-1) >= 0 && t[x][y-1] > 0 && min > t[x][y-1] ) {
					min =  t[x][y-1];
					i = x;
					j = y-1;
					s = "z";
				}
				x = i;
				y = j;
				System.out.println(t[x][y] + " " +s );
				liste.add(s);
			}
		}
		return liste;
	}

	//  verifie si un chemin vers le heros a ete trouve
	// donc on regarde si une des 4 cases autour du heros est numerote

	/**
	 * A COMPLETER
	 * verifie si un chemin vers le heros a ete trouve
	 * donc on regarde si une des 4 cases autour du heros est numerote
	 * @param tab
	 * @return
	 */
	public boolean verifChemin(int[][] tab) {
		boolean trouve = false;
		int x = (int)lesHeros.get(0).getX()/LARGEUR_MUR;
		int y = (int)lesHeros.get(0).getY()/HAUTEUR_MUR;
		if ((x+1) < longueur) {
			if (tab[x+1][y] != -1 ) trouve = true;
		}
		if ( (x-1) >= 0) {
			if (tab[x-1][y] != -1 ) trouve = true;
		}
		if ((y+1) < hauteur) {
			if (tab[x][y+1] != -1 ) trouve = true;
		}
		if ((y-1) >= 0 ) {
			if (tab[x][y-1] != -1 ) trouve = true;
		}
		return trouve;
	}

	public int getLongeurCarte(){
		return  longeurCarte;
	}

	public int getHauteurCarte(){
		return hauteurCarte;
	}

	public ArrayList<Heros> getLesHeros(){
		return  lesHeros;
	}

	public Item[][] getLesObjets(){
		return lesObjets;
	}

	public boolean isTresorTrouver() {
		return tresorTrouver;
	}

	public int getLongueurLaby(){
		return longueur*LARGEUR_MUR ;
	}

	public int getHauteurLaby(){
		return hauteur*HAUTEUR_MUR;
	}
}