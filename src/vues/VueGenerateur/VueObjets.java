package vues.VueGenerateur;

import model.generateur.Niveau;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.nio.ByteBuffer;

public class VueObjets extends JPanel implements ActionListener {

    private ImageIcon mur;
    private ImageIcon dragon;
    private ImageIcon soldat;
    private ImageIcon heros;
    private static int NBOBJET = 4;

    private JLabel labelMur;
    private JLabel labelSoldat;
    private JLabel labelDragon;
    private JLabel labelHeros;

    private JTextField lon;
    private JTextField haut;

    private Niveau niv;


    public VueObjets(Niveau niv) {

        this.niv = niv;

        this.setLayout(new BorderLayout());

        JPanel objet = new JPanel();
        mur = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/ArbrePetit.png"));
        dragon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/dragon.png"));;
        soldat = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/soldat.png"));;
        heros = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/heros.png"));;
        mur.setDescription("test");

        objet.setLayout(new GridLayout(NBOBJET/2,2));

        labelMur = new JLabel(mur);
        labelDragon = new JLabel(dragon);
        labelSoldat = new JLabel(soldat);
        labelHeros = new JLabel(heros);

        labelMur.setMinimumSize(new java.awt.Dimension(40,40));
        labelMur.setPreferredSize(new java.awt.Dimension(40,40));


        this.dragAndDrop();

        objet.add(labelMur);
        objet.add(labelDragon);
        objet.add(labelSoldat);
        objet.add(labelHeros);


        JPanel dimension = new JPanel();
        dimension.setLayout(new BoxLayout(dimension, BoxLayout.Y_AXIS));

        Border border=BorderFactory.createTitledBorder("Dimension");
        dimension.setBorder(border);

        JLabel labelLongueur = new JLabel("Longueur");
        JLabel labelLargeur = new JLabel("largeur");
        lon = new JTextField("10");
        haut = new JTextField("10");
        JButton valider = new JButton("Valider");
        JButton button = new JButton("Sauvegarder");

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                niv.setHauteurLargeur(Integer.parseInt(haut.getText()), Integer.parseInt(lon.getText()));
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        dimension.add(labelLongueur);
        dimension.add(lon);
        dimension.add(labelLargeur);
        dimension.add(haut);
        dimension.add(valider);
        dimension.add(button);

        this.add(dimension,BorderLayout.NORTH);
        this.add(objet, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void dragAndDrop(){
        final String propertyName = "icon";
        labelMur.setTransferHandler(new MyTransferHandler());
        labelMur.addMouseListener(new MyMouseAdapter(this));

        labelSoldat.setTransferHandler(new MyTransferHandler());
        labelSoldat.addMouseListener(new MyMouseAdapter(this));

        labelDragon.setTransferHandler(new MyTransferHandler());
        labelDragon.addMouseListener(new MyMouseAdapter(this));

        labelHeros.setTransferHandler(new MyTransferHandler());
        labelHeros.addMouseListener(new MyMouseAdapter(this));
    }

    public void reInitIcon(){
        labelMur.setIcon(mur);
        labelHeros.setIcon(heros);
        labelDragon.setIcon(dragon);
        labelSoldat.setIcon(soldat);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
