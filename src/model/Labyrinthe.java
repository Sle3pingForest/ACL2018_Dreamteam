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

import model.Item.Item;
import model.Item.Tresor;
import model.mur.Mur;
import model.personnages.Heros;
import model.personnages.monstres.Dragon;
import model.personnages.monstres.FabriqueMonstre;
import model.personnages.monstres.Monstre;
import model.personnages.monstres.Orc;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import vues.VueLabyrinthe;


public class Labyrinthe implements Serializable{
	private int[] tabNomMonstre ={FabriqueMonstre.ORC,FabriqueMonstre.ORC};
	private Item [][]lesObjets;
	private int longueur , hauteur;
	private Mur[][] tabMur ;
	private ArrayList<Monstre> listeMonstres;
	private FabriqueMonstre creationMonstres;
	public static boolean MORT_HEROS = false;
	private ArrayList<Heros> lesHeros;
	private float multiplicateurProba = 1.2f;//Il pourrait varier aleatoirement
	private boolean tresorTrouver = false;
	public final static int HAUTEUR_MUR = 31;
	public final static int LARGEUR_MUR = 32;
    private final static int TAILLE_MAX_COULOIR = 6;

	private Random random = new Random();


	/* public Labyrinthe(String fichierName, String nom, int nombre){
        this.creationMonstres = new FabriqueMonstre();
        this.listeMonstres = new ArrayList<>();
        constructionLabyrinthe(fichierName);
        creationMonstres(nombre);
        lesHeros = new ArrayList<Heros>();
    }*/

	public Labyrinthe(int longeur ,int hauteur){
		this.longueur = longeur;
		this.hauteur = hauteur;
		this.creationMonstres = new FabriqueMonstre();
		lesHeros = new ArrayList<Heros>();
		lesObjets = new Item[longeur][hauteur];
		tabMur = new Mur[longeur][hauteur];
		this.listeMonstres = new ArrayList<>();




		/**** On rempli tout le laby  de mur ****/
		for(int i = 0; i < hauteur ;i++){
			for(int j = 0 ; j < longeur ; j++){
				tabMur[j][i] =  new Mur(j,i);
			}
		}

		/**** Et maintenant on creuse ****/
		creuse();
		produitMonstres(50);
	}

    private void creuse(){

        int rand,choix;
        ArrayList<Integer> listChoix = new ArrayList<Integer>();
        Mur murActu = null;
        int nbCaseACreuser = (int)(longueur*hauteur);
        int nbAEncoreCreuser = nbCaseACreuser;

        /***** On choisi aleatoirement le debut du laby la ou sera notre heros *****/
        int xDebut = random.nextInt(longueur-2)+1;
        int yDebut = random.nextInt(hauteur-2)+1;

        lesHeros.add(new Heros(xDebut* VueLabyrinthe.LARGEUR_MUR,yDebut*VueLabyrinthe.HAUTEUR_MUR, "Link"));


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
        lesObjets[murActu.getPosX()][murActu.getPosY()] = new Tresor(murActu.getPosX()* VueLabyrinthe.LARGEUR_MUR,murActu.getPosY()*VueLabyrinthe.HAUTEUR_MUR,null);
    }

    /**** On verifie si on peut creuser le mur ****/
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

	public void update(GameContainer container, int delta) throws SlickException {
		lesHeros.get(0).update(container,delta);
		if(lesHeros.get(0).getTresorDeMap() != null){
			tresorTrouver = true;
		}
		deplacerMonstres();
		for(Monstre m : listeMonstres){
			m.update(container, delta);
		}
	}

	public Mur[][] getTabMur(){
		return  tabMur;
	}
	//Construction Monstres et Labyrinthe
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

