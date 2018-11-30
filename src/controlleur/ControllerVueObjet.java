package controlleur;

import model.generateur.Niveau;

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

                break;

            default:
        }


    }
}
