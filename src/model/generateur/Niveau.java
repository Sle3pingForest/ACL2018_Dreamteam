package model.generateur;

import model.Item.Epee;
import model.Item.Item;
import model.Item.Tresor;
import model.mur.Mur;
import model.personnages.monstres.Dragon;
import model.personnages.monstres.Monstre;
import model.personnages.monstres.Soldat;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Observable;

public class Niveau extends Observable implements Serializable {

    private Mur[][] lesMurs;
    private Monstre[][] checkMonstres;
    private ArrayList<Monstre> lesMonstres;
    private Item[][] lesItems;
    private String nom;
    private int xFin,yFin,xDebut,yDebut;
    private ArrayList<Mur> labEnCour;
    private ArrayList<Monstre>  labMonstres;
    private ArrayList<Item> labItems;
    private int hauteur =0,largeur=0;


    public Niveau(String nom) {
        this.nom = nom;
        this.xDebut = -1;
        this.yDebut = -1;
        this.xFin = -1;
        this.yFin = -1;
        labEnCour = new ArrayList<Mur>();
        labMonstres = new ArrayList<Monstre>();
        labItems = new ArrayList<Item>();
    }

    public void placerHeros(int x,int y){
        this.xDebut = x;
        this.yDebut =y;
    }

    /*public void ChangeDimension(int ligne, int colonne){
        for(int i = 0; i<) {

        }
    }*/

    /*public void ajouterLigne(int index ){
        ArrayList<Mur> ligneMur = new ArrayList<Mur>();
        ArrayList<Monstre> ligneMonstre = new ArrayList<Monstre>();
        ArrayList<Item> ligneItemps = new ArrayList<Item>();

        for(int i = 0 ; i < largeur ; i++){
            ligneMur.add(new Mur(i,hauteur));
            ligneItemps.add(null);
            ligneMonstre.add(null);
        }
        labEnCour.add(index,ligneMur);
        labMonstres.add(index,ligneMonstre);
        labItems.add(index,ligneItemps);
        hauteur++;
    }*/

    /*public void ajouterColonne(int index){

        for(int i = 0 ; i < hauteur ; i++){
            labEnCour.get(i).add(index,new Mur(largeur,i));
            labMonstres.get(i).add(index,null);
            labItems.get(i).add(index,null);
        }

        largeur++;

    }*/

    public void suppLigne(int index){

        if(index == yDebut){
            xDebut = -1;
            yDebut = -1;
        }

        if(index == yFin){
            xFin = -1;
            xFin = -1;
        }

        labEnCour.remove(index);
        labMonstres.remove(index);
        labItems.remove(index);
        hauteur--;
    }

    /*public void suppColonne(int index){

        if(index == xDebut){
            xDebut = -1;
            yDebut = -1;
        }

        if(index == xFin){
            xFin = -1;
            xFin = -1;
        }

        for(int i = 0 ; i < hauteur ; i++){
            labEnCour.get(i).remove(index);
            labMonstres.get(i).remove(index);
            labItems.get(i).remove(index);
        }

        largeur--;
    }*/

    public void ajouterMur(int x,int y){
        Mur mur = new Mur(x,y);
        lesMurs[x][y] = mur;
        if(checkMonstres[x][y] != null){
            labMonstres.remove(checkMonstres[x][y]);
            checkMonstres[x][y] = null;
        }

        if(lesItems[x][y] != null){
            lesItems[x][y] =null;
        }

    }

    /*public void suppMur(int x,int y){
        labEnCour.get(y).set(x,null);
    }*/

    public void ajouterMonstre(String type,int x,int y){
        Monstre monstre = null;
        if(type.equals("dragon")){
            monstre = new Dragon(x,y);
        }
        else if(type.equals("soldat")){
            monstre = new Soldat(x,y);
        }

        if(checkMonstres[x][y] != null){
            labMonstres.remove(checkMonstres[x][y]);
            checkMonstres[x][y] = null;
        }

        if(lesItems[x][y] != null){
            lesItems[x][y] =null;
        }

        if(lesMurs[x][y] != null){
            lesMurs[x][y] = null;
        }

        labMonstres.add(monstre);
        checkMonstres[x][y] = monstre;
    }

    /*public void suppMonstre(int x,int y){
        labMonstres.get(y).set(x,null);
    }*/


    public void ajouterItem(String type,int x,int y){
        Item item = null;
        if(type.equals("tresor")){
            item = new Tresor(x,y,null);
        }
        else if(type.equals("epee")){
            item = new Epee(x,y);
        }

        if(checkMonstres[x][y] != null){
            labMonstres.remove(checkMonstres[x][y]);
            checkMonstres[x][y] = null;
        }

        if(lesItems[x][y] != null){
            lesItems[x][y] =null;
        }

        if(lesMurs[x][y] != null){
            lesMurs[x][y] = null;
        }

        lesItems[x][y] = null;

    }

    /*public void suppItem(int x,int y){
        labMonstres.get(y).set(x,null);
    }*/

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteurLargeur(int hauteur,int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        setChanged();
        notifyObservers();
    }
    public int getLargeur() {
        return largeur;
    }


    public void suppCase(int x, int y) {

        checkMonstres[x][y] = null;
        lesItems[x][y] =null;
        lesMurs[x][y] = null;
        labMonstres.remove(checkMonstres[x][y]);

        if(this.xDebut == x && this.yDebut == y){
            this.xDebut = -1;
            this.yDebut = -1;
        }
    }

    public void serialize() throws IOException {
        File fichier =  new File("src/main/resources/sauvegardeEditeur/saveNiveau.save") ;

        // ouverture d'un flux sur un fichier
        ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(fichier)) ;

        // sérialization de l'objet
        oos.writeObject(this) ;
    }

    public void creerLab(){
        System.out.println(labEnCour.size());
        for(int i=0;i<labEnCour.size();i++){
            /*for(int j=0;j<labEnCour.get(0).size();j++){
                lesMurs[i][j]=labEnCour.get(i).get(j);
                System.out.println(lesMurs[i][j]);
            }*/
        }
    }

    public void deSerilize() throws IOException, ClassNotFoundException {


        // dans une méthode main
        // on simplifie le code en retirant la gestion des exceptions
        File fichier =  new File("src/main/resources/sauvegardeEditeur/saveNiveau.save") ;

        // ouverture d'un flux sur un fichier
        ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;

        // désérialization de l'objet
        Niveau m = (Niveau)ois.readObject() ;

        // fermeture du flux dans le bloc finally
    }
}
