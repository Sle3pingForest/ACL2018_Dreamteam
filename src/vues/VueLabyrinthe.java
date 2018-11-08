package vues;

import model.Item.Item;
import model.Item.Tresor;
import model.Labyrinthe;
import model.mur.Mur;

import java.io.Serializable;
import java.util.ArrayList;

import model.personnages.Heros;
import model.personnages.monstres.Dragon;
import model.personnages.monstres.Monstre;
import model.personnages.monstres.Orc;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import vues.VueItem.VueItem;
import vues.VueItem.VueTresor;
import vues.VueMonstres.VueDragon;
import vues.VueMonstres.VueMonstres;
import vues.VueMonstres.VueOrc;

public class VueLabyrinthe implements Serializable{

    private Labyrinthe lab;
    private ArrayList<VueHeros> lesHerosVue;
    private ArrayList<VueItem> lesObjetsVue;
    private ArrayList<VueMonstres> lesMonstresVue;

    
    private static VueLabyrinthe instance = null;

	public static VueLabyrinthe getInstance() throws SlickException {
		if (instance == null) {
			instance = new VueLabyrinthe();
		}
		return instance;
	}

    private VueLabyrinthe() throws SlickException {
        VueElementDecor.mettreForet();
        //lab = new model.Labyrinthe("murLvl1.txt","link",10);
        lesHerosVue = new ArrayList<VueHeros>();
        lesObjetsVue = new ArrayList<VueItem>();
        lesMonstresVue = new ArrayList<VueMonstres>();

    }

    public void setLab(Labyrinthe lab) throws SlickException {
	    this.lab = lab;

        ArrayList<Heros> lesHeros = lab.getLesHeros();
        for(int i = 0 ; i < lesHeros.size() ; i++){
            lesHerosVue.add(new VueHeros(VueHeros.BLEU,lesHeros.get(i)));
        }
        
        ArrayList<Monstre> lesMonstres = lab.getListeMonstres();
        for (int k = 0; k < lesMonstres.size() ; k++ ){
        	if(lesMonstres.get(k).getNom() == "orc"){
        		lesMonstresVue.add(new VueOrc((Orc)lesMonstres.get(k))) ;
        	}
        	if(lesMonstres.get(k).getNom() == "dragon"){
        		lesMonstresVue.add(new VueDragon((Dragon)lesMonstres.get(k))) ;
        	}
			
        }
        Item[][]lesObjets = lab.getLesObjets();
        for(int i = 0 ; i < lesObjets.length ; i++){
            for(int j = 0 ; j < lesObjets[i].length ; j++) {
                Item itemActu = lesObjets[i][j];
                if(itemActu != null){
                    switch (itemActu.getClass().getName()) {
                        case "model.Item.Tresor":

                            lesObjetsVue.add(new VueTresor((Tresor) itemActu));
                            break;
                    }
                }
            }

        }
    }

    public void render(GameContainer container, Graphics g,int xMin , int xMax , int yMin , int yMax){
        Mur[][] mur = lab.getTabMur();
        int hauteur = mur[0].length;
        int largeur = mur.length;


        int largeurMin = xMin/Labyrinthe.LARGEUR_MUR;
        int largeurMax = xMax/Labyrinthe.LARGEUR_MUR;

        int hauteurMin = yMin/Labyrinthe.HAUTEUR_MUR;
        int hauteurMax = yMax/Labyrinthe.HAUTEUR_MUR;


        int xActu = largeurMin*Labyrinthe.LARGEUR_MUR;
        int yActu = hauteurMin*Labyrinthe.HAUTEUR_MUR;

        for(int i = hauteurMin ; i < hauteur && i <= hauteurMax ;i++){
            for(int j = largeurMin ; j < largeur && j <= largeurMax ; j++){
                g.drawImage(VueElementDecor.getChemin(),xActu,yActu);
                if(mur[j][i]!= null){
                    g.drawImage(VueElementDecor.getMur(),xActu,yActu);
                }
                xActu += Labyrinthe.LARGEUR_MUR;
            }
            xActu = largeurMin*Labyrinthe.LARGEUR_MUR;
            yActu += Labyrinthe.HAUTEUR_MUR;
        }

        for(VueHeros h : lesHerosVue){
            h.render(container,g);
        }

        for(VueMonstres m : lesMonstresVue){
            m.render(container,g);
        }

        for(VueItem i : lesObjetsVue){
            i.render(container, g);
        }
    }

    public model.Labyrinthe getLab() {
        return lab;
    }



}
