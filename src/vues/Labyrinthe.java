package vues;

import model.mur.Mur;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Labyrinthe {

    public final static int HAUTEUR_MUR = 31;
    public final static int LARGEUR_MUR = 32;
    private model.Labyrinthe lab;

    public Labyrinthe() throws SlickException {
        ElementDecor.mettreForet();
        lab = new model.Labyrinthe("murLvl1.txt","link",10);
    }

    public void render(GameContainer container, Graphics g){
        Mur[][] mur = lab.getTabMur();
        int hauteur = mur[0].length;
        int largeur = mur.length;

        int xActu = 0;
        int yActu = 0;

        for(int i = 0 ; i < hauteur ;i++){
            for(int j = 0 ; j < largeur ; j++){
                g.drawImage(ElementDecor.getChemin(),xActu,yActu);
                if(mur[j][i]!= null){
                    g.drawImage(ElementDecor.getMur(),xActu,yActu);
                }
                xActu += LARGEUR_MUR;
            }
            xActu = 0;
            yActu += HAUTEUR_MUR;
        }

    }

    public model.Labyrinthe getLab() {
        return lab;
    }
}
