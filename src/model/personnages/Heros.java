package model.personnages;

public class Heros extends Personnage {

    private String nom;


    public Heros(int x, int y, String nom){
    	super(x,y);
    	this.pointVie = 5;
    	this.nom = nom;
    	this.attaque = 2;
    	this.defense = 0;
    }

    public String toString(){
        String donnee = "Le Heros est en  x :"+x+" , +y : "+y;

        return donnee;
    }
}
