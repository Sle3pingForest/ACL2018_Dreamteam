package model.generateur;

import java.util.ArrayList;

public class Monde {

    private ArrayList<Niveau> lesNiveaux;
    private String nom;

    public Monde(String nom) {
        this.nom = nom;
        this.lesNiveaux = new ArrayList<Niveau>();
    }
}
