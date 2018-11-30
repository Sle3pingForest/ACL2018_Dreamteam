package model.generateur;

import model.Item.Epee;
import model.Item.Item;
import model.Item.Piege;
import model.Item.Tresor;
import model.mur.Mur;
import model.personnages.monstres.Dragon;
import model.personnages.monstres.Monstre;
import model.personnages.monstres.Soldat;

import javax.swing.*;
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
    private int hauteur =0,largeur=0;

    public Niveau(String nom) {
        this.nom = nom;
        this.xDebut = -1;
        this.yDebut = -1;
        this.xFin = -1;
        this.yFin = -1;
    }

    public Mur[][] getLesMurs() {
        return lesMurs;
    }

    public ArrayList<Monstre> getLesMonstres() {
        return lesMonstres;
    }

    public Item[][] getLesItems() {
        return lesItems;
    }

    public int getxDebut() {
        return xDebut;
    }

    public int getyDebut() {
        return yDebut;
    }

    public void placerHeros(int x, int y){

        if(xFin == x && yFin == y){
            xFin = -1;
            yFin = -1;
        }

        this.xDebut = x;
        this.yDebut = y;
        setChanged();
        notifyObservers();
    }

    public void ajouterMur(int x,int y){

        if(xDebut == x && yDebut == y){
            xDebut = -1;
            yDebut = -1;
        }

        if(xFin == x && yFin == y){
            xFin = -1;
            yFin = -1;
        }

        Mur mur = new Mur(x,y);
        lesMurs[x][y] = mur;
        if(checkMonstres[x][y] != null){
            lesMonstres.remove(checkMonstres[x][y]);
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

        if(xDebut == x && yDebut == y){
            xDebut = -1;
            yDebut = -1;
        }

        if(xFin == x && yFin == y){
            xFin = -1;
            yFin = -1;
        }

        Monstre monstre = null;
        if(type.equals("dragon")){
            monstre = new Dragon(x,y);
        }
        else if(type.equals("soldat")){
            monstre = new Soldat(x,y);
        }

        if(checkMonstres[x][y] != null){
            lesMonstres.remove(checkMonstres[x][y]);
            checkMonstres[x][y] = null;
        }

        if(lesItems[x][y] != null){
            lesItems[x][y] =null;
        }

        if(lesMurs[x][y] != null){
            lesMurs[x][y] = null;
        }

        lesMonstres.add(monstre);
        checkMonstres[x][y] = monstre;
    }

    /*public void suppMonstre(int x,int y){
        labMonstres.get(y).set(x,null);
    }*/


    public void ajouterItem(String type,int x,int y){


        if(lesItems[x][y] != null){
            lesItems[x][y] =null;
        }

        if(xDebut == x && yDebut == y){
            xDebut = -1;
            yDebut =-1;
        }

        if(xFin == x && yFin == y){
            xFin = -1;
            yFin =-1;
        }

        Item item;
        if(type.equals("tresor")){
            System.out.println("lapin");
            for(int i=0;i<lesItems.length;i++){
                for(int j=0;j<lesItems.length;j++){
                    if(lesItems[i][j] instanceof Tresor){
                        System.out.println("lapin2");
                        lesItems[i][j] = null;
                    }
                }
            }
            item = new Tresor(x,y,null);
            xFin = x;
            yFin = y;

            lesItems[x][y] = item;
            setChanged();
            notifyObservers();
        }
        else if(type.equals("epee")){
            item = new Epee(x,y);
            lesItems[x][y] = item;
        }

        else if(type.equals("piege")){
            item = new Piege(x,y);
            lesItems[x][y] = item;
        }


        if(checkMonstres[x][y] != null){
            lesMonstres.remove(checkMonstres[x][y]);
            checkMonstres[x][y] = null;
        }

        if(lesMurs[x][y] != null){
            lesMurs[x][y] = null;
        }
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setDimensionInit(int hauteur, int largeur) {
        xDebut = -1;
        yDebut = -1;
        this.hauteur = hauteur;
        this.largeur = largeur;
        lesMurs = new Mur[hauteur][largeur];
        lesItems = new Item[hauteur][largeur];
        checkMonstres = new Monstre[hauteur][largeur];
        lesMonstres = new ArrayList<Monstre>();
        setChanged();
        notifyObservers();
    }

    public void setHauteurLargeur(int hauteur, int largeur){
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public int getLargeur() {
        return largeur;
    }

    public Mur getMur(int i,int j){
        return lesMurs[i][j];
    }

    public Monstre getMonstre(int i,int j){
        return checkMonstres[i][j];
    }

    public Item getItem(int i,int j){
        return lesItems[i][j];
    }

    public int getXHeros(){
        return xDebut;
    }
    public int getYHeros(){
        return  yDebut;
    }

    public void suppCase(int x, int y) {

        checkMonstres[x][y] = null;
        lesItems[x][y] =null;
        lesMurs[x][y] = null;
        lesMonstres.remove(checkMonstres[x][y]);

        if(this.xDebut == x && this.yDebut == y){
            this.xDebut = -1;
            this.yDebut = -1;
        }

        if(this.xFin == x && this.yFin == y){
            this.xFin = -1;
            this.yFin = -1;
        }
    }

    public void serialize() throws IOException {

        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;
        File fichier = null;

        if (dialogue.showOpenDialog(null)==
                JFileChooser.APPROVE_OPTION) {
            fichier = dialogue.getSelectedFile();
            sortie = new PrintWriter
                    (new FileWriter(fichier.getPath(), true));
            sortie.close();
        }


        if (fichier !=null) {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
            // sérialization de l'objet
            oos.writeObject(this);
        }
    }

    public void deSerilize() throws IOException, ClassNotFoundException {


        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;
        File fichier = null;

        if (dialogue.showOpenDialog(null)==
                JFileChooser.APPROVE_OPTION) {
            fichier = dialogue.getSelectedFile();
            sortie = new PrintWriter
                    (new FileWriter(fichier.getPath(), true));
            sortie.close();
        }

        if (fichier !=null) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));

            // désérialization de l'objet
            Niveau n = (Niveau) ois.readObject();
            Charger(n);
        }

        // fermeture du flux dans le bloc finally
    }

    private void Charger(Niveau n) {
        setHauteurLargeur(n.hauteur,n.largeur);
        this.lesMurs = n.lesMurs;
        this.checkMonstres = n.checkMonstres;
        this.lesMonstres = n.lesMonstres;
        this.lesItems = n.lesItems;
        this.nom = n.nom;
        this.xFin = n.xFin;
        this.yFin = n.yFin;
        this.xDebut = n.xDebut;
        this.yDebut = n.yDebut;
        setChanged();
        notifyObservers();
    }
}
