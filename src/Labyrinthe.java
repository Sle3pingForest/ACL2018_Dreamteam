import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
					System.out.println(posX + " ," + posY);
				
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
	
	public void deplacerHaut(){
		int y = heros.getY();
		int x = heros.getX();
		
		if(y > 0 && tabMur[x][y-1] == null){
			heros.goHaut();
		}
	}
	
	public void deplacerBas(){
		int y = heros.getY();
		int x = heros.getX();
		
		if(y < hauteur-1 && tabMur[x][y+1] == null){
			heros.goBas();
		}
	}
	
	public void deplacerDroite(){
		int y = heros.getY();
		int x = heros.getX();
		
		if(x < longeur-1 && tabMur[x +1][y] == null){
			heros.goDroite();
		}
	}
	
	public void deplacerGauche(){
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
	

}
