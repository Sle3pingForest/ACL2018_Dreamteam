package vues.VueGenerateur;

import model.personnages.Heros;
import model.personnages.monstres.Dragon;
import model.personnages.monstres.Soldat;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import vues.VueElementDecor;
import vues.VueHeros;
import vues.VueMonstres.VueDragon;
import vues.VueMonstres.VueSoldat;

import javax.swing.*;
import java.awt.*;

public class VueObjets extends JPanel{

    private ImageIcon mur;
    private Image dragon;
    private Image soldat;
    private Image heros;
    private static int NBOBJET = 4;

    public VueObjets() {
        mur = new ImageIcon("main/resources/map/tuiles/Mur/ArbrePetit.png");
        /*dragon = VueDragon.getImageGenerateur();
        soldat = VueSoldat.getImageGenerateur();
        heros = VueHeros.getImageGenerateur();*/
        this.setLayout(new GridLayout(NBOBJET/2,2));
        this.add(new JButton("mur",mur));
        this.setVisible(true);
    }
}
