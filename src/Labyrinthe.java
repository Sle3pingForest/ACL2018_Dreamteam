import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import model.mur.Mur;
import model.mur.MurNormal;
import model.personnages.Heros;
import model.personnages.monstres.FabriqueMonstre;
import model.personnages.monstres.Monstre;


public class Labyrinthe {
	private String[] tabNomMonstre ={"Orc","Dragon"};
	private int longeur = 10, hauteur = 7;
	private Mur[][] tabMur ;
	private Heros heros;
	private ArrayList<Monstre> listeMonstres;
	private FabriqueMonstre creationMonstres;
	
	
	public Labyrinthe(String fichierName, String nom, int nombre){
		this.heros =  new Heros(0,0, nom);
		this.tabMur = new Mur[longeur][hauteur];
		this.creationMonstres = new FabriqueMonstre();
		this.listeMonstres = new ArrayList<>();
		constructionLabyrinthe(fichierName);
		creationMonstres(nombre);
		
	}
	
	private void creationMonstres(int nombreMonstre){
		
		for(int i = 0; i < nombreMonstre; ++i){
			int rng  = (int)(Math.random() * (tabNomMonstre.length)) ;
			int posX = (int)(Math.random() * (10));
			int posY = (int)(Math.random() * (7));
			boolean correct = false;
			while(!correct){
				if(tabMur[posX][posY] == null){
					correct = true;
					listeMonstres.add(this.creationMonstres.creerMonstres(tabNomMonstre[rng], posX, posY));
				
				}
				else{
					posX = (int)(Math.random() * (10));
					posY = (int)(Math.random() * (7));
				}
			}
		}
			
	}
	
	public void constructionLabyrinthe(String fichierName){
		 
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
		            for(int i = 0; i < this.longeur; i++){
		            	char c = line.charAt(i);
		            	if(c=='1'){
		            		this.tabMur[i][xMur] = new MurNormal(i, xMur);
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
	
	public void deplacerHerosHaut(){
		int y = heros.getY();
		int x = heros.getX();
		
		if(y > 0 && tabMur[x][y-1] == null){
			heros.goHaut();
		}
	}
	
	public void deplacerHerosBas(){
		int y = heros.getY();
		int x = heros.getX();
		
		if(y < hauteur-1 && tabMur[x][y+1] == null){
			heros.goBas();
		}
	}
	
	public void deplacerHerosDroite(){
		int y = heros.getY();
		int x = heros.getX();
		
		if(x < longeur-1 && tabMur[x +1][y] == null){
			heros.goDroite();
		}
	}
	
	public void deplacerHerosGauche(){
		int y = heros.getY();
		int x = heros.getX();
		
		if(x > 0 && tabMur[x-1][y] == null){
			heros.goGauche();
		}
	}
	
	public void afficher(){
		int x =  heros.getX();
		int y = heros.getY();
		for(int i = 0 ; i < this.hauteur;i++){
			for( int j = 0; j < this.longeur ; j++){
				if(i == y && j == x ){
					System.out.print("H");
				}
				else{
					if(tabMur[j][i] != null){
						System.out.print("+");
					}
					else{
						for (Monstre m : listeMonstres){
							if(m.getX() == j && i == m.getY()){
								System.out.print("k");
							}
						}

						System.out.print("=");
					}
				}
				
			}
			System.out.println();
		}
	}

	public Heros getHeros() {
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
		
		/*----------*/
		
		ArrayList<String> chemin = new ArrayList<>();
		chemin.add(t);
		
		if (x == heros.getX() && y == heros.getY()) {
			return chemin;
		} else {
			
			if ((x+1) < longeur && tabMur[x+1][y] == null && chemin.get(chemin.size()-1) != "z") {
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
		
		/*--------------*/
		return chemin;
	}
	
	public int[][] deplacement(int x,int y) {
		int[][] tab = new int[longeur][hauteur];
		boolean trouve = false;
		
		for(int v=0;v<tab.length;v++) {
			Arrays.fill(tab[v], -1);
		}
		tab[heros.getX()][heros.getY()] = -2;
		tab[x][y] = 0;
		for (int i = 0; i < longeur; i++) {
			for (int j = 0; j < hauteur; j++) {
				if (tabMur[i][j] != null) tab[i][j] = -5;
			}
		}
		remplirTableau(tab, x, y);
		
		
		return tab;
	}
	
	public void remplirTableau(int[][] tab,int x,int y) {
		boolean faire[] = new boolean[4];
		Arrays.fill(faire, false);
		if (y >= 0 && y < hauteur && x >= 0 && x < longeur) {
			if (verifChemin(tab) == false && tabMur[x][y] == null) {
				if ((x+1) < longeur && tabMur[x+1][y] == null && tab[x+1][y] == -1 ) {
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
	
	public ArrayList<String> calculChemin(int[][] t) {
		int x = heros.getX();
		int y = heros.getY();
		ArrayList<String> tab = new ArrayList<>();
		
		int min = Integer.MAX_VALUE;
		while (min > 1 && x >= 0 && y >= 0) {
			String s = "";
			int i = -1,j = -1;
			if ((x+1) < longeur && t[x+1][y] > 0 && min > t[x+1][y]) {
				min = t[x+1][y];
				i = x+1;
				j = y;
				s = "q";
			}
			if ( (x-1) >= 0 && t[x-1][y] > 0 && min > t[x-1][y]) {
				min =  t[x+1][y];
				i = x-1;
				j = y;
				s = "d";
			}
			if ((y+1) < hauteur && t[x][y+1] > 0 && min > t[x][y+1]) {
				min = t[x+1][y];
				i = x;
				j = y+1;
				s = "z";
			}
			if ((y-1) >= 0 && t[x][y-1] > 0 && min > t[x][y-1]) {
				min =  t[x+1][y];
				i = x;
				j = y-1;
				s = "s";
			}
			x = i;
			y = j;
			tab.add(s);
		}
		return tab;
	}
	
	public boolean verifChemin(int[][] tab) {
		boolean trouve = false;
		int x = heros.getX();
		int y = heros.getY();
			if ((x+1) < longeur) {
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
	
	
	
	public static void main(String[] args) {
		Labyrinthe laby = new Labyrinthe("src/murLvl1.txt", "SleepingForest",1);
		
		int x = laby.getListeMonstres().get(0).getX();
		int y = laby.getListeMonstres().get(0).getY();
		
		System.out.println("MONSTRE " + x + "   " + y);
		int[][]tab = laby.deplacement(x,y);
		
		
		for(int v=0;v<tab[0].length;v++) {
			for(int w=0;w<tab.length;w++) 
				System.out.print(tab[w][v] + " ");
			
			System.out.println();
		}
		

		ArrayList<String> l = laby.calculChemin(tab);

		System.out.println("test");
		System.out.println(l.size());
		System.out.println(l.get(l.size()-1));
		
	}


	

}
