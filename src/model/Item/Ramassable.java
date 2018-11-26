package model.Item;
//Permet de savoir si l'objet est ramassable
public interface Ramassable {

    boolean ramasser = false;

    boolean estRamasser();
    boolean setRamasser();
}
