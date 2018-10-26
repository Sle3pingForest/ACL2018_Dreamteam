package vues;

import model.mur.Mur;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class VueLabyrinthe {

    public final static int HAUTEUR_MUR = 31;
    public final static int LARGEUR_MUR = 32;
    private model.Labyrinthe lab;
    private int longeurCarte;
    private int hauteurCarte;
    
    private static VueLabyrinthe instance = null;
	public static VueLabyrinthe getInstance() throws SlickException {
		if (instance == null) {
			instance = new VueLabyrinthe();
		}
		return instance;
	}

    private VueLabyrinthe() throws SlickException {
        VueElementDecor.mettreForet();
        lab = new model.Labyrinthe("murLvl1.txt","link",10);
        longeurCarte = lab.getTabMur().length * LARGEUR_MUR;
        hauteurCarte = lab.getTabMur()[0].length * HAUTEUR_MUR;
    }

    public void render(GameContainer container, Graphics g,int xMin , int xMax , int yMin , int yMax){
        Mur[][] mur = lab.getTabMur();
        int hauteur = mur[0].length;
        int largeur = mur.length;


        int largeurMin = xMin/LARGEUR_MUR;
        int largeurMax = xMax/LARGEUR_MUR;

        int hauteurMin = yMin/HAUTEUR_MUR;
        int hauteurMax = yMax/HAUTEUR_MUR;


        int xActu = largeurMin*LARGEUR_MUR;
        int yActu = hauteurMin*HAUTEUR_MUR;

        for(int i = hauteurMin ; i < hauteur && i <= hauteurMax ;i++){
            for(int j = largeurMin ; j < largeur && j <= largeurMax ; j++){
                g.drawImage(VueElementDecor.getChemin(),xActu,yActu);
                if(mur[j][i]!= null){
                    g.drawImage(VueElementDecor.getMur(),xActu,yActu);
                }
                xActu += LARGEUR_MUR;
            }
            xActu = largeurMin*LARGEUR_MUR;
            yActu += HAUTEUR_MUR;
        }
    }

    public model.Labyrinthe getLab() {
        return lab;
    }

    public int getLongeurCarte(){
        return  longeurCarte;
    }

    public int getHauteurCarte(){
        return hauteurCarte;
    }
}