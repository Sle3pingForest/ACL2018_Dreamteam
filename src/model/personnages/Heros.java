package model.personnages;

import model.Item.Item;
import model.Item.Piege;
import model.Item.Ramassable;
import model.Item.Tresor;
import model.Labyrinthe;
import model.mur.Mur;

import java.io.Serializable;
import java.util.ArrayList;

import model.personnages.monstres.Monstre;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;


public class Heros extends Personnage {

    private Tresor tresorDeMap = null;
    private ArrayList<Item> inventaire;

    private int pointVie;


    protected int tailleInventaire = 10;


    public Heros(float x, float y, String nom){
    	super(x,y);
    	this.pointVie = 5;
    	this.nom = nom;
    	this.attaque = 3;
    	this.defense = 0;
    	inventaire = new ArrayList<Item>();
    	tailleInventaire = 10;
    }
    
    public void charge(Heros h) {
    	this.x = h.getX();
    	this.y = h.getY();
    	this.pointVie = h.getPointVie();
    	this.nom = h.getNom();
    	this.attaque = h.getAttaque();
    	this.defense = h.getDefense();
    	inventaire = h.getInventaire();
    }

    public ArrayList<Item> getInventaire() {
		return inventaire;
	}

	public String toString(){
        String donnee = "Le Heros est en  x :"+x+" , +y : "+y;

        return donnee;
    }
    

    public String getNom() {
		return nom;
	}

    public int getTailleInventaire() {
        return tailleInventaire;
    }

    public void setTailleInventaire(int tailleInventaire) {
        this.tailleInventaire = tailleInventaire;
    }
    
    public int getDirectionActu(){
    	return directionActu;
    }
    
    public Tresor getTresorDeMap(){
        return  tresorDeMap;
    }

    public int getPointVie() {
        return pointVie;
    }

    public void setPointVie(int pointVie) {
        this.pointVie = pointVie;
    }

    public void ajouterAInventaire(Item i){
        if(!i.getClass().getName().equals("model.Item.Tresor")) {
            if(!i.isRamasser() && i.isRamassable()) {
                if (inventaire.size() < tailleInventaire) {
                    inventaire.add(i);
                    i.ramasser();
                    System.out.println(inventaire);
                }
            }
            else if(i.getClass().getName().equals("model.Item.Piege")){
                if(!i.isRamasser()){
                    System.out.println("Avant" + this.pointVie);
                    i.blesser(this);
                    i.ramasser();
                    System.out.println("Après" + this.pointVie);
                }
            }
        }else{
            tresorDeMap = (Tresor)i;
        }
    }

    /**
     * fonction de mise a jour du Heros
     * @param lab labyrinthe
     * @param delta
     * @throws SlickException
     */
    public void updateHeros(Labyrinthe lab, int delta) throws SlickException{

        float vitesseActu = delta*Heros.VITESSE;

		/*int xH = (int)(lesHeros.get(0).getX()/LARGEUR_MUR);
		int yH = (int)(lesHeros.get(0).getY()/HAUTEUR_MUR);
		if(lesObjets[xH][yH] != null){
			//tresorTrouver = true;
			lesHeros.get(0).ajouterAInventaire(lesObjets[xH][yH]);
			lesObjets[xH][yH].isRamasser();
			System.err.println("j ai trouve mon tresort");
		}*/

        float futureX = x + horizontal * vitesseActu;
        float futureY = y + vertical * vitesseActu;
        if(futureX > 0 && futureX < lab.getLongeurCarte() - Labyrinthe.LARGEUR_MUR && futureY > 0 && futureY < lab.getHauteurCarte()- Labyrinthe.HAUTEUR_MUR){
            if (getCollision()) {
                if(vertical == -1){
                    if(!collisionHaut(lab, futureX, futureY)){
                        setY(futureY);
                    }
                }
                if(vertical == 1){
                    if(!collisionBas( lab,futureX, futureY)){
                        setY(futureY);
                    }
                }

                if(horizontal == -1){
                    if(!collisionGauche(lab, futureX, futureY)){
                        setX(futureX);
                    }
                }
                if(horizontal == 1){
                    if(!collisionDroite(lab, futureX, futureY)){
                        setX(futureX);
                    }
                }
            } else {
                setX(futureX);
                setY(futureY);
            }
        }

    }

    /**
     * Fonction permettant au heros d'attaquer
     * @param lab
     */
    public void attaquer(Labyrinthe lab){

        attaquer();
        float xH = getX()/Labyrinthe.LARGEUR_MUR;
        float yH = getY()/Labyrinthe.HAUTEUR_MUR;
        for(Monstre m : lab.getListeMonstres()){
            float xM = m.getX()/Labyrinthe.LARGEUR_MUR;
            float yM = m.getY()/Labyrinthe.HAUTEUR_MUR;
            boolean estToucher = false;
            if(Math.abs(yH -yM) < 1 && Math.abs(xH-xM) < 1  ){
                estToucher = true;
            }

            if(estToucher){
                setPointVie(m.getAttaque());
                m.setPointVie(getAttaque());
                if(m.getPointVie() <= 0){
                    m.mortMonstres();
                }
            }
            if(getPointVie() <= 0){
                Labyrinthe.MORT_HEROS = true;
                mort();
            }
        }

    }
}
