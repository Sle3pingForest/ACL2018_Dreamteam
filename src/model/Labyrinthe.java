package model;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
import org.newdawn.slick.geom.Rectangle;


public class Labyrinthe implements Serializable{

	public final static int HAUTEUR_MUR = 31;
	public final static int LARGEUR_MUR = 32;

	private int longeurCarte, hauteurCarte;

	private int[] tabNomMonstre ={FabriqueMonstre.SOLDAT,FabriqueMonstre.SOLDAT,FabriqueMonstre.SOLDAT,FabriqueMonstre.SOLDAT};
	private Item [][]lesObjets;
	private int [] tabNomItem = {ItemFactory.EPEE, ItemFactory.PIEGE};
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

	private Heros link;


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

		link = new Heros(xDebut*LARGEUR_MUR,yDebut*HAUTEUR_MUR, "Link");
		lesHeros.add(link);
		

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
		while(i < longueur-1&& tabMur[i][y] == null){
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

		for(Monstre m : listeMonstres){
			if(m.getPointVie() >0 ){
				m.updateMonstre(this, delta);
			}
		}
		if(!lesHeros.get(0).estMort()) {
			lesHeros.get(0).updateHeros(this, delta);
			collison();
		}

	}

	public void attaquer(){
		if(!getHeros(0).estMort()) {
			getHeros(0).attaquer(this);
		}
	}
	
	public void tirer(){
		if(!getHeros(0).estMort()) {
			getHeros(0).tirer(this);
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
	
	
	public void attaquerStop(){
		lesHeros.get(0).attaquerStop();
	}


	/**
	 * Verifie la collision entre les monstres et les heros
	 */
	public void collison(){
		if(!lesHeros.get(0).estInvulnerable()) {
			Rectangle boxHero = lesHeros.get(0).getBoxColliderDegat();
			Rectangle boxMonstre;
			for (Monstre monstre : listeMonstres) {
				if (!monstre.estMort()) {
					boxMonstre = monstre.getBoxColliderDegat();
					if (boxHero.intersects(boxMonstre)) {
						lesHeros.get(0).setPointVie(monstre.getAttaque());
						lesHeros.get(0).mettreInvulnerable();
					}
				}
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

	public Heros getLink() {
		return link;
	}
}