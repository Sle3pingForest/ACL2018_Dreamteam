package vues.VueGenerateur;

import model.generateur.Niveau;

import javax.swing.*;
import java.awt.*;

public class Case extends JLabel {

    private int ligne,colonne;
    private Niveau niveau;
    public static String DRAGON = "dragon";
    public String type;


    public Case(Niveau niveau, int ligne, int colonne, ImageIcon i){
        super();
        super.setIcon(i);
        this.ligne = ligne;
        this.colonne = colonne;
        this.niveau = niveau;
    }

    public ImageIcon getIcon() {
        return (ImageIcon) super.getIcon();
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void ajouterMur(){

    }

    public void ajouterDragon(){

    }

    public void ajouterSoldat(){

    }

    public void ajouterHeros(){

    }

    public void ajouterTresor(){

    }


    public void ajouterElement(){
        if(type.equals(DRAGON)){
                System.out.println("lapin");
        }

        /*switch() {

        }*/

    }

    public void setType(String type){
        this.type = type;
    }

    //public
}
