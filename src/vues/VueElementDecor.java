package vues;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class VueElementDecor {

    private static Image mur ;
    private static Image chemin;

    public static void mettreForet() throws SlickException {
        mur = new Image("main/resources/map/tuiles/Mur/ArbrePetit.png");
        chemin = new Image("main/resources/map/tuiles/Chemin/HerbeNormal.png");
    }



    public static Image getChemin() {
        return chemin;
    }

    public static Image getMur() {
        return mur;
    }
}
