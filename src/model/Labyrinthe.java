package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import model.mur.Mur;
import model.personnages.Heros;
import model.personnages.Personnage;
import model.personnages.monstres.Dragon;
import model.personnages.monstres.FabriqueMonstre;
import model.personnages.monstres.Monstre;
import model.personnages.monstres.Orc;


public class Labyrinthe {
    private int[] tabNomMonstre ={FabriqueMonstre.ORC,FabriqueMonstre.DRAGON};
    private int longueur , hauteur;
    private Mur[][] tabMur ;
    private Personnage heros;
    private ArrayList<Monstre> listeMonstres;
    private FabriqueMonstre creationMonstres;
    public static boolean MORT_HEROS = false;


    public Labyrinthe(String fichierName, String nom, int nombre){
        this.heros =  new Heros(0,0, nom);
        this.creationMonstres = new FabriqueMonstre();
        this.listeMonstres = new ArrayList<>();
        constructionLabyrinthe(fichierName);
        creationMonstres(nombre);

    }

    public Mur[][] getTabMur(){
        return  tabMur;
    }
    //Construction Monstres et Labyrinthe
    private void creationMonstres(int nombreMonstre){

        for(int i = 0; i < nombreMonstre; ++i){
            int rng  = (int)(Math.random() * (tabNomMonstre.length)) ;

            int posX = (int)(Math.random() * (longueur));
            int posY = (int)(Math.random() * (hauteur));
            boolean correct = false;
            while(!correct){
                if(tabMur[posX][posY] == null && posX != this.heros.getX() && posY != this.heros.getY()){
                    correct = true;
                    listeMonstres.add(this.creationMonstres.creerMonstres(tabNomMonstre[rng], posX, posY));

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
    public void deplacerHerosHaut(){
        int y = (int)heros.getY();
        int x = (int)heros.getX();

        if(y > 0 && tabMur[x][y-1] == null){
            heros.goHaut();
        }
    }

    public void deplacerHerosBas(){
        int y = (int)heros.getY();
        int x = (int)heros.getX();

        if(y < hauteur-1 && tabMur[x][y+1] == null){
            heros.goBas();
        }
    }

    public void deplacerHerosDroite(){
        int y = (int)heros.getY();
        int x = (int)heros.getX();

        if(x < longueur-1 && tabMur[x +1][y] == null){
            heros.goDroite();
        }
    }

    public void deplacerHerosGauche(){
        int y = (int)heros.getY();
        int x = (int)heros.getX();

        if(x > 0 && tabMur[x-1][y] == null){
            heros.goGauche();
        }
    }

    public void deplacerMonstres(){
        for(Monstre monstre :listeMonstres){
            int x = (int)monstre.getX();
            int y =  (int)monstre.getY();
            Random r = new Random();
            int direction =  r.nextInt(5);

            if(direction == 1 && x < longueur-1 && tabMur[x +1][y] == null){
                monstre.goDroite();
            }
            if(direction == 2 && y > 0 && tabMur[x][y-1] == null ){
                monstre.goHaut();
            }

            if(direction == 3 && x > 0 && tabMur[x-1][y] == null ){
                monstre.goGauche();
            }
            if (direction == 4 && y < hauteur-1 && tabMur[x][y+1] == null){
                monstre.goBas();
            }
        }
    }

    public void collison(){
        for(Monstre monstre: listeMonstres){
            if(this.heros.getX() == monstre.getX() && this.heros.getY() == monstre.getY()){
                MORT_HEROS = true;
            }
        }
    }

    //AFFICHAGE
    public void afficher(){
        int x =  (int)heros.getX();
        int y = (int)heros.getY();
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
    public Personnage getHeros() {
        return heros;
    }

    public void setHeros(Heros heros) {
        this.heros = heros;
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

        if (x == heros.getX() && y == heros.getY()) {
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
        int x = (int)heros.getX();
        int y = (int)heros.getY();
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

        int xh = (int)heros.getX(), yh = (int)heros.getY();
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
/*
	//  verifie si un chemin vers le heros a ete trouve
	// donc on regarde si une des 4 cases autour du heros est numerote
	public boolean verifChemin(int[][] tab) {
		boolean trouve = false;
		int x = heros.getX();
		int y = heros.getY();
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
*/




}