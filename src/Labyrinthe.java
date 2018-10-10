import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.Personnages.Heros;
import model.mur.Mur;
import model.mur.MurNormal;


public class Labyrinthe {
	
	protected int longeur = 10, hauteur = 7;
	public Mur[][] tabMur ;
	public Heros heros;
	
	public Labyrinthe(String fichierName){
		this.tabMur = new Mur[longeur][hauteur];
		constructionLabyrinthe(fichierName);
		this.heros =  new Heros(0,0);
		
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
		            		Mur m = new MurNormal(xMur, i);
		            		this.tabMur[i][xMur] = m;
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
		
		if(y < hauteur && tabMur[x][y+1] == null){
			heros.goBas();
		}
	}
	
	public void deplacerDroite(){
		int y = heros.getY();
		int x = heros.getX();
		
		if(x < longeur && tabMur[x +1][y] == null){
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
				}else{
					if(tabMur[j][i] != null){
						System.out.print("m");
					}
					else{
						System.out.print("0");
					}
				}
				
			}
			System.out.println();
		}
	}
}
