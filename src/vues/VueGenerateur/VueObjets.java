package vues.VueGenerateur;

import controlleur.ControllerVueObjet;
import model.generateur.Niveau;

import javax.swing.*;
import javax.swing.border.Border;

import controlleur.MyMouseAdapter;
import controlleur.MyTransferHandler;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;

public class VueObjets extends JPanel{

    private ImageIcon mur;
    private ImageIcon dragon;
    private ImageIcon soldat;
    private ImageIcon heros;
    private ImageIcon herbe;
    private ImageIcon tresor;
    private ImageIcon epee;
    private ImageIcon piege;

    private static int NBOBJET = 8;

    private Case labelMur;
    private Case labelSoldat;
    private Case labelDragon;
    private Case labelHeros;
    private Case labelHerbe;
    private Case labelTresor;
    private Case labelEpee;
    private Case labelPiege;

    private JTextField lon;
    private JTextField haut;

    private Niveau niv;


    public VueObjets(final Niveau niv) {

        this.niv = niv;

        this.setLayout(new BorderLayout());

        JPanel objet = new JPanel();
        mur = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/ArbrePetit.png"));
        dragon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/dragon.png"));;
        soldat = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/soldat.png"));;
        heros = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/heros.png"));;
        herbe = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/herbe.png"));;
        tresor = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/tresor.png"));;
        epee = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/epee.png"));;
        piege = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/piege.png"));;

        objet.setLayout(new GridLayout(NBOBJET/2+NBOBJET%2,2));

        labelMur = new Case(niv,-1,-1,mur);
        labelDragon = new Case(niv,-1,-1,dragon);
        labelSoldat = new Case(niv,-1,-1,soldat);
        labelHeros = new Case(niv,-1,-1,heros);
        labelHerbe = new Case(niv,-1,-1,herbe);
        labelTresor = new Case(niv,-1,-1,tresor);
        labelEpee = new Case(niv,-1,-1,epee);
        labelPiege = new Case(niv,-1,-1,piege);

        labelDragon.setType("dragon");
        labelMur.setType("mur");
        labelSoldat.setType("soldat");
        labelHeros.setType("heros");
        labelHerbe.setType("herbe");
        labelTresor.setType("tresor");
        labelEpee.setType("epee");
        labelPiege.setType("piege");

        labelMur.setMinimumSize(new java.awt.Dimension(40,40));
        labelMur.setPreferredSize(new java.awt.Dimension(40,40));

        labelDragon.setName("noChange");
        labelMur.setName("noChange");
        labelSoldat.setName("noChange");
        labelHeros.setName("noChange");
        labelHerbe.setName("noChange");
        labelTresor.setName("noChange");
        labelEpee.setName("noChange");
        labelPiege.setName("noChange");



        this.dragAndDrop();

        objet.add(labelMur);
        objet.add(labelDragon);
        objet.add(labelSoldat);
        objet.add(labelHeros);
        objet.add(labelHerbe);
        objet.add(labelTresor);
        objet.add(labelEpee);
        objet.add(labelPiege);

        JPanel dimension = new JPanel();
        dimension.setLayout(new BoxLayout(dimension, BoxLayout.Y_AXIS));

        Border border=BorderFactory.createTitledBorder("Dimension");
        dimension.setBorder(border);

        JLabel labelLongueur = new JLabel("Longueur");
        JLabel labelLargeur = new JLabel("largeur");
        lon = new JTextField("10");
        haut = new JTextField("10");
        JButton valider = new JButton("Valider");


        JPanel loadSave = new JPanel();
        loadSave.setLayout(new GridLayout(3,1));
        Border borderSave=BorderFactory.createTitledBorder("Save & Load");
        loadSave.setBorder(borderSave);

        JButton buttonSave = new JButton("Sauvegarder");
        JButton buttonLoad = new JButton("Charger");
        JButton buttonLaunch = new JButton("Lancer");



        valider.addActionListener(new ControllerVueObjet(niv,"valider", lon,haut));
        buttonSave.addActionListener(new ControllerVueObjet(niv,"save"));
        buttonLoad.addActionListener(new ControllerVueObjet(niv,"load"));
        buttonLaunch.addActionListener(new ControllerVueObjet(niv,"launch"));


        dimension.add(labelLongueur);
        dimension.add(lon);
        dimension.add(labelLargeur);
        dimension.add(haut);
        dimension.add(valider);

        loadSave.add(buttonSave);
        loadSave.add(buttonLoad);
        loadSave.add(buttonLaunch);


        JPanel nord = new JPanel();
        nord.setLayout(new BoxLayout(nord,BoxLayout.Y_AXIS));

        nord.add(dimension);
        nord.add(loadSave);

        this.add(nord,BorderLayout.NORTH);
        this.add(objet, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void dragAndDrop(){
        labelMur.setTransferHandler(new MyTransferHandler());
        labelMur.addMouseListener(new MyMouseAdapter(this));

        labelSoldat.setTransferHandler(new MyTransferHandler());
        labelSoldat.addMouseListener(new MyMouseAdapter(this));

        labelDragon.setTransferHandler(new MyTransferHandler());
        labelDragon.addMouseListener(new MyMouseAdapter(this));

        labelHeros.setTransferHandler(new MyTransferHandler());
        labelHeros.addMouseListener(new MyMouseAdapter(this));

        labelHerbe.setTransferHandler(new MyTransferHandler());
        labelHerbe.addMouseListener(new MyMouseAdapter(this));

        labelTresor.setTransferHandler(new MyTransferHandler());
        labelTresor.addMouseListener(new MyMouseAdapter(this));

        labelEpee.setTransferHandler(new MyTransferHandler());
        labelEpee.addMouseListener(new MyMouseAdapter(this));

        labelPiege.setTransferHandler(new MyTransferHandler());
        labelPiege.addMouseListener(new MyMouseAdapter(this));
    }
}
