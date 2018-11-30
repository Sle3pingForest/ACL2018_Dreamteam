package controlleur;

import Jeu.Jeu;
import Jeu.StateGame;
import model.generateur.Niveau;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControllerVueObjet implements ActionListener {

    private String ope;
    private Niveau niv;
    private JTextField lon, haut;

    public ControllerVueObjet(Niveau niv,String ope){
        this.ope = ope;
        this.niv = niv;
    }

    public ControllerVueObjet(Niveau niv, String ope, JTextField lon, JTextField haut) {
        this.ope = ope;
        this.niv = niv;
        this.haut = haut;
        this.lon = lon;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        switch(ope){
            case "valider":
                niv.setDimensionInit(Integer.parseInt(haut.getText()),Integer.parseInt(lon.getText()));
                break;

            case "save":
                try {
                    niv.serialize();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;

            case "load":
                try {
                    niv.deSerilize();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                break;

            case "launch":
                Jeu.LESMURS = niv.getLesMurs();
                Jeu.LESITEMS = niv.getLesItems();
                Jeu.LESMONSTRES = niv.getLesMonstres();
                Jeu.XDEBUT = niv.getxDebut();
                Jeu.YDEBUT = niv.getyDebut();
                try {
                    new AppGameContainer(new StateGame(),   1000, 1000, false).start();
                } catch (SlickException e1) {
                    e1.printStackTrace();
                }
                break;

            default:
        }


    }
}