	private void iniLargeurHauteur(String fichierName){
		//On lis deja une fois le fichier pour connaitre la hauteur  oui c lourd  mais de toute facon no fichier ne feront probablement jamais plus de quelque Ko  donc  cela reste rapide
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

	public final static int BAS_M = 0;

	public final static int DROITE_M = 1;
	public final static int HAUT_M = 2;
	public final static int GAUCHE_M = 3;

	// Avancer
	public final static int AVANCER_BAS_M = 4;
	public final static int AVANCER_DROITE_M = 5;
	public final static int AVANCER_HAUT_M = 6;
	public final static int AVANCER_GAUCHE_M = 7;

	public void deplacerMonstres(){
		for(Monstre monstre :listeMonstres){

			int x = (int)monstre.getX()/ LARGEUR_MUR;
			int y = (int)(monstre.getY()) / HAUTEUR_MUR;
			int direction = 0;
			boolean check = false;

			int direcActu = monstre.getDirectionActu();

			if (direcActu == 0 || direcActu == 4) {
				//bas
				if(y+1 < hauteur-1 && tabMur[x][y+1] == null) direction = 4;
				else check = true;
			}
			else if (direcActu == 1 || direcActu == 5) {
				//droite
				if(x+1 < longueur-1 && tabMur[x+1][y] == null) direction = 1;
				else check = true;
			}
			else if (direcActu == 2 || direcActu == 6) {
				// haut
				if(y-1 > 0 && tabMur[x][y-1] == null) direction = 2;
				else check = true;
			} 
			else if (direcActu == 3 || direcActu == 7) {
				// gauche
				if (x-1 > 0 && tabMur[x-1][y] == null) direction = 3;
				else check = true;
			}

			if (check) direction = direction(monstre);

			if(direction == 1){
				monstre.goDroite();
			}
			if(direction == 2){
				monstre.goHaut();
			}

			if(direction == 3){
				monstre.goGauche();
			}

			if (direction == 4){
				monstre.goBas();
			}
		}
	}

	private int direction(Monstre monstre) {
		int x = (int)monstre.getX()/ LARGEUR_MUR;
		int y = (int)(monstre.getY()) / HAUTEUR_MUR;
		ArrayList<Integer> cheminValide = new ArrayList<>();
		if( x+1 < longueur-1 && tabMur[x+1][y] == null){
			cheminValide.add(1);
		}
		if(y-1 > 0 && tabMur[x][y-1] == null ){
			cheminValide.add(2);
		}
		if(x-1 > 0 && tabMur[x-1][y] == null ){
			cheminValide.add(3);
		}
		if(y+1 < hauteur-1 && tabMur[x][y+1] == null){
			cheminValide.add(4);
		}

			Random r = new Random();
			int rd =  r.nextInt(cheminValide.size());
			int direction = cheminValide.get(rd);
		

		return direction;
	}

	public void collison(){
		for(Monstre monstre: listeMonstres){
			if(lesHeros.get(0).getX() == monstre.getX() && lesHeros.get(0).getY() == monstre.getY()){
				MORT_HEROS = true;
			}
		}
	}

	//AFFICHAGE
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
								if(m instanceof Orc){
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

	// fait bouger le monstre vers le heros de facon "intelligente" chemin le plus court
	// calcul les chemins du heros vers les monstres
	public void deplacementIntelligentMonstre(Monstre m, int[][] tab) {

		ArrayList<String> dep = calculChemin(tab, m);
		for (String ss : dep ) System.out.print(ss + "  " );
		System.out.println();
		// recupere le premier deplacement du monstre
		String s = dep.get(0);
		switch(s) {
		case "s":
			m.goBas();
			break;
		case "z":
			m.goHaut();
			break;
		case "q":
			m.goGauche();
			break;
		case "d":
			m.goDroite();
			break;
		default:
			break;
		}
	}


	// cherche un chemin du monstre vers le heros en valuant les cases
	// la valuation correspond au nombre de deplacement necessaire en partant du heros
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


	// Calcul du chemin de depart (monstre) vers larrivee (heros)
	public ArrayList<String> calculChemin(int[][] t, Monstre m) {
		int x = (int)m.getX();
		int y = (int)m.getY();
		ArrayList<String> tab = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		String s = "";

		int xh = (int)lesHeros.get(0).getX(), yh = (int)lesHeros.get(0).getY();
		if (x == xh && y == yh) {
			tab.add("");
		}
		// Si le monstre est a 1 case du heros
		else if (x == xh) {
			if (y + 1 == yh) s = "s";
			else s = "z";
			tab.add(0,s);
		} else if (y == yh) {
			if (x + 1 == xh) s = "d";
			else s = "q";
			tab.add(0,s);
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
				tab.add(s);
			}
		}
		return tab;
	}

	//  verifie si un chemin vers le heros a ete trouve
	// donc on regarde si une des 4 cases autour du heros est numerote
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