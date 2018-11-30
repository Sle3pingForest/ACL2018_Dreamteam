package vues.VueGenerateur;

import model.Item.Epee;
import model.Item.Piege;
import model.Item.Tresor;
import model.generateur.Niveau;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controlleur.MyTransferHandler;
import model.personnages.monstres.Dragon;
import model.personnages.monstres.Soldat;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class VueCreaLaby extends JPanel implements Observer {

    private int nbLigne,nbColonne;
    private JLabel[][] tabLab;
    private Niveau niv;
    private  JPanel jp;
    private JScrollPane js;

    public VueCreaLaby(Niveau niv, int nbLigne, int nbColonne){

        this.niv = niv;
        niv.addObserver(this);

        this.nbLigne = nbLigne;
        this.nbColonne = nbColonne;

        this.setLayout(new BorderLayout());

        jp = new JPanel();
        jp.setLayout(null);


        tabLab = new JLabel[nbLigne][nbColonne];
        jp.setPreferredSize(new Dimension(nbLigne*50,nbColonne*50));

        js = new JScrollPane(jp);
        js.createHorizontalScrollBar();
        js.createVerticalScrollBar();
        this.add(js);
    }

    public Dimension getTailleLaby(){
        return new Dimension(50*nbLigne,50*nbColonne);
    }

    private void modifLab(int h, int l){

        for (int i = 0; i < nbLigne; i++) {

            for (int j = 0; j < nbColonne; j++) {
                jp.remove(tabLab[i][j]);
            }
        }

        nbLigne=h;
        nbColonne=l;
        tabLab = new Case[nbLigne][nbColonne];
        jp.setPreferredSize(new Dimension(nbLigne*50,nbColonne*50));

        for(int i =0;i<nbLigne;i++){
            for(int j =0;j<nbColonne;j++){

                if(i==0 || i==nbLigne-1 || j==0 || j==nbColonne-1) {
                    tabLab[i][j] = new Case(niv, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/ArbrePetit.png")));
                    tabLab[i][j].setName("noChange");
                }
                else{
                    if (niv.getMur(i, j) != null) {
                        tabLab[i][j] = new Case(niv, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/ArbrePetit.png")));
                    }
                    else if (niv.getXHeros() == i && niv.getYHeros() == j) {
                        tabLab[i][j] = new Case(niv, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/heros.png")));
                    }
                    else if (niv.getItem(i, j) instanceof Tresor) {
                        tabLab[i][j] = new Case(niv, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/tresor.png")));
                    }
                    else if (niv.getItem(i, j) instanceof Epee) {
                        tabLab[i][j] = new Case(niv, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/epee.png")));
                    }
                    else if (niv.getMonstre(i, j) instanceof Dragon) {
                        tabLab[i][j] = new Case(niv, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/dragon.png")));
                    }
                    else if (niv.getMonstre(i, j) instanceof Soldat) {
                        tabLab[i][j] = new Case(niv, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/soldat.png")));
                    }
                    else if (niv.getItem(i, j) instanceof Piege) {
                        tabLab[i][j] = new Case(niv, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/piege.png")));
                    }
                    else {
                        tabLab[i][j] = new Case(niv, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/herbe.png")));
                    }
                }
                tabLab[i][j].setTransferHandler(new MyTransferHandler());
                tabLab[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                tabLab[i][j].setBounds(i*50,j*50,50,50);
                jp.add(tabLab[i][j]);
            }
        }
        js.setViewportView(jp);
        this.add(js);
    }


    @Override
    public void update(Observable o, Object arg) {
        this.modifLab(niv.getHauteur(),niv.getLargeur());
        this.repaint();
    }
}
