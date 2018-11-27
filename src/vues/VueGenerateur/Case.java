package vues.VueGenerateur;

import model.generateur.Niveau;

import javax.swing.*;
import java.awt.*;

public class Case extends JLabel {

    private int ligne,colonne;
    private Niveau niveau;
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
        niveau.ajouterMur(colonne,ligne);
    }

    public void ajouterMonstre(){
        niveau.ajouterMonstre(type,colonne,ligne);
    }

    public void ajouterHeros(){
        niveau.placerHeros(colonne,ligne);
    }

    public void ajouterTresor(){
        niveau.ajouterItem(type,colonne,ligne);
    }

    public void ajouterHerbe(){

    }


    public void ajouterElement(){

        switch(type) {
            case "mur":
                this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/ArbrePetit.png")));
                ajouterMur();
                break;

            case "dragon":
                this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/dragon.png")));
                ajouterMonstre();
                break;

            case "soldat":
                this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/soldat.png")));
                ajouterMonstre();
                break;

            case "heros":
                this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/heros.png")));
                ajouterHeros();
                break;

            case "tresor":
                this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/tresor.png")));
                ajouterTresor();
                break;

            default:
                this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/herbe.png")));
                ajouterHerbe();
                break;
        }

    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    //public
}
